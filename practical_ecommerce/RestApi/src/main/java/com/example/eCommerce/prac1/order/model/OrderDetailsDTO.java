package com.example.eCommerce.prac1.order.model;

import com.example.eCommerce.prac1.product.model.ProductDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
public class OrderDetailsDTO {
    private Long id;
    @NotNull(message = "Product should not be empty")
    private ProductDTO product;
    private Double price = 0.0;
    private Double qty = 0.0;

    @JsonBackReference
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private OrderDTO order;
}
