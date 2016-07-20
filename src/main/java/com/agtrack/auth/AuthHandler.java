package com.agtrack.auth;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class AuthHandler {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response getLot(UserPrincipal userPrincipal) {
        Response response;

        try {
            if (userPrincipal.getName() == null || "".equals(userPrincipal.getName())
                    || userPrincipal.getPassword() == null || "".equals(userPrincipal.getPassword())) {
                throw new IllegalArgumentException("Incorrect username or password");
            }

            if (ServiceAuth.authenticate(userPrincipal.getName(), userPrincipal.getPassword())) {

                String token = ServiceAuth.getToken(userPrincipal);
                response = Response.ok().entity(token).build();


            } else {
                throw new IllegalArgumentException("Incorrect username or password");
            }


        } catch (IllegalArgumentException e){
            response = Response.status(Response.Status.FORBIDDEN)
                    .entity(e.getMessage()).type(MediaType.TEXT_PLAIN).build();
        } catch (Exception e) {
            response = Response.serverError()
                    .entity(e.getMessage()).type(MediaType.TEXT_PLAIN).build();
        }

        return response;
    }


}
