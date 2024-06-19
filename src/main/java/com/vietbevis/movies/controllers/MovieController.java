package com.vietbevis.movies.controllers;

import com.vietbevis.movies.controllers.requestMovie.MovieCreationRequest;
import com.vietbevis.movies.fetchapi.ApiService;
import com.vietbevis.movies.responses.ResponseObject;
import com.vietbevis.movies.responses.movie.MovieChildResponse;
import com.vietbevis.movies.responses.movie.MovieResponse;
import com.vietbevis.movies.services.movie.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/movies")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;
    private final ApiService apiService;

    @PostMapping("")
    public ResponseEntity<?> createMovie(@Valid @RequestBody MovieCreationRequest request) {
        MovieResponse movieResponse = movieService.createMovie(request.getMovie(), request.getEpisodes());
        return ResponseObject.getBuilder()
                .setMessage("Movie created successfully")
                .setData(movieResponse)
                .build();
    }

    @GetMapping("/{slug}")
    public ResponseEntity<?> getMovie(@PathVariable String slug) {
        MovieResponse movieResponse = movieService.getMovie(slug);
        return ResponseObject.getBuilder()
                .setMessage("Movie retrieved successfully")
                .setData(movieResponse)
                .build();
    }

    @GetMapping("")
    public ResponseEntity<?> getMovies() {
        apiService.fetchApiData();
        List<MovieChildResponse> movieListResponse = movieService.getMovies();
        return ResponseObject.getBuilder()
                .setMessage("Movies retrieved successfully")
                .setData(movieService.getMovies())
                .build();
    }

    @PutMapping("/{slug}")
    public ResponseEntity<?> updateMovie(@PathVariable String slug, @Valid @RequestBody MovieCreationRequest request) {
        MovieResponse movieResponse = movieService.updateMovie(slug, request.getMovie(), request.getEpisodes());
        return ResponseObject.getBuilder()
                .setMessage("Movie updated successfully")
                .setData(movieResponse)
                .build();
    }
}
