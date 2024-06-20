package com.vietbevis.movies.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Getter
@Setter
public class MovieDTO {

    @NotBlank(message = "Name movie is required")
    @JsonProperty("name")
    private String name;

    @NotBlank(message = "Slug movie is required")
    @JsonProperty("slug")
    private String slug;

    @NotBlank(message = "Origin name movie is required")
    @JsonProperty("origin_name")
    private String originName;

    @JsonProperty("type")
    private String type;

    @JsonProperty("status")
    private String status;

    @JsonProperty("content")
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

    @JsonProperty("quality")
    private String quality;

    @JsonProperty("lang")
    private String lang;

    @JsonProperty("year")
    private Integer year;

    @JsonProperty("view")
    private Integer view;

    @JsonProperty("time")
    private String time;

    @JsonProperty("showtimes")
    private String showTimes;

    @JsonProperty("actor")
    private List<String> actors;

    @JsonProperty("category")
    private List<CategoryDTO> categories;

    @JsonProperty("director")
    private List<String> directors;

    @JsonProperty("country")
    private List<CountryDTO> countries;

    @JsonProperty("episodes")
    private String episodes;

}
