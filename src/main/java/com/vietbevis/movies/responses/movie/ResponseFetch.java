package com.vietbevis.movies.responses.movie;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    private com.vietbevis.movies.dtos.paginationDTO paginationDTO;
}
