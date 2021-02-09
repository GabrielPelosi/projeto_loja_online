package com.online.loja.repository;

import com.online.loja.repository.entity.Category;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;

@DataJpaTest
@DisplayName("Test to Category Repository")
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    @DisplayName("Test to sve category when successful scenario")
    public void save_category_successful(){
        Category categoryToBeSaved = Category.builder().name("Empada").build();

        Category savedCategory = categoryRepository.save(categoryToBeSaved);

        Assertions.assertThat(savedCategory.getName()).isNotNull();
        Assertions.assertThat(savedCategory.getName()).isNotBlank();
        Assertions.assertThat(savedCategory.getName()).isEqualTo(categoryToBeSaved.getName());
    }

    @Test
    @DisplayName("Test to sve category with blank name")
    public void save_category__not_successful_blank_name(){
        Category categoryToBeSaved = Category.builder().name(" ").build();

        Assertions.assertThatThrownBy(() -> {
            Category savedCategory = categoryRepository.save(categoryToBeSaved);
        }).isInstanceOf(ConstraintViolationException.class)
                .hasMessageContaining("must not be blank");

    }

    @Test
    @DisplayName("Test to sve category with null name")
    public void save_category__not_successful_null_name(){
        Category categoryToBeSaved = Category.builder().name(null).build();

        Assertions.assertThatThrownBy(() -> {
            Category savedCategory = categoryRepository.save(categoryToBeSaved);
        }).isInstanceOf(ConstraintViolationException.class)
                .hasMessageContaining("must not be null");

    }
}