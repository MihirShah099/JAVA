package com.example.RestApi.P2.rest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenericModelForResponse {

    private int status;
    private String responseMessage;
    private Object data;

    @Override
    public String toString() {
        return "GenericModelForResponse{" +
                "status=" + status +
                ", responseMessage='" + responseMessage + '\'' +
                ", data=" + data +
                '}';
    }
}
