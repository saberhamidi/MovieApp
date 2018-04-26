package com.qa.business.repository;

import com.qa.persistence.domain.Movie;
import com.qa.util.JSONUtil;
import org.apache.log4j.Logger;

import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

@Alternative
public class MovieMapRepository implements IMovieRepository {

    private static final Logger LOGGER = Logger.getLogger(MovieDBRepository.class);

    private Map<Long, Movie> movies = new HashMap<Long, Movie>();

    @Inject
    private JSONUtil util;

    @Override
    public String getAllMovies() {
        return util.getJSONForObject(movies);
    }

    @Override
    public String getMovie(Long id) {

        return util.getJSONForObject(movies.get(id));
    }

    @Override
    public String addMovie(String movie) {
        Movie newMovie = util.getObjectForJSON(movie,Movie.class);
        movies.put(newMovie.getId(), newMovie);
        return "{\"message\":\"Movie successfully added\"}";
    }

    @Override
    public String deleteMovie(Long id) {
        Movie movieToBeDeleted = movies.get(id);
        if(movieToBeDeleted == null){
            return "{\"message\":\"Movie could't be found!\"}";
        }
        else {
            movies.remove(id);
            return "{\"message\":\"Movie successfully removed!\"}";
        }
    }

    @Override
    public String updateMovie(String movie) {
        Movie updatedMovie = util.getObjectForJSON(movie, Movie.class);
        Movie movieFound = movies.get(updatedMovie.getId());
        if(movieFound == null){
            return "{\"message\":\"Movie could't be found!\"}";
        }
        else {
            movies.put(updatedMovie.getId(), updatedMovie);
            return "{\"message\":\"Movie successfully updated!\"}";
        }
    }
}
