package com.vietbevis.movies.mapper;

import com.vietbevis.movies.dtos.MovieDTO;
import com.vietbevis.movies.models.Movie;
import com.vietbevis.movies.responses.movie.MovieChildResponse;
import com.vietbevis.movies.responses.movie.MovieResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    @Mapping(target = "episodes", ignore = true)
    MovieResponse toMovieResponse(Movie movie);

    @Mappings({
            @Mapping(target = "directors", ignore = true),
            @Mapping(target = "actors", ignore = true),
            @Mapping(target = "categories", ignore = true),
            @Mapping(target = "countries", ignore = true),
    })
    Movie toMovie(MovieDTO movieDTO);

    MovieChildResponse toMovieChildResponse(Movie movie);

}
