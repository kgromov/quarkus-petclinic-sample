package org.acme.model;

import java.time.LocalDate;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity(name="visits")
@Cacheable
public class Visit extends PanacheEntityBase {

	@Id
	@SequenceGenerator(
			name = "visitsSequence",
			sequenceName = "visits_id_seq",
			allocationSize = 1
    )
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "visitsSequence")
	public Long id;
  
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