package com.javaegitimleri.petclinic.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

@Entity
@Table(name="t_person")
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Person extends BaseEntity {
	@NaturalId
	@Column(name="first_name")
	private String firstName;
	
	@NaturalId
	@Column(name="last_name")
	private String lastName;
	
	@ElementCollection
	@CollectionTable(name="t_person_email",joinColumns=@JoinColumn(name="person_id"))
	private Set<Email> emails = new HashSet<>();
	
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

	public Set<Email> getEmails() {
		return emails;
	}

	public void setEmails(Set<Email> emails) {
		this.emails = emails;
	}

	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", lastName=" + lastName + ", getId()=" + getId() + "]";
	}
}
