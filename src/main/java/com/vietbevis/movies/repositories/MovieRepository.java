package com.vietbevis.movies.repositories;

import com.vietbevis.movies.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, String> {

    Movie findMovieBySlug(String slug);

    Movie findMovieByName(String id);
}
