package com.example.eCommerce.prac1.productType.repository;

import com.example.eCommerce.prac1.productType.domain.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {
}
