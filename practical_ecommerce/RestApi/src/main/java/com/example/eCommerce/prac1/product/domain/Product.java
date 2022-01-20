package com.example.eCommerce.prac1.product.domain;

import com.example.eCommerce.prac1.productType.domain.ProductType;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tbl_product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @ManyToOne
    private ProductType productType;
    @Column(columnDefinition = "double default 0.0")
    private Double price;
    @Column(columnDefinition = "double default 0.0")
    private Double stock;
}
