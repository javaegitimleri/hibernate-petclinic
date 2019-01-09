package com.javaegitimleri.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="t_image")
public class Image extends BaseEntity {
	
	@Column(name="file_path")
	private String filePath;
	
	@Column(name="height")
	private int height;
	
	@Column(name="width")
	private int width;
	
	@ManyToOne
	@JoinColumn(name="pet_id")
	private Pet pet;

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
}
