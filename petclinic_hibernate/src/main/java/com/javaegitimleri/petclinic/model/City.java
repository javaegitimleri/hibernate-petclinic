package com.javaegitimleri.petclinic.model;

import javax.persistence.Table;

import javax.persistence.Entity;

@Entity
@Table(name="t_city")
public class City extends BaseEntity {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
