package com.javaegitimleri.petclinic.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "t_vet")
public class Vet extends Person {
	@Column(name = "works_full_time")
	private Boolean worksFullTime;

	@ManyToMany
	@JoinTable(name = "t_vet_specialty", joinColumns = @JoinColumn(name = "vet_id"), inverseJoinColumns = @JoinColumn(name = "specialty_id"))
	private Set<Specialty> specialties = new HashSet<>();

	public Boolean getWorksFullTime() {
		return worksFullTime;
	}

	public void setWorksFullTime(Boolean worksFullTime) {
		this.worksFullTime = worksFullTime;
	}
}
