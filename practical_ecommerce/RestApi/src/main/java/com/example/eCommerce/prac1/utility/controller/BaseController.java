package com.example.eCommerce.prac1.utility.controller;

import com.example.eCommerce.prac1.utility.genericResponse.GenericResponseModel;
import com.example.eCommerce.prac1.utility.log.AppLogger;
import com.example.eCommerce.prac1.utility.service.IBaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseController<DTO> implements IBaseController<DTO> {
    private IBaseService<DTO, Long> service;

    public BaseController(IBaseService<DTO, Long> service) {
        this.service = service;
    }

    public abstract String getModuleName();

    @Override
    @GetMapping
    public ResponseEntity<GenericResponseModel> getAll() {
        GenericResponseModel responseModel = new GenericResponseModel();
        try {
            List<DTO> responseList = service.getAllEntities();
            responseModel = getResponseModel(HttpStatus.OK.value(), null, null, responseList, "Success");
            return ResponseEntity.status(HttpStatus.OK).body(responseModel);
        } catch (Exception e) {
            AppLogger.logger.error(getModuleName() + " [getAll()] " + e.getMessage(), e);
            responseModel = getResponseModel(HttpStatus.INTERNAL_SERVER_ERROR.value(), null
                    , null, null, "Internal server error.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseModel);
        }
    }

    @Override
    @GetMapping("{id}")
    public ResponseEntity<GenericResponseModel> getEntityById(@PathVariable String id) {
        GenericResponseModel responseModel = new GenericResponseModel();
        try {
            DTO data = service.getEntityById(Long.valueOf(id));
            responseModel = getResponseModel(HttpStatus.OK.value(), null, data, null, "Success");
            return ResponseEntity.status(HttpStatus.OK).body(responseModel);
        } catch (Exception e) {
            AppLogger.logger.error(getModuleName() + " [getEntityById()] " + e.getMessage(), e);
            responseModel = getResponseModel(HttpStatus.INTERNAL_SERVER_ERROR.value(), null
                    , null, null, "Internal server error.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseModel);
        }
    }

    @Override
    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseModel> save(@Valid @RequestBody DTO entityDTO, BindingResult result) {
        GenericResponseModel responseModel = new GenericResponseModel();
        try {
            if (result.hasErrors()) {
                responseModel = getResponseModel(HttpStatus.NOT_ACCEPTABLE.value(), result, null, null, null);
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(responseModel);
            }
            DTO data = service.saveEntity(entityDTO);
            responseModel = getResponseModel(HttpStatus.OK.value(), null
                    , data, null, "Success.");
            return ResponseEntity.status(HttpStatus.OK).body(responseModel);

        } catch (Exception e) {
            AppLogger.logger.error(getModuleName() + " [save()] " + e.getMessage(), e);
            responseModel = getResponseModel(HttpStatus.INTERNAL_SERVER_ERROR.value(), null
                    , null, null, "Internal server error.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseModel);
        }
    }

    @Override
    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseModel> update(@Valid @RequestBody DTO entityDTO, BindingResult result) {
        GenericResponseModel responseModel = new GenericResponseModel();
        try {
            if (result.hasErrors()) {
                responseModel = getResponseModel(HttpStatus.NOT_ACCEPTABLE.value(), result, null, null, null);
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(responseModel);
            }
            DTO data = service.updateEntity(entityDTO);
            responseModel = getResponseModel(HttpStatus.OK.value(), null
                    , data, null, "Success.");
            return ResponseEntity.status(HttpStatus.OK).body(responseModel);

        } catch (Exception e) {
            AppLogger.logger.error(getModuleName() + " [update()] " + e.getMessage(), e);
            responseModel = getResponseModel(HttpStatus.INTERNAL_SERVER_ERROR.value(), null
                    , null, null, "Internal server error.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseModel);
        }
    }

    @Override
    @PostMapping(value = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseModel> delete(@RequestBody DTO entityDTO) {
        GenericResponseModel responseModel = new GenericResponseModel();
        try {
            service.deleteEntity(entityDTO);
            responseModel = getResponseModel(HttpStatus.OK.value(), null
                    , null, null, "Success.");
            return ResponseEntity.status(HttpStatus.OK).body(responseModel);
        } catch (Exception e) {
            AppLogger.logger.error(getModuleName() + " [delete()] " + e.getMessage(), e);
            responseModel = getResponseModel(HttpStatus.INTERNAL_SERVER_ERROR.value(), null
                    , null, null, "Internal server error.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseModel);
        }
    }

    public  <T> GenericResponseModel getResponseModel(int statusCode, BindingResult bindingResult, T data, List<T> dataList, String message) {
        return new GenericResponseModel(statusCode, data, dataList
                , null != bindingResult ? getError(bindingResult.getFieldErrors()) : message);
    }

    private String getError(List<FieldError> fieldErrors) {
        if (null != fieldErrors && 0 < fieldErrors.size())
            return fieldErrors.stream()
                    .map(error -> error.getDefaultMessage()
                            + " given Input : " + error.getRejectedValue() + "\n")
                    .collect(Collectors.joining());
        else
            return "Internal server error.";
    }
}
