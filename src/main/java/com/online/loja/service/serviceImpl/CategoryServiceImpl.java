package com.online.loja.service.serviceImpl;


import com.online.loja.repository.CategoryRepository;
import com.online.loja.repository.entity.Category;
import com.online.loja.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Optional<Category> updateCategory(Category category) {
        return categoryRepository.findById(category.getId())
                .map(p -> category)
                .map(categoryRepository::save);
    }

    @Override
    public Optional<Boolean> deleteCategoryById(Long id) {
        return categoryRepository.findById(id)
                .map(c -> {
                    categoryRepository.delete(c);
                    return true;
                });

        }

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }
}

