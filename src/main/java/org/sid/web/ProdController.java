package org.sid.web;



import java.util.List;
import java.util.Optional;


import org.sid.dao.ProduitRepository;
import org.sid.entities.Categorie;
import org.sid.entities.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value="/api")

public class ProdController {
	
	@Autowired
	private ProduitRepository produitRepository;
	
	@GetMapping(value="/products",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Produit>> getProducts() {
		List<Produit> listProducts = produitRepository.findAll();
		return ResponseEntity.ok().body(listProducts);
	}
	

}