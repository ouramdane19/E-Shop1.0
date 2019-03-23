package org.sid.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.transaction.Transactional;

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
	public Long getIdLigneCommande() {
		return idLigneCommande;
	}
	public void setIdLigneCommande(Long idLigneCommande) {
		this.idLigneCommande = idLigneCommande;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	public double getQuantite() {
		return quantite;
	}
	public void setQuantite(double quantite) {
		this.quantite = quantite;
	}
	public Produit getProduit() {
		return produit;
	}
	public void setProduit(Produit produit) {
		this.produit = produit;
	}
	
	
	

}
