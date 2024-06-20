package com.vietbevis.movies.services.init_movie_data;

import com.vietbevis.movies.controllers.requestMovie.MovieCreationRequest;
import com.vietbevis.movies.responses.movie.ResponseFetch;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.util.retry.Retry;

import java.time.Duration;

@Component
public class ApiService {
    private final WebClient webClient;

    public ApiService(WebClient webClient) {
        this.webClient = webClient;
    }

    public ResponseFetch fetchMovieData(Integer page) {
        String apiUrl = "https://apii.online/apii/danh-sach/phim-moi-cap-nhat?page=" + page;
        return webClient.get()
                .uri(apiUrl)
                .retrieve()
                .bodyToMono(ResponseFetch.class)
                .block();
    }

    public MovieCreationRequest fetchMovieDetails(String slug) {
        String apiDetailsUrl = "https://apii.online/apii/phim/" + slug;
        return webClient.get()
                .uri(apiDetailsUrl)
                .retrieve()
                .bodyToMono(MovieCreationRequest.class)
                .block();
    }

}
