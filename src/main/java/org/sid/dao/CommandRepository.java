package org.sid.dao;




import org.sid.entities.Commande;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface CommandRepository extends JpaRepository<Commande, Long> {
	
	@Query("select cl from Client cl where cl.nomClient like :x")
	public Page<Commande> chercherCommande(@Param("x")String mc,Pageable pageable); 
	
	@Query("select cl from Client cl where cl.idClient like :x")
	public Commande editCommande(@Param("x") Long id);
}
