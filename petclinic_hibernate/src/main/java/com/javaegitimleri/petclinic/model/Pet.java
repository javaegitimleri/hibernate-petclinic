package com.javaegitimleri.petclinic.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="t_pet")
@SequenceGenerator(name="seqGen",sequenceName="pet_seq")
public class Pet {
	@Id
	@GeneratedValue(generator="seqGen")
	private Long id;
	
	@Basic(optional=false)
	@Column(name="pet_name",nullable=false)
	private String name;
	
	@Column(name="birth_date")
	private Date birthDate;
	
	public Pet() {
		
	}
	
	public Pet(String name, Date birthDate) {
		this.name = name;
		this.birthDate = birthDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Pet [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
	}
}
