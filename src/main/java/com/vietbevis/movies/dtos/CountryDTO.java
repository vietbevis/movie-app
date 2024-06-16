package com.vietbevis.movies.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
public class CountryDTO {
    @NotBlank(message = "Name country is required")
    private String name;

    @NotBlank(message = "Slug country is required")
    private String slug;
}
