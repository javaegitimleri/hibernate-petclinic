package com.javaegitimleri.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="t_vet")
public class Vet extends Person {
	@Column(name="works_full_time")
	private Boolean worksFullTime;

	public Boolean getWorksFullTime() {
		return worksFullTime;
	}

	public void setWorksFullTime(Boolean worksFullTime) {
		this.worksFullTime = worksFullTime;
	}
}
