package com.qa.business.repository;

import com.qa.persistence.domain.Movie;
import com.qa.util.JSONUtil;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;

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
}
