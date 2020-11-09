package com.online.loja.api.endpoint;


import com.online.loja.api.mapper.CategoryMapper;
import com.online.loja.endpoint.CategoriesApi;
import com.online.loja.model.CategoryRequest;
import com.online.loja.model.CategoryResponse;
import com.online.loja.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CategoryController implements CategoriesApi {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryController(CategoryService categoryService,
                              CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }


    @Override
    @PreAuthorize(value = "hasRole('ADMIN')")
    public ResponseEntity<CategoryResponse> createCategory(CategoryRequest categoryRequest) {
        var category = categoryMapper.toCategoryEntity(categoryRequest);
        var categoryResponse = categoryMapper.toCategoryResponse(categoryService.createCategory(category));
        return ResponseEntity.ok(categoryResponse);
    }

    @Override
    @PreAuthorize(value = "hasRole('ADMIN')")
    public ResponseEntity<Void> deleteCategoryById(Long categoryId) {
        return categoryService.deleteCategoryById(categoryId)
                .map(res -> new ResponseEntity<Void>(HttpStatus.NO_CONTENT))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategory()
                .stream()
                .map(categoryMapper::toCategoryResponse)
                .collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<CategoryResponse> getCategoryById(Long categoryId) {
        return categoryService.getCategoryById(categoryId)
                .map(categoryMapper::toCategoryResponse)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    @PreAuthorize(value = "hasRole('ADMIN')")
    public ResponseEntity<CategoryResponse> updateCategoryById(Long categoryId, CategoryRequest categoryRequest) {

        var category = categoryMapper.toCategoryEntity(categoryRequest);
        category.setId(categoryId);
        return categoryService.updateCategory(category)
                .map(categoryMapper::toCategoryResponse)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
