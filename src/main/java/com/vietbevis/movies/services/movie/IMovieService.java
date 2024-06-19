package com.vietbevis.movies.services.movie;

import com.vietbevis.movies.dtos.MovieDTO;
import com.vietbevis.movies.responses.movie.MovieChildResponse;
import com.vietbevis.movies.responses.movie.MovieResponse;

import java.util.List;

public interface IMovieService {
    MovieResponse getMovie(String id);

    MovieResponse createMovie(MovieDTO movieDTO, List<?> episodeDTO);

    MovieResponse updateMovie(String id, MovieDTO movieDTO, List<?> episode);

    MovieResponse deleteMovie(String id);

    List<MovieChildResponse> getMovies();
}
