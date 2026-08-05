package com.mohit.shoppingnotification.controller;

import com.mohit.shoppingnotification.model.Product;
import com.mohit.shoppingnotification.service.ProductService;
import org.springframework.web.bind.annotation.* ;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Integer id) {
        return productService.getProductById(id);
    }
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Integer id,
                                 @RequestBody Product product) {

        return productService.updateProduct(id, product);
    }
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
    }
}