package org.acme.rest;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import org.acme.model.Owner;
import org.acme.model.Pet;
import org.acme.model.Visit;
import org.acme.model.VisitForm;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/")
public class VisitsResource {

    @Inject
    Template visit;

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("getVisit")
    public TemplateInstance getPet(@QueryParam("ownerId") Long ownerId,
                                   @QueryParam("petId") Long petId) {
        return visit.data("active", "owners")
                    .data("owner", Owner.findById(ownerId))
                    .data("pet", Pet.findById(petId));
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Transactional
    @Path("addVisit")
    public Response addPet(@MultipartForm VisitForm visitForm,
                           @QueryParam("ownerId") Long ownerId,
                           @QueryParam("petId") Long petId) {
        Visit newVisit = visitForm.addVisit();
        newVisit.setPet(Pet.findById(petId));
        newVisit.persist();
        return Response.status(301)
                    .location(URI.create("/owners?id=" + ownerId))
                    .build();
    }
}