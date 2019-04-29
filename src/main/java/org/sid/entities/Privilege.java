package org.sid.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;
@Data
@Entity
public class Privilege {

	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long id;
	    private String name;
	    
	    @ManyToMany(mappedBy = "privileges")
	    private Collection<Role> roles;

		public Privilege() {
			super();
			
		}

		public Privilege(final String name) {
			super();
			this.name = name;
		}
   
}
