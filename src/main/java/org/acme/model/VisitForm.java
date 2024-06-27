package org.acme.model;

import org.jboss.resteasy.reactive.RestForm;

import java.time.LocalDate;

public class VisitForm {

    public @RestForm LocalDate date;
    public @RestForm String description;

    public Visit addVisit() {
        Visit newVisit = new Visit();
        newVisit.date = date;
        newVisit.description = description;
        return newVisit;
    }

}
