package com.example.eCommerce.prac1.role.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RoleDTO {
    private Long id;
    @NotBlank(message = "Name should not be empty.")
    private String name;
    private String description;
}
