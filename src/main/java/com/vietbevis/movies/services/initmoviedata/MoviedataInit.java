package com.vietbevis.movies.services.initmoviedata;

import com.vietbevis.movies.fetchapi.ApiService;
import com.vietbevis.movies.fetchapi.ResponseFetch;
import com.vietbevis.movies.models.Movie;
import com.vietbevis.movies.repositories.MovieRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MoviedataInit {

    private final ApiService apiService;
    private final MovieRepository movieRepository;


    @PostConstruct
    @Transactional
    public ResponseFetch fetchMovies() {
        // add try catch later
        try {
            ResponseFetch responseFetch = apiService.fetchMovieData();
            if (responseFetch!=null) {
            }
        }catch (Exception e) {

        }
        ResponseFetch responseFetch = apiService.fetchMovieData();
        Movie movie = new Movie();
        movie.setName(responseFetch.getItems().get(0).getName());
        movieRepository.save(movie);
        return responseFetch;
    }

}
