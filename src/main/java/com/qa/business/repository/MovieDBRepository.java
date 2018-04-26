package com.qa.business.repository;

import com.qa.persistence.domain.Movie;
import com.qa.util.JSONUtil;
import org.apache.log4j.Logger;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.Collection;

@Default
@Transactional(Transactional.TxType.SUPPORTS)
public class MovieDBRepository implements IMovieRepository {

    private static final Logger LOGGER = Logger.getLogger(MovieDBRepository.class);

    @PersistenceContext(unitName = "primary")
    private EntityManager manager;

    @Inject
    private JSONUtil util;

    @Override
    public String getAllMovies() {
        LOGGER.info("MovieDBRepository getAllMovies");
        Query query = manager.createQuery("SELECT m from Movie m");
        Collection<Movie> movies = (Collection<Movie>) query.getResultList();
        return util.getJSONForObject(movies);
    }

    @Override
    public String getMovie(Long id) {
        Movie movie = findMovie(id);
        if(movie == null){
            return "{\"message\":\"Movie could not be found\"}";
        }
        else return util.getJSONForObject(movie);
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public String addMovie(String movie) {
        Movie movieToBeAdded = util.getObjectForJSON(movie, Movie.class);
        manager.persist(movieToBeAdded);
        return "{\"message\":\"Movie cureated\"}";
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public String deleteMovie(Long id) {
        Movie movieToBeDeleted = this.findMovie(id);
        if(movieToBeDeleted == null){
            return "{\"message\":\"Movie could't be found!\"}";
        }
        else {
            manager.remove(movieToBeDeleted);
            return "{\"message\":\"Movie successfully deleted!\"}";
        }
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public String updateMovie(String movie) {
        Movie updatedMovie = util.getObjectForJSON(movie, Movie.class);
        Movie foundMovie = this.findMovie(updatedMovie.getId());
        if(foundMovie == null){
            return "{\"message\":\"Movie could't be found!\"}";
        }
        else {
            manager.merge(updatedMovie);
            return "{\"message\":\"Movie successfully updated!\"}";
        }
    }

    private Movie findMovie(Long id) {
        return manager.find(Movie.class, id);
    }
}
