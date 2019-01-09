package com.javaegitimleri.petclinic.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="t_visit")
public class Visit extends BaseEntity {
	
	@Column(name="visit_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date visitDate;

	public Date getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}
	
	
	
}
