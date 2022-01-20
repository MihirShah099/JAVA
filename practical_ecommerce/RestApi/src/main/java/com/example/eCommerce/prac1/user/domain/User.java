package com.example.eCommerce.prac1.user.domain;

import com.example.eCommerce.prac1.role.domain.Role;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "tbl_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String countryCode;
    private String mobileNo;
    private String address;
    private String userName;
    //store password in encrypted form..
    private String password;
    @ManyToOne
    private Role role;
}
