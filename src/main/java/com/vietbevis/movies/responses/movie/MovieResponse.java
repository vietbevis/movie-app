package com.vietbevis.movies.responses.movie;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vietbevis.movies.responses.actor.ActorResponse;
import com.vietbevis.movies.responses.category.CategoryResponse;
import com.vietbevis.movies.responses.country.CountryResponse;
import com.vietbevis.movies.responses.director.DirectorResponse;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieResponse {
    private String id;

    private String name;

    private String slug;

    @JsonProperty("origin_name")
    private String originName;

    private String type;

    private String status;

    private String content;

    @JsonProperty("thumb_url")
    private String thumbUrl;

    @JsonProperty("poster_url")
    private String posterUrl;

    @JsonProperty("trailer_url")
    private String trailerUrl;

    @JsonProperty("episode_current")
    private String episodeCurrent;

    @JsonProperty("episode_total")
    private String episodeTotal;

    private String quality;

    private String lang;

    private Integer year;

    private Integer view;

    private String time;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("updated_at")
    private String updatedAt;

    private List<ActorResponse> actors;

    private List<CategoryResponse> categories;

    private List<DirectorResponse> directors;

    private List<CountryResponse> countries;

    private List<?> episodes;
}
