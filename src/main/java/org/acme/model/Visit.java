package org.acme.model;

import java.time.LocalDate;

import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity(name="visits")
@Cacheable
public class Visit extends PanacheEntity {
  
	@ManyToOne
	@JoinColumn(name = "pet_id")
	public Pet pet;

    @Column(name = "visit_date")
	public LocalDate date;

	@NotBlank
	public String description;

	public Pet getPet() {
		return this.pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}
	
}