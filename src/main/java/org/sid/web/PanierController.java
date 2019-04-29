package org.sid.web;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.sid.dao.LigneCommandeRepository;
import org.sid.entities.LigneCommande;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/api")
public class PanierController {
 
	@Autowired
	private LigneCommandeRepository ligneRepo;
	@GetMapping(value="/panier",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<LigneCommande>> listItems() {
		List<LigneCommande> listeCommandes= ligneRepo.findAll();
		return ResponseEntity.ok().body(listeCommandes);
	}
}
