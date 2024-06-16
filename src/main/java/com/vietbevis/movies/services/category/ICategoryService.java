package com.vietbevis.movies.services.category;

import com.vietbevis.movies.dtos.CategoryDTO;
import com.vietbevis.movies.models.Category;
import com.vietbevis.movies.responses.category.CategoryResponse;

import java.util.List;

public interface ICategoryService {
    CategoryResponse createCategory(CategoryDTO categoryDTO);

    CategoryResponse updateCategory(Long id, CategoryDTO categoryDTO);

    void deleteCategory(Long id);

    List<CategoryResponse> getAllCategories();

    CategoryResponse getCategoryById(Long id);

    CategoryResponse getCategoryByName(String name);
}
