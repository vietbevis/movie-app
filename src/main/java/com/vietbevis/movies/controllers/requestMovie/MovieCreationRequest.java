package com.vietbevis.movies.controllers.requestMovie;

import com.vietbevis.movies.dtos.MovieDTO;
import jakarta.validation.Valid;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MovieCreationRequest {
    @Valid
    private MovieDTO movie;
    private List<EpisodeDTO> episodes;
    private boolean status;
}

