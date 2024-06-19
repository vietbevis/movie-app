package com.vietbevis.movies.fetchapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vietbevis.movies.responses.movie.MovieChildResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseFetch {
    private boolean status;

    private List<MovieChildResponse> items;

    @JsonProperty("pathImage")
    private String pathImage;

    private Pagination pagination;
}
