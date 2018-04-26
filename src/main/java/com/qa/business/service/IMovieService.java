package com.qa.business.service;

public interface IMovieService {

    String getAllMovies();
    String getMovie(Long id);
    String addMovie(String movie);
    String deleteMovie(Long id);

}
