package com.arc.TEST;

import com.arc.core.dto.GenericDataDTO;
import com.arc.core.exceptions.ExcelSameFieldNameException;
import com.arc.core.utillity.log.ApplicationLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/test")
public class TESTController {
    @Autowired
    private TESTService service;

    @PostMapping(path = "/json", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public GenericDataDTO getJson(@RequestParam(value = "file") MultipartFile file) throws Exception {
        GenericDataDTO genericDataDTO = new GenericDataDTO();
        genericDataDTO.setResponseMessage("Success");
        genericDataDTO.setResponseCode(HttpStatus.OK.value());
        try {
            genericDataDTO = GenericDataDTO.getGenericDataDTO(service.getJson(file));
        } catch (Exception e) {
            genericDataDTO.setResponseCode(HttpStatus.EXPECTATION_FAILED.value());
            genericDataDTO.setResponseMessage(HttpStatus.EXPECTATION_FAILED.getReasonPhrase());
            ApplicationLogger.logger.error("[Controller] " + e.getMessage(), e);
        }
        return genericDataDTO;
    }

    @PostMapping(path = "/json2", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public GenericDataDTO getJson2(@RequestParam(value = "file") MultipartFile file) throws Exception {
        GenericDataDTO genericDataDTO = new GenericDataDTO();
        genericDataDTO.setResponseMessage("Success");
        genericDataDTO.setResponseCode(HttpStatus.OK.value());
        try {
            genericDataDTO = GenericDataDTO.getGenericDataDTO(service.getJson2(file));
        } catch (Exception e) {
            if (e instanceof ExcelSameFieldNameException) {
                genericDataDTO.setResponseCode(HttpStatus.NOT_ACCEPTABLE.value());
                genericDataDTO.setResponseMessage(e.getMessage());
                return genericDataDTO;
            }
            genericDataDTO.setResponseCode(HttpStatus.EXPECTATION_FAILED.value());
            genericDataDTO.setResponseMessage(HttpStatus.EXPECTATION_FAILED.getReasonPhrase());
            ApplicationLogger.logger.error("[Controller] " + e.getMessage(), e);
        }
        return genericDataDTO;
    }
}



