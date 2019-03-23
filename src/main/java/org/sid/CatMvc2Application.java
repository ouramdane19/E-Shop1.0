package org.sid;


import org.sid.dao.CategorieRepository;
import org.sid.dao.ClientRepository;
import org.sid.dao.CommandRepository;
import org.sid.dao.LigneCommandeRepository;
import org.sid.dao.ProduitRepository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class CatMvc2Application {

	public static void main(String[] args) {
		/*ApplicationContext ctx = SpringApplication.run(CatMvc2Application.class, args);
		ProduitRepository produitRepository = ctx.getBean(ProduitRepository.class);
		
		// ajout de produits 
		/*produitRepository.save(new Produit("Acer200",150.25,100));
		produitRepository.save(new Produit("HP",150.25,10));
		produitRepository.save(new Produit("Intel",1.25,101));
		produitRepository.save(new Produit("Toshiba",15000.25,120));
		produitRepository.save(new Produit("LG",1520,10));*/
		
		// affichage des produits en utilisation forEache et une expression lambda de java 8
		
		/*produitRepository.findAll().forEach(p->System.out.println(p.getDesignation()));*/
		ApplicationContext ctx = SpringApplication.run(CatMvc2Application.class, args);
		ProduitRepository produitRepository = ctx.getBean(ProduitRepository.class);
		CategorieRepository  categorieRepository = ctx.getBean(CategorieRepository.class);
		CommandRepository  commandRepository = ctx.getBean(CommandRepository.class);
		ClientRepository  clientRepository = ctx.getBean(ClientRepository.class);
		LigneCommandeRepository ligneCommandeRepository = ctx.getBean(LigneCommandeRepository.class);

		// ajout de produits 
		/*produitRepository.save(new Produit("Acer200",150.25,100));
		produitRepository.save(new Produit("HP",150.25,10));
		produitRepository.save(new Produit("Intel",1.25,101));
		produitRepository.save(new Produit("Toshiba",15000.25,120));
		produitRepository.save(new Produit("LG",1520,10));*/
		
		// affichage des produits en utilisation forEache et une expression lambda de java 8
		System.out.println("**********Designation produit********");
		produitRepository.findAll().forEach(p->System.out.println(p.getDesignation()));
		System.out.println("**********nom categoriet********");
		categorieRepository.findAll().forEach(cat->System.out.println(cat.getNomCategorie()));
		System.out.println("**********Id commande********");
		commandRepository.findAll().forEach(co->System.out.println(co.getIdCommande()));
		System.out.println("**********lignecommande ligneCommande********");
		ligneCommandeRepository.findAll().forEach(lc->System.out.println(lc.getQuantite()));
		System.out.println("**********nom client********");
		clientRepository.findAll().forEach(cl-> System.out.println(cl.getNomClient()));
		
		
		
		
		
	}
}
