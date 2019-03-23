package org.sid.web;

import java.util.List;

import javax.validation.Valid;


import org.sid.dao.CommandRepository;
import org.sid.entities.Client;
import org.sid.entities.Commande;

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
public class CommandeController {
	@Autowired
	private CommandRepository commandRepository;


	@RequestMapping(value = "/admin/commandeManager", method = RequestMethod.GET)
	public String commandeManager(Model model, @RequestParam(name = "page", defaultValue = "0") int cl,
			@RequestParam(name = "size", defaultValue = "5") int s,
			@RequestParam(name = "motCle", defaultValue = "") String mc) {

		List<Commande> mesCommandes = commandRepository.findAll();
		model.addAttribute("mesCommandes",mesCommandes);
		Page<Commande> pageCommandes = commandRepository.chercherCommande("%" + mc + "%", new PageRequest(cl, s));
		model.addAttribute("listeCommandes", pageCommandes.getContent());
		int[] pages = new int[pageCommandes.getTotalPages()];
		model.addAttribute("pages", pages);
		model.addAttribute("size", s);
		model.addAttribute("pageCourante", cl);
		model.addAttribute("motCle", mc);
		
		return "commandes";
	}



	// edition d'un commande
	/*@RequestMapping(value = "/admin/editCommande", method = RequestMethod.GET)
	public String editCommande(Model model, @RequestParam(name = "id") Long x) {
		Commande cl = commandRepository.editCommande(x);
		model.addAttribute("commande", cl);
		return "editionCommande";
	}

	// ajouter un commande
	@RequestMapping(value = "/admin/saveCommande", method = RequestMethod.POST)
	
		public String saveCommande(Model model,@Valid Commande commande,BindingResult bindingResult) {
			
			if (bindingResult.hasErrors())
				return "Commandes";
			
			commandRepository.save(commande);
	
			return "redirect:/admin/commandeManager";
	}
	
	@RequestMapping(value = "/admin/saveMCommande", method = RequestMethod.POST)
	public String saveMCommande(Model model,@Valid Commande commande,BindingResult bindingResult) {
		
		if (bindingResult.hasErrors())
			return "editionCommande";
		
		commandRepository.save(commande);
		return "confirmationEditionCommande";
	}
	

	// suppression d'un commande
	@RequestMapping(value = "/admin/deleteCommande", method = RequestMethod.GET)
	public String deleteCat(Long id, String motCle, int page, int size) {
		commandRepository.deleteById(id);
		return "redirect:/admin/commandeManager?page=" + page + "&size=" + size + "&motCle=" + motCle + "";
	}
*/
}
