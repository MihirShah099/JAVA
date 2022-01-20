package com.arc.excelUpload.excelToJson;

import lombok.Data;

import java.sql.Date;

@Data
public class ExcelResponseForException {
    private Boolean isValid = true;
    private Date sqlDate;
    private Double doubleData;
    private String stringForLong;
    private String exceptionMessage;
}
