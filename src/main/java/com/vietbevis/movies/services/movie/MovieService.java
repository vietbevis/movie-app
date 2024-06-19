package com.vietbevis.movies.services.movie;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vietbevis.movies.dtos.MovieDTO;
import com.vietbevis.movies.exceptions.commons.DataExistsException;
import com.vietbevis.movies.exceptions.commons.DataNotFoundException;
import com.vietbevis.movies.exceptions.commons.ToJsonException;
import com.vietbevis.movies.mapper.MovieMapper;
import com.vietbevis.movies.models.*;
import com.vietbevis.movies.repositories.*;
import com.vietbevis.movies.responses.movie.MovieChildResponse;
import com.vietbevis.movies.responses.movie.MovieResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService implements IMovieService {
    private final MovieRepository movieRepository;
    private final ActorRepository actorRepository;
    private final DirectorRepository directorRepository;
    private final CategoryRepository categoryRepository;
    private final CountryRepository countryRepository;

    private final MovieMapper movieMapper;
    private final ObjectMapper objectMapper;

    @Override
    public MovieResponse getMovie(String slug) {
        Movie movie = movieRepository.findMovieBySlug(slug);
        if (movie == null) {
            throw new DataNotFoundException("Movie not found");
        }
        MovieResponse movieResponse = movieMapper.toMovieResponse(movie);
        try {
            List<?> episodes = objectMapper.readValue(movie.getEpisodes(), new TypeReference<>() {
            });
            movieResponse.setEpisodes(episodes);
        } catch (JsonProcessingException e) {
            throw new ToJsonException("Cannot convert to json: " + e.getMessage());
        }
        return movieResponse;
    }

    @Override
    public MovieResponse createMovie(MovieDTO movieDTO, List<?> episode) {
        Movie movieExistsName = movieRepository.findMovieByName(movieDTO.getSlug());
        Movie movieExistsSlug = movieRepository.findMovieBySlug(movieDTO.getSlug());
        if (movieExistsName != null || movieExistsSlug != null) {
            throw new DataExistsException("Movie already exists");
        }
        Movie movie = movieMapper.toMovie(movieDTO);
        movie.setCreatedAt(LocalDate.now());
        return CheckDetailsMovie(movieDTO, episode, movie);
    }

    @Override
    public MovieResponse updateMovie(String id, MovieDTO movieDTO, List<?> episode) {
        Movie movieExistsName = movieRepository.findMovieByName(movieDTO.getSlug());
        Movie movieExistsSlug = movieRepository.findMovieBySlug(movieDTO.getSlug());
        if (movieExistsName == null || movieExistsSlug == null) {
            throw new DataExistsException("Movie not exists");
        }
        Movie movie = movieMapper.toMovie(movieDTO);
        CheckDetailsMovie(movieDTO, episode, movie);
        return CheckDetailsMovie(movieDTO, episode, movie);
    }

    @Override
    public MovieResponse deleteMovie(String id) {
        return null;
    }

    @Override
    public List<MovieChildResponse> getMovies() {
        return movieRepository.findAll().stream().map(movieMapper::toMovieChildResponse).toList();
    }

    private MovieResponse CheckDetailsMovie(MovieDTO movieDTO, List<?> episode, Movie movie) {
        movie.setUpdatedAt(LocalDate.now());

        movie.setActors(new ArrayList<>());
        movieDTO.getActors().stream().map(actorDTO -> {
            Actor actor = actorRepository.findByName(actorDTO);
            if (actor == null) {
                actor = Actor.builder()
                        .name(actorDTO)
                        .build();
                actorRepository.save(actor);
            }
            return actor;
        }).forEach(movie.getActors()::add);

        movie.setDirectors(new ArrayList<>());
        movieDTO.getDirectors().stream().map(directorDTO -> {
            Director director = directorRepository.findByName(directorDTO);
            if (director == null) {
                director = Director.builder()
                        .name(directorDTO)
                        .build();
                directorRepository.save(director);
            }
            return director;
        }).forEach(movie.getDirectors()::add);

        movie.setCategories(new ArrayList<>());
        movieDTO.getCategories().stream().map(categoryDTO -> {
            Category category = categoryRepository.findByNameAndSlug(categoryDTO.getName(), categoryDTO.getSlug());
            if (category == null) {
                category = Category.builder()
                        .name(categoryDTO.getName())
                        .slug(categoryDTO.getSlug())
                        .build();
                categoryRepository.save(category);
            }
            return category;
        }).forEach(movie.getCategories()::add);

        movie.setCountries(new ArrayList<>());
        movieDTO.getCountries().stream().map(countryDTO -> {
            Country country = countryRepository.findByNameAndSlug(countryDTO.getName(), countryDTO.getSlug());
            if (country == null) {
                country = Country.builder()
                        .name(countryDTO.getName())
                        .slug(countryDTO.getSlug())
                        .build();
                countryRepository.save(country);
            }
            return country;
        }).forEach(movie.getCountries()::add);

        try {
            String episodeJson = objectMapper.writeValueAsString(episode);
            movie.setEpisodes(episodeJson);
        } catch (JsonProcessingException e) {
            throw new ToJsonException("Cannot convert to json: " + e.getMessage());
        }
        Movie savedMovie = movieRepository.save(movie);
        MovieResponse movieResponse = movieMapper.toMovieResponse(savedMovie);
        movieResponse.setEpisodes(episode);
        return movieResponse;
    }
}
