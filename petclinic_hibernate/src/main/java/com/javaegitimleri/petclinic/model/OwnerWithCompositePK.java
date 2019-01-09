package com.javaegitimleri.petclinic.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_owner_with_composite_pk")
public class OwnerWithCompositePK {
	
	@Embeddable
	public static class OwnerId implements Serializable {
		
		@Column(name="first_name",nullable=false)
		private String firstName;
		
		@Column(name="last_name",nullable=false)
		private String lastName;

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
	}
	
	@Id
	private OwnerId id;

	public OwnerId getId() {
		return id;
	}

	public void setId(OwnerId id) {
		this.id = id;
	}
}
