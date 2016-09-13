package com.agtrack.handler;

import com.agtrack.model.Crop;
import com.agtrack.service.ServiceCrop;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/crop")
public class CropHandler {

    @GET
    @Path("/all/{growerNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response crops(@PathParam("growerNumber") int growerNumber) {
        Response response;

        try {

            List<Crop> crops = ServiceCrop.getCrops(growerNumber);
            response = Response.ok().entity(crops).build();

        }catch (Exception e){
            response = Response.serverError().entity(e.getMessage()).type(MediaType.TEXT_PLAIN).build();
        }

        return response;
    }

    @GET
    @Path("/{cropId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCrop(@PathParam("cropId") int cropId){
        Response response;

        try {

            Crop crop = ServiceCrop.getCrop(cropId);
            response = Response.ok().entity(crop).build();

        }catch (Exception e){
            response = Response.serverError().entity(e.getMessage()).type(MediaType.TEXT_PLAIN).build();
        }

        return response;
    }

    @POST
    @Path("/create/{growerId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@PathParam("growerId") int growerId, Crop crop){
        Response response;

        try{

            ServiceCrop.insertCrop(growerId, crop);
            response = Response.ok().build();

        }catch (Exception e){
            response = Response.serverError().entity(e.getMessage()).type(MediaType.TEXT_PLAIN).build();
        }

        return response;
    }

    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(Crop crop){
        Response response;
        try{

            ServiceCrop.deleteCrop(crop.getId());
            ServiceCrop.insertCrop(crop.getGrowerId(), crop);
            response = Response.ok().build();

        }catch (Exception e){
            response = Response.serverError().entity(e.getMessage()).type(MediaType.TEXT_PLAIN).build();
        }

        return response;
    }

    @DELETE
    @Path("/{cropId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("cropId") int cropId) {
        Response response;

        try {
            ServiceCrop.deleteCrop(cropId);
            response = Response.ok().build();

        } catch (Exception e) {
            response = Response.serverError().entity(e.getMessage()).type(MediaType.TEXT_PLAIN).build();
        }

        return response;
    }
}
