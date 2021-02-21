package com.online.loja.service.serviceImpl;

import com.online.loja.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;


class CategoryServiceImplTest {

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @Test
    void when_get_all_categories_should_return_all_categories() {
    }

    @Test
    void when_get_category_by_id_should_return_category() {
    }

    @Test
    void when_update_category_should_return_category_updated() {
    }

    @Test
    void when_delete_category_by_id_should_return_true() {

    }

    @Test
    void when_delete_category_by_id_should_return_exceptiuon() {

    }

    @Test
    void when_create_category_should_return_category() {
    }
}