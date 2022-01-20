package darshan.thakkar.practical.practicals.practical_2.utility;

import darshan.thakkar.practical.practicals.practical_2.models.GenericResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/")
public abstract class RestControllerUtility<T> extends LoggerUtility<T> {

    public ResponseEntity<GenericResponseModel> getResponseFromReq(@Valid @RequestBody T reqModel, Errors errors) {
        GenericResponseModel responseModel = new GenericResponseModel();
        long startTime = System.currentTimeMillis() * 1000;
        infoLog(START);
        try {
            if (errors.hasErrors()) {
                responseModel = getResponseModel(HttpStatus.NOT_ACCEPTABLE.value()
                        , errors, null, null);
                errorLog(false, reqModel, responseModel);
                infoLog(REQUEST_PROCESSING_TIME_LOG_STRING + " -> " +
                        ((System.currentTimeMillis() * 1000) - startTime));
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE.value())
                        .body(responseModel);
            }
            responseModel = getResponseModel(HttpStatus.OK.value()
                    , null, reqModel, "Request is processed successfully !!");
            debugLog(true, reqModel, responseModel);
            infoLog(END + " with " + REQUEST_PROCESSING_TIME_LOG_STRING + " -> "
                    + ((System.currentTimeMillis() * 1000) - startTime));
            return ResponseEntity.status(HttpStatus.OK.value())
                    .body(responseModel);
        } catch (Exception ex) {
            responseModel = getResponseModel(HttpStatus.INTERNAL_SERVER_ERROR.value()
                    , null, reqModel, "Server is not reachable or there may be some error !!");
            errorLog(false, reqModel, responseModel);
            infoLog(REQUEST_PROCESSING_TIME_LOG_STRING + " -> " + ((System.currentTimeMillis() * 1000) - startTime));
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
            outputStr = outputStr + cm + fe.getDefaultMessage() + ". Rejected Value: (" + fe.getRejectedValue() + ")";
            cm = " \n";

        }
        return outputStr;
    }

    private <T> GenericResponseModel getResponseModel(int statusCode, Errors errors, T data, String message) {
        return new GenericResponseModel(statusCode
                , (null != errors) ? getDefaultErrorMessages(errors.getFieldErrors()) : message, data);
    }
}
