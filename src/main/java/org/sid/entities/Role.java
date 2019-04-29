package org.sid.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Data;
@Data
@Entity
public class Role {

	private @Id @GeneratedValue(strategy=GenerationType.AUTO) Long id;
	@ManyToMany(mappedBy = "roles")
    private Collection<User> users;
	
	@ManyToMany
    @JoinTable(name = "roles_privileges", joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "id"))
    private Collection<Privilege> privileges;

	private Long idUser;
	private String RoleName;
	
	public Role() {
		super();
		
	}
}
