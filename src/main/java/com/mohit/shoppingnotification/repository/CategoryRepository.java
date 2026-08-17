package com.mohit.shoppingnotification.repository;

import com.mohit.shoppingnotification.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}