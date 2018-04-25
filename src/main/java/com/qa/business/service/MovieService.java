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
}
