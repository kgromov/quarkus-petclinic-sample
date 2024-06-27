package org.acme.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

@Path("oups")
public class OupsResource {

    @Inject
    Template oups;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance get() {
        return oups.data("active", "oups")
                .data("message", "Expected: resource is used to showcase what happens when an exception is thrown");
    }
    
}