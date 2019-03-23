package org.sid.dao;

import org.sid.entities.Categorie;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {
	@Query("select c from Categorie c where c.nomCategorie like :x")
	public Page<Categorie> chercher(@Param("x")String mc,Pageable pageable); 
	
	@Query("select c from Categorie c where c.idCategorie like :x")
	public Categorie editCategorie(@Param("x") Long id);
	

}
