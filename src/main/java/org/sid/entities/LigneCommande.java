package org.sid.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.transaction.Transactional;

import lombok.Data;

@Data
@Entity
@Transactional
public class LigneCommande implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idLigneCommande;
	private double prix;
	private double quantite;
	@ManyToOne
	@JoinColumn(name="idProduit")
	private Produit produit;

	
	public LigneCommande() {
		super();
		
	}
	public LigneCommande( double prix, double quantite, Produit produit) {
		super();
		
		this.prix = prix;
		this.quantite = quantite;
		this.produit = produit;
	}
}
