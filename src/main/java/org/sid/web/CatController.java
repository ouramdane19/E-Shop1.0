package org.sid.web;


import java.util.List;
import java.util.Optional;

import org.sid.dao.CategorieRepository;
import org.sid.dao.ProduitRepository;
import org.sid.entities.Categorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class CatController {
	
	@Autowired
	private CategorieRepository categorieRepository;
	@Autowired
	private ProduitRepository produitRepository;
	
	@RequestMapping(value="/api/categories",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Categorie>> getCategories() {
		List<Categorie> listCategories = categorieRepository.findAll();
		return ResponseEntity.ok().body(listCategories);
	}

	
}