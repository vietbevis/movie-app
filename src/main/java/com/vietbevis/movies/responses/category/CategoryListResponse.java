package com.vietbevis.movies.responses.category;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryListResponse {
    private List<CategoryResponse> categories;
}
