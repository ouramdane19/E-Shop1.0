package org.sid.entities;
import java.io.Serializable;
import java.util.*;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.transaction.Transactional;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.CascadeType;
import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Transactional
public class Categorie implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idCategorie;

	@NonNull
	@Size(min=4,max=80)
	private String nomCategorie;
	private String description;
	private String nomPhoto;
	@Lob
	private byte[] photo;
	@OneToMany ( mappedBy="categorie" )
	private Collection<Produit> produits = new ArrayList<Produit>();

	// constructors 
	public Categorie() {
		super();
	}

	// getters & setters 

	public Categorie( String nomCategorie, String description, String nomPhoto,
			byte[] photo, Collection<Produit> produits) {
		super();
		this.nomCategorie = nomCategorie;
		this.description = description;
		this.nomPhoto = nomPhoto;
		this.photo = photo;
		this.produits = produits;
	}

	public Categorie( String nomCategorie, String description, Collection<Produit> produits) {
		super();
		this.nomCategorie = nomCategorie;
		this.description = description;
		this.produits = produits;
	}
	
	public Categorie( String nomCategorie, String description) {
		super();
		this.nomCategorie = nomCategorie;
		this.description = description;
		
	}
	
	public String getNomCategorie() {
		return nomCategorie;
	}
	public Long getIdCategorie() {
		return idCategorie;
	}
	public void setIdCategorie(Long idCategorie) {
		this.idCategorie = idCategorie;
	}
	public void setNomCategorie(String nomCategorie) {
		this.nomCategorie = nomCategorie;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getNomPhoto() {
		return nomPhoto;
	}
	public void setNomPhoto(String nomPhoto) {
		this.nomPhoto = nomPhoto;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	@JsonIgnore
	public Collection<Produit> getProduits() {
		return produits;
	}
	public void setProduits(Collection<Produit> produits) {
		this.produits = produits;
	}

}
