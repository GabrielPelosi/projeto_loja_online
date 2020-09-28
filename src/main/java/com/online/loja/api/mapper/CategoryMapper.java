package com.online.loja.api.mapper;


import com.online.loja.model.CategoryRequest;
import com.online.loja.model.CategoryResponse;
import com.online.loja.repository.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {


    public Category toCategoryEntity(CategoryRequest categoryRequest){
        return Category
                .builder()
                .id(null)
                .name(categoryRequest.getName())
                .build();
    }

    public CategoryResponse toCategoryResponse(Category category){
        return new CategoryResponse()
                .id(category.getId())
                .name(category.getName());
    }

}
