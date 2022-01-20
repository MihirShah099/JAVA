package com.example.RestApi.prac1.user.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserDTO {
    private Long id;
    @NotBlank(message = "Name should not be empty.")
    private String name;
    private String address;
    @NotBlank(message = "UserName should not be empty.")
    private String userName;
    @Size(min = 6, message = "Password should be at least 6 characters long.")
    private String password;
}
