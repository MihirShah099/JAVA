package com.example.RestApi.P2.utility;

import com.example.RestApi.P2.rest.GenericModelForResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/")
public abstract class RestControllerFramework<T> extends LogFramework<T>{

    public ResponseEntity<GenericModelForResponse> validateRequest(@Valid @RequestBody T reqModel, Errors errors) {
        GenericModelForResponse responseModel = new GenericModelForResponse();
        long requestTimeStart = System.currentTimeMillis() * 1000;
        information(START);
        try {
            if (errors.hasErrors()) {
                responseModel = getResponseModel(HttpStatus.NOT_ACCEPTABLE.value()
                        , errors, null, null);

                //Error Log
                error(false, reqModel, responseModel);
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE.value())
                        .body(responseModel);
            }
            responseModel = getResponseModel(HttpStatus.OK.value()
                    , null, reqModel, "Procession Of Request Is Done!!");

            //debug Log
            debug(true, reqModel, responseModel);

            //Processing Time
            information(END + " with " + PROCESS_TIME + " - "
                    + ((System.currentTimeMillis() * 1000) - requestTimeStart));
            return ResponseEntity.status(HttpStatus.OK.value())
                    .body(responseModel);
        } catch (Exception ex) {
            responseModel = getResponseModel(HttpStatus.INTERNAL_SERVER_ERROR.value()
                    , null, reqModel, "Server is not in reach or there may be some error !!");
            error(false, reqModel, responseModel);
            information(PROCESS_TIME + " - " + ((System.currentTimeMillis() * 1000) - requestTimeStart));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .body(null);
        }
    }

    private String getDefaultErrorMessages(List<FieldError> list) {
        if (null == list || list.size() < 1) {
            return "Something went wrong, Please try after some time";
        }
        String outputStr = "";
        String cm = "";
        for (FieldError fe : list) {
            outputStr = outputStr + cm + fe.getDefaultMessage() + ". Not accepted Value: (" + fe.getRejectedValue() + ")";
            cm = " \n";

        }
        return outputStr;
    }

    private <T> GenericModelForResponse  getResponseModel(int statusCode, Errors errors, T data, String message) {
        return new GenericModelForResponse(statusCode
                , (null != errors) ? getDefaultErrorMessages(errors.getFieldErrors()) : message, data);
    }
}
