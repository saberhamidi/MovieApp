package com.qa.integration;

import com.qa.business.service.IMovieService;

import javax.inject.Inject;
import javax.validation.constraints.Min;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("movie")
public class MovieEndPoint {

    @Inject
    private IMovieService service;

    @GET
    @Path("/json")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllMovies(){

        return service.getAllMovies();
    }

    @GET
    @Path("/json/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getMovie(@PathParam("id") @Min(1) Long id){
        return  service.getMovie(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/json")
    public String addMovie(String movie){
        return  service.addMovie(movie);
    }

    @DELETE
    @Path("/json/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteMovie(@PathParam("id") @Min(1) Long id){
        return service.deleteMovie(id);
    }

    @PUT
    @Path("/json")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String updateMovie(String movie){
       return service.updateMovie(movie);
    }
}
