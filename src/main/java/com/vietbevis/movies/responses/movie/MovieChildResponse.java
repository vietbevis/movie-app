package com.vietbevis.movies.responses.movie;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieChildResponse {
    private String id;

    private String name;

    private String slug;

    @JsonProperty("origin_name")
    private String originName;

    private String type;

    private String status;

    @JsonProperty("thumb_url")
    private String thumbUrl;

    @JsonProperty("poster_url")
    private String posterUrl;

    @JsonProperty("episode_current")
    private String episodeCurrent;

    @JsonProperty("episode_total")
    private String episodeTotal;

    private String quality;

    private String lang;

    private Integer year;

    private Integer view;
}
