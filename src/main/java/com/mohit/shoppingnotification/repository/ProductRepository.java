package com.mohit.shoppingnotification.repository;

import com.mohit.shoppingnotification.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}