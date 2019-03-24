package org.sid.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.transaction.Transactional;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;
import org.springframework.lang.NonNull;


@Entity
@Transactional
public class Produit implements Serializable {
	@Id @GeneratedValue
	private  Long id;
	@NonNull
	@Size(min=4,max=80)
	private String designation;
	@DecimalMin(value="10")
	private double prix;
	@DecimalMin(value="1")
	private int quantite ;
	@Lob
	private byte[] photo;
	private String nomPhoto;
	private boolean selected;
	@ManyToOne 
	@JoinColumn(name="idCategorie")  // Foreign key ou clé etrangère
	
	private Categorie categorie;
	
	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public String getNomPhoto() {
		return nomPhoto;
	}



	public void setNomPhoto(String nomPhoto) {
		this.nomPhoto = nomPhoto;
	}



	public boolean isSelected() {
		return selected;
	}



	public void setSelected(boolean selected) {
		this.selected = selected;
	}



	public Produit() {
		super();
	
	}

	public Produit(String designation,  double prix,
			 int quantite, byte[] photo) {
		super();
		this.designation = designation;
		this.prix = prix;
		this.quantite = quantite;
		this.photo = photo;
	}

	public Produit(String designation,  double prix,
			 int quantite) {
		super();
		this.designation = designation;
		this.prix = prix;
		this.quantite = quantite;
	
	}

	public byte[] getPhoto() {
		return photo;
	}



	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
		
}
