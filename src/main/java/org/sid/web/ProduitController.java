package org.sid.web;

import java.util.List;

import javax.validation.Valid;

import org.sid.dao.CategorieRepository;
import org.sid.dao.ProduitRepository;
import org.sid.entities.Categorie;
import org.sid.entities.Produit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cloudinary.Cloudinary;

@Controller

public class ProduitController {
	public double total=0;
	@Autowired
	private ProduitRepository produitRepository;

	@Autowired
	private CategorieRepository categorieRepository;


	
	@RequestMapping(value = "/user/index", method = RequestMethod.GET)
	public String index(Model model,  @RequestParam(name = "page", defaultValue = "0") int p,
			@RequestParam(name = "size", defaultValue = "8") int s,
			@RequestParam(name = "motCle", defaultValue = "") String mc) {
		

		//categories
		model.addAttribute("categorie", new Categorie());
		List<Categorie> listeCategories = categorieRepository.findAll();
		model.addAttribute("listeCategories",listeCategories);
	    // products
		model.addAttribute("produit", new Produit());
		Page<Produit> pageProduits = produitRepository.chercher("%" + mc + "%", new PageRequest(p, s));
		model.addAttribute("listeProduits", pageProduits.getContent());
		int[] pages = new int[pageProduits.getTotalPages()];
		model.addAttribute("pages", pages);
		model.addAttribute("size", s);
		model.addAttribute("pageCourante", p);
		model.addAttribute("motCle", mc);
		
		return "prod";
		
	}

	
// suppression produit
	@RequestMapping(value = "/admin/delete", method = RequestMethod.GET)
	public String delete(Long id, String motCle, int page, int size) {
		produitRepository.deleteById(id);
		
		return "redirect:/user/index?page=" + page + "&size=" + size + "&motCle=" + motCle + "";
	}


	// ajouter un produit
	@RequestMapping(value = "/admin/save", method = RequestMethod.POST)
	public String saveProduct(Model model, @Valid Produit produit, BindingResult bindingResult) {

		model.addAttribute("produit", new Produit());

		if (bindingResult.hasErrors()) {
			System.out.println("erreur au niveau du binding");
			return "prod";
		}

		//lister les catégories
		model.addAttribute("categorie", new Categorie());
		List<Categorie> listeCategories = categorieRepository.findAll();
		model.addAttribute("listeCategories",listeCategories);	
		produitRepository.save(produit);	
		return "redirect:/user/index";

	}
	
     // modifier un produit
	@RequestMapping(value = "/admin/save2", method = RequestMethod.POST)
	public String saveModif(Model model, @Valid Produit produit, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			System.out.println("erreur au niveau du binding");
			return "editionProduit";
		}

		produitRepository.save(produit);		
		return "confirmationEdition";

	}
	
	
	// editer un produit
	@RequestMapping(value = "/admin/edit", method = RequestMethod.GET)
	public String editProduit(Model model, @RequestParam(name = "id") Long x) {
		Produit p = produitRepository.editP(x);
		model.addAttribute("produit", p);

		return "editionProduit";
	}

    


	
}
