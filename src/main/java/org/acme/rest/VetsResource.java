package org.acme.rest;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import org.acme.model.Vet;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("vets")
public class VetsResource {

    @Inject
    Template vets;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getVets() {
        return vets.data("active", "vets")
                .data("vets", Vet.listAll());
    }
}