package br.com.rest.api.spring.mscorinthans.dto;

import lombok.Data;

@Data
public class Error {

    private int httpCode;
    private String httpMessage;
    private String moreInformation;

    public Error(int httpCode, String httpMessage, String moreInformation) {
        this.httpCode = httpCode;
        this.httpMessage = httpMessage;
        this.moreInformation = moreInformation;
    }
}
