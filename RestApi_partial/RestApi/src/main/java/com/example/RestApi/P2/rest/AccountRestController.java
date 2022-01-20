package com.example.RestApi.P2.rest;


import com.example.RestApi.P2.model.Account;
import com.example.RestApi.P2.utility.RestControllerFramework;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/account")
public class AccountRestController extends RestControllerFramework<Account> {

    /**
     * This method is used for validate all the request
     * @param reqModel Account
     * @param error Errors
     * @return ResponseEntity<GenericModelForResponse>
     * */
    @PostMapping(path = "/create",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericModelForResponse> createAccount(@Valid @RequestBody Account reqModel, Errors error){
        return super.validateRequest(reqModel,error);
    }

    @Override
    protected String getLocationName() {
        return "This is Account Controller";
    }
}
