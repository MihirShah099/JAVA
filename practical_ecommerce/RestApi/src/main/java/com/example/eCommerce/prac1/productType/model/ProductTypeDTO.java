package com.example.eCommerce.prac1.productType.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ProductTypeDTO {
    private Long id;
    @NotBlank(message = "name should not be empty.")
    private String name;
    private String description;
}
