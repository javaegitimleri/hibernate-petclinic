package com.javaegitimleri.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;

@BatchSize(size=10)
@Entity
@Table(name="t_pet_type")
public class PetType extends BaseEntity {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
