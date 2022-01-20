package com.example.eCommerce.prac1.user.model;

import com.example.eCommerce.prac1.role.model.RoleDTO;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Date;

@Data
public class UserDTO {
    private Long id;
    @NotBlank(message = "FirstName should not be empty.")
    private String firstName;
    @NotBlank(message = "LastName should not be empty.")
    private String lastName;
    private Date dateOfBirth;
    private String countryCode;
    @Size(min = 10, max = 10, message = "MobileNo should be 10 digit long.")
    private String mobileNo;
    private String address;
    @NotBlank(message = "UserName should not be empty.")
    private String userName;
    @NotBlank
    @Size(min = 6, message = "Password should be at least 6 characters long.")
    private String password;    //store password in encrypted form..
    private RoleDTO role;
}
