package com.vietbevis.movies.services.category;

import com.vietbevis.movies.dtos.CategoryDTO;
import com.vietbevis.movies.responses.category.CategoryResponse;

import java.util.List;
import java.util.UUID;

public interface ICategoryService {
    CategoryResponse createCategory(CategoryDTO categoryDTO);

    CategoryResponse updateCategory(String id, CategoryDTO categoryDTO);

    void deleteCategory(String id);

    List<CategoryResponse> getAllCategories();

    CategoryResponse getCategoryById(String id);

    CategoryResponse getCategoryByName(String name);

    CategoryResponse getCategoryBySlug(String slug);
}
