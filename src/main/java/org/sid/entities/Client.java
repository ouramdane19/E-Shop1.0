package org.sid.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.transaction.Transactional;

import lombok.Data;

@Data
@Entity
@Transactional

public class Client implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idClient ;
	private String nomClient;
	private String adresse;
	private String email;
	private String tel; 

	@OneToMany(mappedBy="client")
	private Collection<Commande> commandes;
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "userId")
	private User users;
	
	// Constructors
	public Client() {
		super();
		
	}
	
	 public Client(String nomClient, String adresse, String email, String tel) {
		super();
		this.nomClient = nomClient;
		this.adresse = adresse;
		this.email = email;
		this.tel = tel;
	
	}

}