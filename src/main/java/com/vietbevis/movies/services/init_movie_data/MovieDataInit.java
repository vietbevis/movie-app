package com.vietbevis.movies.services.init_movie_data;

import com.vietbevis.movies.controllers.requestMovie.MovieCreationRequest;
import com.vietbevis.movies.exceptions.commons.DataExistsException;
import com.vietbevis.movies.responses.movie.MovieResponse;
import com.vietbevis.movies.responses.movie.ResponseFetch;
import com.vietbevis.movies.models.Movie;
import com.vietbevis.movies.repositories.MovieRepository;
import com.vietbevis.movies.services.movie.MovieService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
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

    @PostConstruct
    @Transactional
    public ResponseFetch fetchMovies() {

        for (int i = 1; i <= 50; i++) {
            try {
                fetchAndProcessPage(i);
//                if (i % 2 == 0) Thread.sleep(10000);
            } catch (Exception e) {
                logger.severe("Lỗi tải page: " + i + " từ API. " + e.getMessage());
            }
        }
        if (!slugsError.isEmpty()) retryFailedSlugs();
        logger.info("Tải dữ liệu thành công.");
        return null;
    }

    private void fetchAndProcessPage(int pageIndex) throws DataExistsException {
        ResponseFetch responseFetch = apiService.fetchMovieData(pageIndex);
        if (responseFetch == null) {
            throw new DataExistsException("Không có dữ liệu page: " + pageIndex + " từ API.");
        }
        logger.info("Lấy dữ liệu từ page: " + pageIndex + " thành công.");

        responseFetch.getItems().forEach(item -> processMovie(item.getSlug()));

        logger.info("Lưu dữ liệu từ page: " + pageIndex + " thành công.");
    }

    private void processMovie(String slug) {
        try {
            Movie movieExistsName = movieRepository.findMovieBySlug(slug);
            if (movieExistsName != null) {
                return;
            }

            MovieCreationRequest movieCreationRequest = apiService.fetchMovieDetails(slug);
            MovieResponse movieResponse = movieService.createMovie(movieCreationRequest);
            count++;
            logger.info(count + ", Tải phim: " + movieResponse.getName() + " thành công.");
        } catch (Exception e) {
            slugsError.add(slug);
            logger.severe("Lỗi tải phim: " + slug + ". " + e.getMessage());
        }
    }

    private void retryFailedSlugs() {
        List<String> remainingSlugs = new ArrayList<>(slugsError);
        slugsError.clear();
        for (String slug : remainingSlugs) {
            int retryCount = 0;
            boolean success = false;
            while (retryCount < 3 && !success) {
                try {
                    processMovie(slug);
                    Thread.sleep(300000);
                    success = true;
                } catch (Exception e) {
                    retryCount++;
                    logger.severe("Lỗi tải phim: " + slug + " lần " + retryCount + ". " + e.getMessage());
                    if (retryCount == 3) {
                        logger.severe("Bỏ qua phim: " + slug + " sau 3 lần thử không thành công.");
                    }
                }
            }
        }
    }
}
