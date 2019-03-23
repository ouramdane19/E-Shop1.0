package org.sid.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Panier implements Serializable {

	private Map<Long, LigneCommande> items = new HashMap<Long, LigneCommande>();
	
	// ajouter des articles ou items 
	public void addItem(Produit p ,int quantite) {
		LigneCommande lc =items.get(p.getId());
		if(lc==null) {
			LigneCommande art = new LigneCommande();
			art.setProduit(p);
			art.setQuantite(quantite);
			art.setPrix(p.getPrix());
			items.put(p.getId(), art);
		}else {
			lc.setQuantite(lc.getQuantite()+quantite);
		}
	
	}
	
	// recupération des items 
	public Collection<LigneCommande> getItems(){
		return items.values();
	}
	
	// total de la commande 
	public double getTotal() {
		double total = 0 ;
		for (LigneCommande lc : items.values()) {
			total += lc.getPrix()*lc.getQuantite();
		}
		
		return total ; 
	}
	
	// nombre de produits dans un panier
	public int getSize() {
		return items.size();
	}
	
	// supprimer un item
	public void delteItem(Long idProduit) {
		items.remove(idProduit);
	}
	
}
