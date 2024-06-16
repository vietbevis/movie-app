package com.vietbevis.movies.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
public class ActorDTO {
    @NotBlank(message = "Name actor is required")
    private String name;
}
