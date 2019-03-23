package org.sid.web;

import javax.validation.Valid;

import org.sid.dao.ClientRepository;
import org.sid.dao.ProduitRepository;
import org.sid.entities.Categorie;
import org.sid.entities.Client;
import org.sid.entities.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ClientController {
	@Autowired
	private ClientRepository clientRepository;

	@RequestMapping(value = "/admin/clientManager", method = RequestMethod.GET)
	public String clientManager(Model model, @RequestParam(name = "page", defaultValue = "0") int cl,
			@RequestParam(name = "size", defaultValue = "5") int s,
			@RequestParam(name = "motCle", defaultValue = "") String mc) {

		model.addAttribute("client", new Client());

		Page<Client> pageClients = clientRepository.chercher("%" + mc + "%", new PageRequest(cl, s));
		model.addAttribute("listeClients", pageClients.getContent());
		int[] pages = new int[pageClients.getTotalPages()];
		model.addAttribute("pages", pages);
		model.addAttribute("size", s);
		model.addAttribute("pageCourante", cl);
		model.addAttribute("motCle", mc);
		return "Clients";
	}

	// edition d'un client
	@RequestMapping(value = "/admin/editClient", method = RequestMethod.GET)
	public String editClient(Model model, @RequestParam(name = "id") Long x) {
		Client cl = clientRepository.editClient(x);
		model.addAttribute("client", cl);
		return "editionClient";
	}

	// ajouter un client
	@RequestMapping(value = "/admin/saveClient", method = RequestMethod.POST)

	public String saveClient(Model model, @Valid Client client, BindingResult bindingResult) {

		if (bindingResult.hasErrors())
			return "Clients";

		clientRepository.save(client);
		return "redirect:/admin/clientManager";
	}

	@RequestMapping(value = "/admin/saveMClient", method = RequestMethod.POST)
	public String saveMClient(Model model, @Valid Client client, BindingResult bindingResult) {

		if (bindingResult.hasErrors())
			return "editionClient";

		clientRepository.save(client);
		return "confirmationEditionClient";
	}

	// suppression d'un client
	@RequestMapping(value = "/admin/deleteClient", method = RequestMethod.GET)
	public String deleteCat(Long id, String motCle, int page, int size) {
		clientRepository.deleteById(id);
		return "redirect:/admin/clientManager?page=" + page + "&size=" + size + "&motCle=" + motCle + "";
	}

}
