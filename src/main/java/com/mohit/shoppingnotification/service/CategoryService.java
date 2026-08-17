package com.mohit.shoppingnotification.service;

import com.mohit.shoppingnotification.exception.CategoryNotFoundException;
import com.mohit.shoppingnotification.model.Category;
import com.mohit.shoppingnotification.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import com.mohit.shoppingnotification.dto.CategoryResponseDTO;
import com.mohit.shoppingnotification.dto.ProductResponseDTO;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public CategoryResponseDTO getCategoryById(Integer id) {

        Category category = categoryRepository
                .findById(id)
                .orElseThrow(() ->
                        new CategoryNotFoundException(
                                "Category not found with id: " + id));

        CategoryResponseDTO responseDTO = new CategoryResponseDTO();

        responseDTO.setId(category.getId());
        responseDTO.setName(category.getName());

        List<ProductResponseDTO> productDTOs = category.getProducts()
                .stream()
                .map(product -> {

                    ProductResponseDTO productDTO = new ProductResponseDTO();

                    productDTO.setId(product.getId());
                    productDTO.setName(product.getName());
                    productDTO.setPrice(product.getPrice());

                    return productDTO;
                })
                .toList();

        responseDTO.setProducts(productDTOs);

        return responseDTO;
    }
}