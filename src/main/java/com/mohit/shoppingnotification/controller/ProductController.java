package com.mohit.shoppingnotification.controller;

import com.mohit.shoppingnotification.dto.ProductResponseDTO;
import com.mohit.shoppingnotification.model.Product;
import com.mohit.shoppingnotification.service.ProductService;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.* ;
import jakarta.validation.Valid;
import java.util.List;
import com.mohit.shoppingnotification.dto.ProductRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ProductResponseDTO createProduct(
            @Valid @RequestBody ProductRequestDTO requestDTO) {

        return productService.createProduct(requestDTO);
    }

    @GetMapping
    public Page<ProductResponseDTO> getAllProducts(
            @PageableDefault(size = 10, page = 0) Pageable pageable) {

        return productService.getAllProducts(pageable);
    }

    @GetMapping("/{id}")
    public ProductResponseDTO getProductById(@PathVariable Integer id) {
        return productService.getProductById(id);
    }

    @PutMapping("/{id}")
    public ProductResponseDTO updateProduct(
            @PathVariable Integer id,
            @Valid @RequestBody ProductRequestDTO requestDTO) {

        return productService.updateProduct(id, requestDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
    }
}