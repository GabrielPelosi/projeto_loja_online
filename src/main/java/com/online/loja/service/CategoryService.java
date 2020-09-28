package com.online.loja.service;

import com.online.loja.repository.entity.Category;


import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<Category> getAllCategory();

    Optional<Category> getCategoryById(Long id);

    Optional<Category> updateCategory(Category category);

    Optional<Boolean> deleteCategoryById(Long id);

    Category createCategory(Category category);


}
