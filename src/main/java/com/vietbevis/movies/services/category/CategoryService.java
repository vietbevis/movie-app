package com.vietbevis.movies.services.category;

import com.vietbevis.movies.dtos.CategoryDTO;
import com.vietbevis.movies.mapper.CategoryMapper;
import com.vietbevis.movies.models.Category;
import com.vietbevis.movies.repositories.CategoryRepository;
import com.vietbevis.movies.responses.category.CategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@RequiredArgsConstructor
public class CategoryService implements ICategoryService {
    private CategoryRepository categoryRepository;

    private CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public CategoryResponse createCategory(CategoryDTO categoryDTO) {
        CategoryResponse categoryExists = getCategoryByName(categoryDTO.getName());
        if (categoryExists != null) {
            throw new RuntimeException("Category already exists");
        }
        Category newCategory = Category.builder()
                .name(categoryDTO.getName())
                .slug(categoryDTO.getSlug())
                .build();
        return categoryMapper.toCategoryResponse(categoryRepository.save(newCategory));
    }

    @Override
    public CategoryResponse updateCategory(Long id, CategoryDTO categoryDTO) {
        Category categoryExists = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        categoryExists.setName(categoryDTO.getName());
        categoryExists.setSlug(categoryDTO.getSlug());
        return categoryMapper.toCategoryResponse(categoryRepository.save(categoryExists));
    }

    @Override
    public void deleteCategory(Long id) {

    }

    @Override
    public List<CategoryResponse> getAllCategories() {
        return categoryMapper.toListCategoryResponse(categoryRepository.findAll());
    }

    @Override
    public CategoryResponse getCategoryById(Long id) {
        return categoryMapper.toCategoryResponse(categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found")));
    }

    @Override
    public CategoryResponse getCategoryByName(String name) {
        return categoryMapper.toCategoryResponse(categoryRepository.findByName(name));
    }
}
