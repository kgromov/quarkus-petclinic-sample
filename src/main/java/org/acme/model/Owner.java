package org.acme.model;

import java.util.List;

import jakarta.persistence.Cacheable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity(name="owners")
@Cacheable
public class Owner extends PanacheEntityBase {

    @Id
    @SequenceGenerator(
            name = "ownersSequence",
            sequenceName = "owners_id_seq",
            allocationSize = 1,
            initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ownersSequence")
    public Long id;

    @Column(name = "first_name")
	@NotBlank
	public String firstName;

	@Column(name = "last_name")
	@NotBlank
	public String lastName;
    
    public String address;
    public String city;

    @NotBlank
	@Digits(fraction = 0, integer = 10)
    public String telephone;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    public List<Pet> pets;
    
    public Long getId(){
        return id;
    }

}