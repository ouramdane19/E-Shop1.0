package org.sid.dao;



import org.sid.entities.LigneCommande;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface LigneCommandeRepository extends JpaRepository<LigneCommande, Long> {
	
	@Query("select lc from LigneCommande lc where lc.idLigneCommande like :x")
	public LigneCommande editLC(@Param("x") Long id);
	
	@Query("select  sum(l.prix) from LigneCommande l ")
	public double  total();
	
//	@Query("SELECT SUM(prix) AS prix_total FROM LigneCommande WHERE commande.idCommande = 1")
//	public double  total();
}
