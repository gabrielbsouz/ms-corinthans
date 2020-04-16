package br.com.rest.api.spring.mscorinthans.forms;

import lombok.Data;

@Data
public class LoginFormPost {

    private String email;
    private String senha;
}
