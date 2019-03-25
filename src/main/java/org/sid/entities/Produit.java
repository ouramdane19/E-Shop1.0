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
	@DecimalMin(value="1")
	private double prix;
	@DecimalMin(value="1")
	private int quantite ;
	private String photo;
	private boolean selected;
	private String productDescription;
	@ManyToOne 
	@JoinColumn(name="idCategorie")  // Foreign key
	private Categorie categorie;
   
	// constructors 

	public Produit() {
		super();
	
	}

	public Produit(String designation,String productDescription,double prix,
			 int quantite, String photo) {
		super();
		this.designation = designation;
		this.prix = prix;
		this.quantite = quantite;
		this.photo = photo;
		this.productDescription = productDescription;
	}

	public Produit(String designation,String productDescription,double prix,
			 int quantite, String photo, Categorie categorie) {
		super();
		this.designation = designation;
		this.prix = prix;
		this.quantite = quantite;
		this.photo = photo;
		this.productDescription = productDescription;
		this.categorie = categorie;
	}
	public Produit(String designation,  double prix,
			 int quantite) {
		super();
		this.designation = designation;
		this.prix = prix;
		this.quantite = quantite;
	
	}
	
	// getters and setters

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public boolean isSelected() {
		return selected;
	}


	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	
	public String getPhoto() {
		return photo;
	}



	public void setPhoto(String photo) {
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
