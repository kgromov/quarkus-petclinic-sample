package org.acme.rest;

import java.net.URI;
import java.util.List;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import org.acme.model.Owner;
import org.acme.model.OwnerForm;
import org.acme.repository.OwnerRepository;
import org.jboss.resteasy.reactive.MultipartForm;

import static java.util.Collections.emptyList;
import static java.util.Optional.ofNullable;

@Path("/")
public class OwnersResource {

    @Inject
    OwnerRepository ownerRepository;

    @Inject
    Template owners;

    @Inject
    Template editOwner;

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("owners")
    public TemplateInstance findOwners(@QueryParam("id") Long id) {
        return owners.data("active", "owners")
                .data("lastName", null)
                .data("owners", ofNullable(id).map(ownerId -> List.of(Owner.findById(ownerId))).orElse(emptyList()));
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("find")
    public TemplateInstance findByLastName(@QueryParam("lastName") String lastName) {
        return owners.data("active", "owners")
                .data("lastName", lastName)
                .data("owners", ownerRepository.findByLastName(lastName));
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Transactional
    @Path("addOwner")
    public Response addOwner(@MultipartForm OwnerForm ownerForm) {
        Owner newOwner = ownerForm.addOwner();
        newOwner.persist();
        return Response.status(301)
                .location(URI.create("/owners?id=" + newOwner.getId()))
                .build();
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("getOwner")
    public TemplateInstance editOwner(@QueryParam("ownerId") Long ownerId) {
        // TODO: add validation on not found by id
        return editOwner.data("active", "owners")
                .data("owner", ((ownerId == null) ? null : Owner.findById(ownerId)));
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Transactional
    @Path("editOwner")
    public Response editOwner(@MultipartForm OwnerForm ownerForm, @QueryParam("ownerId") Long ownerId) {
        Owner existingOwner = Owner.findById(ownerId);
        existingOwner = ownerForm.editOwner(existingOwner);
        return Response.status(301)
                .location(URI.create("/owners?id=" + ownerId))
                .build();
    }

}