package com.qa.integration;

import com.qa.business.service.IMovieService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
}
