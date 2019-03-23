package org.sid.web;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.sid.dao.CategorieRepository;
import org.sid.dao.LigneCommandeRepository;
import org.sid.dao.ProduitRepository;
import org.sid.entities.Categorie;
import org.sid.entities.LigneCommande;
import org.sid.entities.Panier;
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
public class InternauteController {
	@Autowired
	private ProduitRepository prodRepo;
	@Autowired
	private CategorieRepository catRepo;
	@Autowired
	private LigneCommandeRepository ligneRepo;

// variables
	public int quantite = 0;
	public double total = 0;
	private Long idProd ;
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String Home(Model model, @RequestParam(name = "page", defaultValue = "0") int p,
			@RequestParam(name = "size", defaultValue = "3") int s,
			@RequestParam(name = "motCle", defaultValue = "") String mc,
			@RequestParam(name = "idCat", defaultValue = "") Long x) {

		model.addAttribute("Categorie", new Categorie());
		List<Categorie> listeCat = catRepo.findAll();
		model.addAttribute("listeCat", listeCat);

		// list of all products
		List<Produit> listeProd = prodRepo.findAll();
		model.addAttribute("listeProd", listeProd);

		// list of products by category

		List<Produit> listePorduitsParCat = prodRepo.produitParCategorie(x);
		System.out.println("idCat" + x);
		System.out.println("x" + x);
		model.addAttribute("idCateg", x);
		model.addAttribute("listePorduitsParCat", listePorduitsParCat);

		// Search product
		Page<Produit> pageProduits = prodRepo.chercher("%" + mc + "%", new PageRequest(p, s));
		int[] pages = new int[pageProduits.getTotalPages()];
		model.addAttribute("pages", pages);
		model.addAttribute("size", s);
		model.addAttribute("pageCourante", p);
		model.addAttribute("motCle", mc);

		return "index";
	}

	@RequestMapping(value = "/item", method = RequestMethod.GET)
	public String item(Model model, @RequestParam(name = "id") Long x) {
		List<Categorie> listeCat = catRepo.findAll();
		model.addAttribute("listeCat", listeCat);
		idProd = x; 
		System.out.println("idProd"+idProd);
		Produit produit = prodRepo.editP(x);
		model.addAttribute("produit", produit);
		return "shopItem";

	}

	// ajouter le produit à la liste des achats
	@RequestMapping(value = "/addItem", method = RequestMethod.POST)
	public String AjouterProduitPanier(Model model, LigneCommande ligneCommande) {
	
		
		
		// id du produit à commander
		System.out.println("idProd dans add Item"+idProd);
		Produit produit = prodRepo.editP(idProd);
		// Ajout d'une ligne de commande
		LigneCommande lc = new LigneCommande(produit.getPrix()*ligneCommande.getQuantite(), ligneCommande.getQuantite(), produit);
		ligneRepo.save(lc);
		model.addAttribute("lc", lc);
//		// ajout d'un item au panier
//		Panier panier = new Panier();
//		panier.addItem(produit, quantite);
//		TotalCommandes = panier.getTotal();
//		model.addAttribute("total",TotalCommandes);
//	    // lister les items
//		Collection<LigneCommande> items = panier.getItems();
//		model.addAttribute("listeCommandes",items);
//		System.out.println();
		return "panier2";
	}
	
	// contenu panier
	@RequestMapping(value = "/panier", method = RequestMethod.GET)
	public String listItems(Model model) {
		// lister les lignes de commandes
		Collection<LigneCommande> listeCommandes = ligneRepo.findAll();
		model.addAttribute("listeCommandes", listeCommandes);
		//total
		double total = ligneRepo.total();
    	model.addAttribute("total",total);
		return "panier";
	}

		// delette commande
		@RequestMapping(value = "/deleteLC", method = RequestMethod.GET)
		public String deleteLC(Model model, @RequestParam(name = "id") Long x) {
			ligneRepo.editLC(x);
			ligneRepo.deleteById(x);
			// lister les lignes de commandes
			Collection<LigneCommande> listeCommandes = ligneRepo.findAll();
			model.addAttribute("listeCommandes", listeCommandes);
           return "panier";
		}
		
		//surpprimer toutes les lignes de commande  
		@RequestMapping(value = "/deleteAll", method = RequestMethod.GET)
		public String deleteAll(Model model) {
			ligneRepo.deleteAll();
			// lister les lignes de commandes
			Collection<LigneCommande> listeCommandes = ligneRepo.findAll();
			model.addAttribute("listeCommandes", listeCommandes);
           return "panier";
		}
		
		//passer une commande 
				@RequestMapping(value = "/commande", method = RequestMethod.GET)
				public String commande() {

		           return "panier";
				}
		
		

}
