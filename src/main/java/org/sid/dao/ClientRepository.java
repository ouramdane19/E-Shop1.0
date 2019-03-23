package org.sid.dao;


import org.sid.entities.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



public interface ClientRepository extends JpaRepository<Client, Long> {
	@Query("select cl from Client cl where cl.nomClient like :x")
	public Page<Client> chercher(@Param("x")String mc,Pageable pageable); 
	
	@Query("select cl from Client cl where cl.idClient like :x")
	public Client editClient(@Param("x") Long id);
	

}
