package com.mohit.shoppingnotification.service;

import com.mohit.shoppingnotification.dto.ProductResponseDTO;
import com.mohit.shoppingnotification.exception.ProductNotFoundException;
import com.mohit.shoppingnotification.model.Product;
import com.mohit.shoppingnotification.repository.ProductRepository;
import org.springframework.stereotype.Service;
import com.mohit.shoppingnotification.dto.ProductRequestDTO;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponseDTO createProduct(ProductRequestDTO requestDTO) {

        Product product = new Product();

        product.setName(requestDTO.getName());
        product.setPrice(requestDTO.getPrice());

        Product savedProduct = productRepository.save(product);

        ProductResponseDTO responseDTO = new ProductResponseDTO();

        responseDTO.setId(savedProduct.getId());
        responseDTO.setName(savedProduct.getName());
        responseDTO.setPrice(savedProduct.getPrice());

        return responseDTO;
    }

    public List<ProductResponseDTO> getAllProducts() {

        List<Product> products = productRepository.findAll();

        return products.stream()
                .map(product -> {
                    ProductResponseDTO dto = new ProductResponseDTO();
                    dto.setId(product.getId());
                    dto.setName(product.getName());
                    dto.setPrice(product.getPrice());
                    return dto;
                })
                .toList();
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

    public ProductResponseDTO updateProduct(Integer id, ProductRequestDTO requestDTO) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        product.setName(requestDTO.getName());
        product.setPrice(requestDTO.getPrice());

        Product updatedProduct = productRepository.save(product);

        ProductResponseDTO responseDTO = new ProductResponseDTO();

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
}