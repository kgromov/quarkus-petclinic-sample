package org.acme.rest;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import org.acme.model.Owner;
import org.acme.model.Pet;
import org.acme.model.PetForm;
import org.acme.model.PetType;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/")
public class PetsResource {

    @Inject
    Template pet;

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("getPet")
    public TemplateInstance getPet(@QueryParam("ownerId") Long ownerId,
                                   @QueryParam("petId") Long petId) {
        return pet.data("active", "owners")
                    .data("owner", Owner.findById(ownerId))
                    .data("pet", (petId != null ? Pet.findById(petId) : petId));
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Transactional
    @Path("addPet")
    public Response addPet(@MultipartForm PetForm petForm,
                           @QueryParam("ownerId") Long ownerId) {
        Pet newPet = petForm.addPet();
        newPet.setOwner(Owner.findById(ownerId));
        newPet.setPetType(PetType.findByName(petForm.type));
        newPet.persist();
        return Response.status(301)
                    .location(URI.create("/owners?id=" + ownerId))
                    .build();
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Transactional
    @Path("editPet")
    public Response editOwner(@MultipartForm PetForm petForm,
                              @QueryParam("ownerId") Long ownerId,
                              @QueryParam("petId")Long petId) {
        Pet existingPet = Pet.findById(petId);
        existingPet = petForm.editPet(existingPet);
        existingPet.setOwner(Owner.findById(ownerId));
        existingPet.setPetType(PetType.findByName(petForm.type));
        return Response.status(301)
            .location(URI.create("/owners?id=" + ownerId))
            .build();
    }

}