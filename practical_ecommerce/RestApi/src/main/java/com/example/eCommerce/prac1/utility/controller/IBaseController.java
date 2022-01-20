package com.example.eCommerce.prac1.utility.controller;

import com.example.eCommerce.prac1.utility.genericResponse.GenericResponseModel;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface IBaseController<T> {
    ResponseEntity<GenericResponseModel> getAll();

    ResponseEntity<GenericResponseModel> getEntityById(@PathVariable String id);

    ResponseEntity<GenericResponseModel> save(@Valid @RequestBody T entityDTO, BindingResult result);

    ResponseEntity<GenericResponseModel> update(@Valid @RequestBody T entityDTO, BindingResult result);

    ResponseEntity<GenericResponseModel> delete(@RequestBody T entityDTO);
}
