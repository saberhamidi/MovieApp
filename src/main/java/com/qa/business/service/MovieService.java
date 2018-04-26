package com.qa.business.service;

import com.qa.business.repository.IMovieRepository;

import javax.inject.Inject;

public class MovieService implements IMovieService {

    @Inject
    private IMovieRepository repository;


    @Override
    public String getAllMovies() {
        return repository.getAllMovies();
    }

    @Override
    public String getMovie(Long id) {
        return repository.getMovie(id);
    }

    @Override
    public String addMovie(String movie) {
        return repository.addMovie(movie);
    }

    @Override
    public String deleteMovie(Long id) {
        return repository.deleteMovie(id);
    }

    @Override
    public String updateMovie(String movie) {
        return repository.updateMovie(movie);
    }
}
