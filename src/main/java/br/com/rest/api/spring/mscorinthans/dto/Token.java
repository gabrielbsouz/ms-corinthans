package br.com.rest.api.spring.mscorinthans.dto;

import lombok.Data;

@Data
public class Token {

    private String token;
    private String tipo;

    public Token(String token, String tipo) {
        this.token = token;
        this.tipo = tipo;
    }
}
