package com.javaegitimleri.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class Address {
	
	@Column(table="t_address",name="phone_type")
	@Enumerated(EnumType.STRING)
	private PhoneType phoneType;
	
	@Column(table="t_address")
	private String street;
	
	@Column(table="t_address")
	private String phone;
	
	@ManyToOne
	@JoinColumn(name="city_id",table="t_address",foreignKey=@ForeignKey(name="fk_address_city"))
	private City city;
	
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
