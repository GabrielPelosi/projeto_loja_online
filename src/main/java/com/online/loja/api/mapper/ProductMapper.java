package com.online.loja.api.mapper;

import com.online.loja.model.ProductRequest;
import com.online.loja.model.ProductResponse;
import com.online.loja.repository.entity.Category;
import com.online.loja.repository.entity.Product;
import com.online.loja.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductMapper {

    private final CategoryMapper categoryMapper;

    private final CategoryService categoryService;

    @Autowired
    public ProductMapper(CategoryMapper categoryMapper, CategoryService categoryService) {
        this.categoryMapper = categoryMapper;
        this.categoryService = categoryService;
    }

    public Product toProductEntity(ProductRequest productRequest){
        return Product
                .builder()
                .id(null)
                .name(productRequest.getName())
                .price(productRequest.getPrice())
                .available(productRequest.getAvailable())
                .category(new Category(productRequest.getCategoryId(), null)).build();
    }

    public ProductResponse toProductResponse(Product product){
        return new ProductResponse()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .available(product.getAvailable())
                .category(categoryMapper.toCategoryResponse(product.getCategory()));
    }

    public List<ProductResponse> toProductResponseList(List<Product> products){
        List<ProductResponse> responseList = new ArrayList<>();

        for (Product product: products){
            ProductResponse productResponse = toProductResponse(product);
            responseList.add(productResponse);
        }

        return responseList;

    }
}
