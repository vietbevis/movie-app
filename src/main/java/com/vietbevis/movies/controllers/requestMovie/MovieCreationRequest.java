package com.vietbevis.movies.controllers.requestMovie;

import com.vietbevis.movies.dtos.MovieDTO;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class MovieCreationRequest {
    @Valid
    private MovieDTO movie;
    private List<EpisodeDTO> episodes;
}

