package org.acme.model;

import org.jboss.resteasy.reactive.RestForm;

import java.time.LocalDate;

public class PetForm {

    public @RestForm("name") String name;
    public @RestForm("birthDate") LocalDate birthDate;
    public @RestForm("type") String type;

    public Pet addPet() {
        Pet newPet = new Pet();
        newPet.name = name;
        newPet.birthDate = birthDate;
        return newPet;
    }

    public Pet editPet(Pet existingPet) {
        existingPet.name = name;
        existingPet.birthDate = birthDate;
        return existingPet;
    }
}
