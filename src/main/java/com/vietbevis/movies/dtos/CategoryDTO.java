package com.vietbevis.movies.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
public class CategoryDTO {
    @NotBlank(message = "Name category is required")
    private String name;

    @NotBlank(message = "Slug category is required")
    private String slug;
}
