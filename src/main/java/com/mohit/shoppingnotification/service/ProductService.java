package com.mohit.shoppingnotification.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.mohit.shoppingnotification.dto.ProductResponseDTO;
import com.mohit.shoppingnotification.exception.CategoryNotFoundException;
import com.mohit.shoppingnotification.exception.ProductNotFoundException;
import com.mohit.shoppingnotification.model.Category;
import com.mohit.shoppingnotification.model.Product;
import com.mohit.shoppingnotification.repository.ProductRepository;
import org.springframework.stereotype.Service;
import com.mohit.shoppingnotification.dto.ProductRequestDTO;
import com.mohit.shoppingnotification.repository.CategoryRepository;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository,
                          CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public ProductResponseDTO createProduct(ProductRequestDTO requestDTO) {

        Product product = new Product();

        product.setName(requestDTO.getName());
        product.setPrice(requestDTO.getPrice());

        Category category = categoryRepository
                .findById(requestDTO.getCategoryId())
                .orElseThrow(() ->
                        new CategoryNotFoundException(
                                "Category not found with id: "
                                        + requestDTO.getCategoryId()));

        product.setCategory(category);

        Product savedProduct = productRepository.save(product);

        ProductResponseDTO responseDTO = new ProductResponseDTO();

        responseDTO.setId(savedProduct.getId());
        responseDTO.setName(savedProduct.getName());
        responseDTO.setPrice(savedProduct.getPrice());

        return responseDTO;
    }

    public Page<ProductResponseDTO> getAllProducts(Pageable pageable) {

        Page<Product> products = productRepository.findAll(pageable);

        return products.map(product -> {
            ProductResponseDTO dto = new ProductResponseDTO();
            dto.setId(product.getId());
            dto.setName(product.getName());
            dto.setPrice(product.getPrice());
            return dto;
        });
    }

    public ProductResponseDTO getProductById(Integer id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        ProductResponseDTO dto = new ProductResponseDTO();

        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());

        return dto;
    }

    public ProductResponseDTO updateProduct(
            Integer id,
            ProductRequestDTO requestDTO) {

        Product existingProduct = productRepository
                .findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Product not found with id: " + id));

        Category category = categoryRepository
                .findById(requestDTO.getCategoryId())
                .orElseThrow(() ->
                        new CategoryNotFoundException(
                                "Category not found with id: "
                                        + requestDTO.getCategoryId()));

        existingProduct.setName(requestDTO.getName());
        existingProduct.setPrice(requestDTO.getPrice());
        existingProduct.setCategory(category);

        Product updatedProduct =
                productRepository.save(existingProduct);

        ProductResponseDTO responseDTO =
                new ProductResponseDTO();

        responseDTO.setId(updatedProduct.getId());
        responseDTO.setName(updatedProduct.getName());
        responseDTO.setPrice(updatedProduct.getPrice());

        return responseDTO;
    }

    public void deleteProduct(Integer id) {

        Product existingProduct = productRepository
                .findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Product not found with id: " + id));

        productRepository.delete(existingProduct);
    }

    public Page<ProductResponseDTO> searchProductsByName(
            String name,
            Pageable pageable) {

        Page<Product> products =
                productRepository.findByNameContaining(name, pageable);

        return products.map(product -> {
            ProductResponseDTO dto = new ProductResponseDTO();

            dto.setId(product.getId());
            dto.setName(product.getName());
            dto.setPrice(product.getPrice());

            return dto;
        });
    }
}