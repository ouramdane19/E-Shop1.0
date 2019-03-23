package org.sid.web;

import javax.validation.Valid;

import org.sid.dao.CategorieRepository;
import org.sid.entities.Categorie;
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

public class CategoriesController {
	@Autowired
	private CategorieRepository categorieRepository;

	@RequestMapping(value = "/admin/categories", method = RequestMethod.GET)
	public String indexCat(Model model, @RequestParam(name = "page", defaultValue = "0") int c,
			@RequestParam(name = "size", defaultValue = "2") int s,
			@RequestParam(name = "motCle", defaultValue = "") String mc) {
		model.addAttribute("Categorie", new Categorie());

		Page<Categorie> pageCategories = categorieRepository.chercher("%" + mc + "%", new PageRequest(c, s));
		model.addAttribute("listeCategories", pageCategories.getContent());
		int[] pages = new int[pageCategories.getTotalPages()];
		model.addAttribute("pages", pages);
		model.addAttribute("size", s);
		model.addAttribute("pageCourante", c);
		model.addAttribute("motCle", mc);
		return "categories";
	}

	// delete category
	@RequestMapping(value = "/admin/deleteCat", method = RequestMethod.GET)
	public String deleteCat(Long id, String motCle, int page, int size) {
		categorieRepository.deleteById(id);
		return "redirect:/admin/categories?page=" + page + "&size=" + size + "&motCle=" + motCle + "";
	}

	// ajouter un Categorie
	@RequestMapping(value = "/admin/saveCat", method = RequestMethod.POST)
	public String saveCat(Model model, @Valid Categorie categorie, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			System.out.println("erreur au niveau du binding");
			return "categories";
		}

		categorieRepository.save(categorie);
		return "redirect:/admin/categories";

	}

	@RequestMapping(value = "/admin/save2Cat", method = RequestMethod.POST)
	public String saveModif(Model model, @Valid Categorie categorie, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			System.out.println("erreur au niveau du binding");
			return "editionCategorie";
		}

		categorieRepository.save(categorie);
		return "confirmationEditionCat";

	}

	// editer un Categorie
	@RequestMapping(value = "/admin/editCat", method = RequestMethod.GET)
	public String editCategorie(Model model, @RequestParam(name = "id") Long x) {
		Categorie c = categorieRepository.editCategorie(x);
		model.addAttribute("categorie", c);

		return "editionCategorie";
	}

}
