package com.vietbevis.movies.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
public class DirectorDTO {
    @NotBlank(message = "Name director is required")
    private String name;
}
