package br.com.rest.api.spring.mscorinthans.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Error {

    private int httpCode;
    private String httpMessage;
    private String description;
    private List<InvalidParameter> invalidParameters;

    public Error(){}

    public Error(int httpCode, String httpMessage, String description) {
        this.httpCode = httpCode;
        this.httpMessage = httpMessage;
        this.description = description;
    }

}
