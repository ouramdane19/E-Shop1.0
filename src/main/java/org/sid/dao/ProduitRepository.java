package org.sid.dao;



import java.util.List;

import org.sid.entities.Produit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ProduitRepository extends JpaRepository<Produit, Long>   {
		
	@Query("select p from Produit p where p.designation like :x")
	public Page<Produit> chercher(@Param("x")String mc,Pageable pageable); 
	
	@Query("select p from Produit p where p.id like :x")
	public Produit editP(@Param("x") Long id);
	

	@Query("select p from Produit p where p.categorie.idCategorie like :x")
	//@Query("select p from Produit p where p.cat like :x")
	public List<Produit> produitParCategorie(@Param("x") Long idCat);
	
	
	//@Query("select sum (p.prix) from Produit p ")
	//public double  total();

}
