package com.example.eCommerce.prac1.product.repository;

import com.example.eCommerce.prac1.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
