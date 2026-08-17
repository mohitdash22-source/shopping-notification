package com.mohit.shoppingnotification.controller;

import com.mohit.shoppingnotification.dto.CategoryResponseDTO;
import com.mohit.shoppingnotification.model.Category;
import com.mohit.shoppingnotification.service.CategoryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")

public class CategoryController {

    @GetMapping("/{id}")
    public CategoryResponseDTO getCategoryById(@PathVariable Integer id) {
        return categoryService.getCategoryById(id);
    }

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return categoryService.createCategory(category);
    }
}