package com.example.eCommerce.prac1.utility.genericResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenericResponseModel {
    private int status;
    private Object data;
    private List dataList;
    private String responseMessage;
}
