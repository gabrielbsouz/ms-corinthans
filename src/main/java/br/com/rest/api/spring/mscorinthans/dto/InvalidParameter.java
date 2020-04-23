package br.com.rest.api.spring.mscorinthans.dto;

import lombok.Data;

@Data
public class InvalidParameter {

    private String parameter;
    private String message;

    public InvalidParameter(String parameter, String message) {
        this.parameter = parameter;
        this.message = message;
    }
}
