package org.acme.model;

import org.jboss.resteasy.reactive.RestForm;

public class OwnerForm {

//    public @RestForm("id") String id;
    public @RestForm("firstName") String firstName;
    public @RestForm("lastName") String lastName;
    public @RestForm("address") String address;
    public @RestForm("city") String city;
    public @RestForm("telephone") String telephone;

    public Owner addOwner() {
        Owner newOwner = new Owner();
        newOwner.firstName = firstName;
        newOwner.lastName = lastName;
        newOwner.address = address;
        newOwner.city = city;
        newOwner.telephone = telephone;
        return newOwner;
    }

    public Owner editOwner(Owner existingOwner) {
        existingOwner.firstName = firstName;
        existingOwner.lastName = lastName;
        existingOwner.address = address;
        existingOwner.city = city;
        existingOwner.telephone = telephone;
        return existingOwner;
    }
    
}
