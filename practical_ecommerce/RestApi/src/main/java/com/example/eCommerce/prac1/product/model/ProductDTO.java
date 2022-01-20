package com.example.eCommerce.prac1.product.model;

import com.example.eCommerce.prac1.productType.model.ProductTypeDTO;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ProductDTO {
    private Long id;
    @NotBlank(message = "name should not be empty.")
    private String name;
    private String description;
    @NotNull(message = "Product type should not be empty.")
    private ProductTypeDTO productType;
    private Double price = 0.0;
    /*made assumption that while saving data we define its current stock
     * because right now we have no module for adding stock(Inward or else)*/
    private Double stock = 0.0;
}
