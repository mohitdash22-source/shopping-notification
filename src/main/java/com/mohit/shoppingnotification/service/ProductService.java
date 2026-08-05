package com.mohit.shoppingnotification.service;

import com.mohit.shoppingnotification.exception.ProductNotFoundException;
import com.mohit.shoppingnotification.model.Product;
import com.mohit.shoppingnotification.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    public Product getProductById(Integer id) {
        return productRepository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Product not found with id: " + id));
    }
    public Product updateProduct(Integer id, Product product) {
        Product existingProduct = productRepository
                .findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Product not found with id: " + id));

        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());

        return productRepository.save(existingProduct);
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