package com.example.RestApi.P2.model;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class Account {

    @NotNull(message = "ID must not be blank.")
    private Long id;
    @NotNull(message = "Account number must not be blank.")
    private String accountNumber;
    @NotBlank(message = "Account holder's name must not be blank.")
    private String accountHolderName;
    @Email(message = "Email id must be valid.")
    private String email;
    @Size(max = 10, min = 10, message = "Mobile number must be of 10 digits.")
    private String mobileNumber;
    @AssertTrue(message = "Make this account as an active account.")
    private boolean activeAccount;
}
