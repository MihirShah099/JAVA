package com.arc.excelUpload.excelToJson;

import lombok.Data;

import java.util.List;

@Data
public class ExcelGenericResponse<T> {
    private String excelMessage;
    private List<T> resultList;
}
