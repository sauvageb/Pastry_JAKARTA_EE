package com.training.pastries.rest;

import com.training.pastries.dao.PastryDao;
import com.training.pastries.dao.entity.Pastry;
import com.training.pastries.dao.factory.DaoFactory;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Definition d'une API REST (De preference, RESTFul) :
 * Collection /api/users
 * *** CRUD ****
 * * CREATE **    HTTP POST /api/users        => ajoute un utilisateur a la collection des utilisateurs
 * * READ **      HTTP GET   /api/users       => retourner les utilisateurs
 * * READ **      HTTP GET   /api/users/1     => retourner l'utilisateur d'id 1
 * * UPDATE **    HTTP PUT /api/users/1       => remplace l'utilisateur 1 par les donnees envoyees dans la requete
 * * UPDATE **    HTTP PATCH /api/users/1     => modifie l'utilisateur 1 grace aux donnees envoyees dans la requete
 * * DELETE **    HTTP DELETE /api/users/1    => supprime l'utilisateur 1 de la collection
 **/
@Path("/pastries")
public class PastryResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<Pastry> pastryList = DaoFactory.getPastryDao().findAll();
        return Response
                .ok(pastryList)
                .build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPastry(@PathParam("id") Long id) {
        Optional<Pastry> pastryOpt = DaoFactory.getPastryDao().findOne(id);
        if (pastryOpt.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(pastryOpt.get()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPastry(Pastry pastry, @Context UriInfo uriInfo) {
        Pastry pastryCreated = DaoFactory.getPastryDao().create(pastry);
        URI location = uriInfo.getAbsolutePathBuilder()
                .path(String.valueOf(pastryCreated.getId()))
                .build();

        return Response.created(location).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePastry(@PathParam("id") Long id, Pastry pastry) {
        PastryDao dao = DaoFactory.getPastryDao();
        Optional<Pastry> pastryOpt = dao.findOne(id);
        if (pastryOpt.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        Pastry p = pastryOpt.get();
        p.setName(pastry.getName());
        p.setDescription(pastry.getDescription());
        p.setRecipeSummary(pastry.getRecipeSummary());
        p.setOrigin(pastry.getOrigin());

        p.setImages(pastry.getImages());

        dao.update(p);

        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletePastry(@PathParam("id") Long id) {
        PastryDao dao = DaoFactory.getPastryDao();
        try {
            dao.deleteById(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (NoSuchElementException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
