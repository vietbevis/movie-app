package com.vietbevis.movies.services.init_movie_data;

import com.vietbevis.movies.controllers.requestMovie.MovieCreationRequest;
import com.vietbevis.movies.exceptions.commons.DataExistsException;
import com.vietbevis.movies.repositories.MovieRepository;
import com.vietbevis.movies.responses.movie.MovieChildResponse;
import com.vietbevis.movies.responses.movie.MovieResponse;
import com.vietbevis.movies.responses.movie.ResponseFetch;
import com.vietbevis.movies.services.movie.MovieService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@CommonsLog
@Service
@RequiredArgsConstructor
public class MovieDataInit {

    private final ApiService apiService;
    private final MovieService movieService;
    private final MovieRepository movieRepository;
    private static final Logger logger = Logger.getLogger(MovieDataInit.class.getName());

    private int count = 0;
    private final List<String> slugsError = new ArrayList<>();


//    public ResponseFetch fetchMovies() {
//
//
//    }

    @PostConstruct
    public void fetchAndProcessPages() throws DataExistsException {
        for (int pageIndex = 1; pageIndex <= 100; pageIndex++) {
            try {
                // Fetch the movie data for the given page index
                ResponseFetch responseFetch = apiService.fetchMovieData(pageIndex);
                // Check if the status is true
                if (responseFetch.isStatus()) {
                    List<MovieChildResponse> movies = responseFetch.getItems();
                    for (int i = 0; i < movies.size(); i++) {
                        MovieChildResponse movie = movies.get(i);
                        logger.log(Level.INFO, "Page {0} - Movie {1}: {2}", new Object[]{pageIndex, i + 1, movie});
                        String slug = movie.getSlug();
                        logger.log(Level.INFO, "Fetched details for movie with slug {0} ", slug);
//                        MovieCreationRequest movieCreationRequest = apiService.fetchMovieDetails(slug);
                        findMovieBySlug(slug);
//                        logger.log(Level.INFO, "Fetched details for movie with slug {0}: {1}", new Object[]{slug, movieCreationRequest});

                    }
                } else {
                    logger.log(Level.WARNING, "Failed to fetch movies for page {0}: Status is false", pageIndex);
                }
            } catch (DataExistsException e) {
                logger.log(Level.SEVERE, "DataExistsException for page " + pageIndex + ": " + e.getMessage(), e);
                throw e;
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Exception while processing page " + pageIndex + ": " + e.getMessage(), e);
            }
        }
    }

    private void findMovieBySlug(String slug) {
        try {
            MovieCreationRequest movieCreationRequest = apiService.fetchMovieDetails(slug);
            if (movieCreationRequest.isStatus()) {
                MovieResponse movieResponse = movieService.createMovie(movieCreationRequest);
                count++;
                logger.info(count + ", Tải phim: " + movieResponse.getName() + " thành công.");
            }
        } catch (Exception e) {
            slugsError.add(slug);
            logger.severe("Lỗi tải phim: " + slug + ". " + e.getMessage());
        }
    }
}
