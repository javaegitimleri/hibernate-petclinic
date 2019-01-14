package com.javaegitimleri.petclinic.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.NaturalId;

@NamedQuery(name="findPetsByName",query="from Pet p where p.name like :name")
@Entity
@Table(name="t_pet")
@SequenceGenerator(name="seqGen",sequenceName="pet_seq")
public class Pet extends BaseEntity {
	
	@NaturalId
	@Basic(optional=false)
	@Column(name="pet_name",nullable=false)
	private String name;
	
	@Column(name="birth_date")
	@Temporal(TemporalType.DATE)
	private Date birthDate;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="type_id")
	private PetType type;
	
	@ManyToOne
	@JoinColumn(name="owner_id")
	private Owner owner;
	
	@OneToMany(orphanRemoval=true,fetch=FetchType.LAZY,cascade=javax.persistence.CascadeType.ALL)
	@JoinColumn(name="pet_id")
	@OrderColumn(name="visit_order")
	private List<Visit> visits = new ArrayList<>();
	
	@OneToMany(mappedBy="pet")
	@MapKey(name="filePath")
	@Cascade(CascadeType.DELETE_ORPHAN)
	private Map<String, Image> imagesByFilePath = new HashMap<>();
	
	public Pet() {
		
	}
	
	public Pet(String name, Date birthDate) {
		this.name = name;
		this.birthDate = birthDate;
	}
	
	

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public PetType getType() {
		return type;
	}

	public void setType(PetType type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}
	
	

	public List<Visit> getVisits() {
		return visits;
	}

	public void setVisits(List<Visit> visits) {
		this.visits = visits;
	}

	public Map<String, Image> getImagesByFilePath() {
		return imagesByFilePath;
	}

	public void setImagesByFilePath(Map<String, Image> imagesByFilePath) {
		this.imagesByFilePath = imagesByFilePath;
	}

	@Override
	public String toString() {
		return "Pet [id=" + getId() + ", name=" + name + ", birthDate=" + birthDate + "]";
	}
}
