package com.vietbevis.movies.fetchapi;

import com.vietbevis.movies.controllers.requestMovie.MovieCreationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class ApiService {
    private final WebClient.Builder webClientBuilder;

    @Scheduled(cron = "0 0 1 * * *")
    public void fetchApiData() {
        WebClient webClient = webClientBuilder.build();

        for (int i = 1; i < 10; i++) {
            fetchPageData(webClient, i);
        }
    }

    private void fetchPageData(WebClient webClient, int page) {
        String apiUrl = "https://apii.online/apii/danh-sach/phim-moi-cap-nhat?page=" + page;
        webClient.get()
                .uri(apiUrl)
                .retrieve()
                .bodyToMono(ResponseFetch.class)
                .retryWhen(Retry.fixedDelay(3, Duration.ofSeconds(5)))
                .subscribe(responseFetch -> {
                    if (responseFetch == null || responseFetch.getItems() == null) {
                        System.out.println("Không thể lấy dữ liệu từ trang : " + page);
                        return;
                    }
                    responseFetch.getItems().forEach(item -> fetchMovieDetails(webClient, item.getSlug()));
                }, error -> System.out.println("Error: " + error.getMessage()));
    }

    private void fetchMovieDetails(WebClient webClient, String slug) {
        String apiDetailsUrl = "https://apii.online/apii/phim/" + slug;
        webClient.get()
                .uri(apiDetailsUrl)
                .retrieve()
                .bodyToMono(MovieCreationRequest.class)
                .retryWhen(Retry.fixedDelay(3, Duration.ofSeconds(5)))
                .subscribe(movieCreationRequest -> {
                    if (movieCreationRequest == null) {
                        System.out.println("Không thể lấy dữ liệu từ : " + slug);
                        return;
                    }
                    postMovieDetails(webClient, movieCreationRequest).subscribe();
                }, error -> System.out.println("Error: " + error.getMessage()));
    }


    private Mono<Void> postMovieDetails(WebClient webClient, MovieCreationRequest movieDetails) {

        return webClient.post()
                .uri("http://localhost:8080/api/v1/movies")
                .bodyValue(movieDetails)
                .retrieve()
                .bodyToMono(Void.class)
                .retryWhen(Retry.fixedDelay(3, Duration.ofSeconds(5)))
                .doOnSuccess(aVoid -> System.out.println("Thêm thành công: " + movieDetails.getMovie().getName()))
                .doOnError(error -> System.out.println("Error: " + error.getMessage()));
    }
}
