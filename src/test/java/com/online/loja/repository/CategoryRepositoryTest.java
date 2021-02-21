package com.online.loja.repository;

import com.online.loja.repository.entity.Category;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import javax.validation.ConstraintViolationException;
import java.util.NoSuchElementException;
import java.util.Optional;

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



    @Test
    @DisplayName("Test to get category by id")
    public void get_category_by_id_successful(){
        Category categoryToBeSaved = Category.builder().name("Empada").build();

        Category savedCategory = categoryRepository.save(categoryToBeSaved);

        Optional<Category> categoryFound = categoryRepository.findById(savedCategory.getId());

        Assertions.assertThat(categoryFound).isNotNull();
        Assertions.assertThat(categoryFound).isNotEmpty();
        Assertions.assertThat(categoryFound.get()).isNotNull();
        Assertions.assertThat(categoryFound.get()).isNotNull();
        Assertions.assertThat(categoryFound.get().getId()).isEqualTo(savedCategory.getId());
        Assertions.assertThat(categoryFound.get().getName()).isEqualTo(savedCategory.getName());
    }


    @Test
    @DisplayName("Test to get category by id not successful scenario")
    public void get_category_by_id_not_successful_category_id_null(){
        Assertions.assertThatThrownBy(() -> {
            categoryRepository.findById(null);
        }).isInstanceOf(InvalidDataAccessApiUsageException.class).hasMessageContaining("The given id must not be null!");
    }

    @Test
    @DisplayName("Test to get category by id not successful scenario, category does not exists")
    public void get_category_by_id_not_successful_category_not_exists(){
        Assertions.assertThatThrownBy(() -> {
            Category category = categoryRepository.findById(1L).get();
        }).isInstanceOf(NoSuchElementException.class).hasMessageContaining("No value present");

        Assertions.assertThatThrownBy(() -> {
            Category category = categoryRepository.findById(0L).get();
        }).isInstanceOf(NoSuchElementException.class).hasMessageContaining("No value present");

        Assertions.assertThatThrownBy(() -> {
            Category category = categoryRepository.findById(3L).get();
        }).isInstanceOf(NoSuchElementException.class).hasMessageContaining("No value present");
    }


}