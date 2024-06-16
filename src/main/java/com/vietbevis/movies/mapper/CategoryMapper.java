package com.vietbevis.movies.mapper;

import com.vietbevis.movies.models.Category;
import com.vietbevis.movies.responses.category.CategoryResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryResponse toCategoryResponse(Category category);

    List<CategoryResponse> toListCategoryResponse(List<Category> categories);
}
