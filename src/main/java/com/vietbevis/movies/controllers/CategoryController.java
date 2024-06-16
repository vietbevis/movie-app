package com.vietbevis.movies.controllers;

import com.vietbevis.movies.dtos.CategoryDTO;
import com.vietbevis.movies.responses.ResponseObject;
import com.vietbevis.movies.responses.category.CategoryListResponse;
import com.vietbevis.movies.responses.category.CategoryResponse;
import com.vietbevis.movies.services.category.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<?> getCategories() {
        CategoryListResponse categoryListResponse = CategoryListResponse.builder()
                .categories(categoryService.getAllCategories()).build();
        return ResponseObject.getBuilder()
                .setMessage("Get all categories successfully")
                .setData(categoryListResponse)
                .build();
    }

    @PostMapping("")
    public ResponseEntity<?> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        CategoryResponse categoryResponse = categoryService.createCategory(categoryDTO);
        return ResponseObject.getBuilder()
                .setMessage("Create category successfully")
                .setData(categoryResponse)
                .build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@Valid @PathVariable("id") Long id) {
        CategoryResponse categoryResponse = categoryService.getCategoryById(id);
        return ResponseObject.getBuilder()
                .setMessage("Get category successfully")
                .setData(categoryResponse)
                .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable("id") Long id, @Valid @RequestBody CategoryDTO categoryDTO) {
        CategoryResponse categoryResponse = categoryService.updateCategory(id, categoryDTO);
        return ResponseObject.getBuilder()
                .setMessage("Get category successfully")
                .setData(categoryResponse)
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") Long id) {
        categoryService.deleteCategory(id);
        return ResponseObject.getBuilder()
                .setMessage("Delete category successfully")
                .build();
    }
}
