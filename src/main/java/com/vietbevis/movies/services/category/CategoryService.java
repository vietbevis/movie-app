package com.vietbevis.movies.services.category;

import com.vietbevis.movies.dtos.CategoryDTO;
import com.vietbevis.movies.exceptions.commons.DataExistsException;
import com.vietbevis.movies.exceptions.commons.DataNotFoundException;
import com.vietbevis.movies.mapper.CategoryMapper;
import com.vietbevis.movies.models.Category;
import com.vietbevis.movies.repositories.CategoryRepository;
import com.vietbevis.movies.responses.category.CategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryResponse createCategory(CategoryDTO categoryDTO) {
        CategoryResponse categoryExists = getCategoryByName(categoryDTO.getName());
        CategoryResponse categoryExistsSlug = getCategoryBySlug(categoryDTO.getSlug());
        if (categoryExists != null || categoryExistsSlug != null) {
            throw new DataExistsException("Category already exists");
        }
        Category newCategory = Category.builder()
                .name(categoryDTO.getName())
                .slug(categoryDTO.getSlug())
                .build();
        Category category = categoryRepository.save(newCategory);
        return categoryMapper.toCategoryResponse(category);
    }

    @Override
    public CategoryResponse updateCategory(String id, CategoryDTO categoryDTO) {
        Category categoryExists = categoryRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Category not found"));
        CategoryResponse categoryName = getCategoryByName(categoryDTO.getName());
        CategoryResponse categorySlug = getCategoryBySlug(categoryDTO.getSlug());
        if (categoryName != null || categorySlug != null) {
            throw new DataExistsException("Category already exists");
        }
        categoryExists.setName(categoryDTO.getName());
        categoryExists.setSlug(categoryDTO.getSlug());
        Category category = categoryRepository.save(categoryExists);
        return categoryMapper.toCategoryResponse(category);
    }

    @Override
    public void deleteCategory(String id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Category not found"));
        categoryRepository.delete(category);
    }

    @Override
    public List<CategoryResponse> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categoryMapper.toListCategoryResponse(categories);
    }

    @Override
    public CategoryResponse getCategoryById(final String id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Category not found"));
        return categoryMapper.toCategoryResponse(category);
    }

    @Override
    public CategoryResponse getCategoryByName(String name) {
        Category category = categoryRepository.findByName(name);
        return categoryMapper.toCategoryResponse(category);
    }

    @Override
    public CategoryResponse getCategoryBySlug(String slug) {
        Category category = categoryRepository.findBySlug(slug);
        return categoryMapper.toCategoryResponse(category);
    }
}
