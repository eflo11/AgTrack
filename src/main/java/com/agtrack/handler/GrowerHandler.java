package com.agtrack.handler;

import com.agtrack.model.Grower;
import com.agtrack.service.ServiceGrower;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("/grower")
public class GrowerHandler {

    @GET
    @Path("/{growerNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGrower(@PathParam("growerNumber") int growerNumber) {
        Response response;
        try {
             Grower grower = ServiceGrower.getGrower(growerNumber);
            response = Response.ok().entity(grower).build();
        } catch (SQLException e) {
            e.printStackTrace();
            response = Response.serverError().entity(e.getMessage()).type(MediaType.TEXT_PLAIN).build();
        }

        return response;
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Grower grower){
        Response response;

        try{

            ServiceGrower.insertGrower(grower);
            response = Response.ok().build();

        }catch (Exception e){
            response = Response.serverError().entity(e.getMessage()).type(MediaType.TEXT_PLAIN).build();
        }

        return response;
    }


    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(Grower grower) {
        Response response;

        try {

            ServiceGrower.updateGrower(grower);
            response = Response.ok().build();

        } catch (Exception e) {
            response = Response.serverError().entity(e.getMessage()).type(MediaType.TEXT_PLAIN).build();
        }

        return response;
    }


    @DELETE
    @Path("/{growerNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("growerNumber") int growerNumber) {
        Response response;

        try {
            ServiceGrower.deleteGrower(growerNumber);
            response = Response.ok().build();

        } catch (Exception e) {
            response = Response.serverError().entity(e.getMessage()).type(MediaType.TEXT_PLAIN).build();
        }

        return response;
    }

}
