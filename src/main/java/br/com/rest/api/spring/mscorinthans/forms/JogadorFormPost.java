package br.com.rest.api.spring.mscorinthans.forms;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class JogadorFormPost {

    private String altura;
    private String cidadeNatal;
    @NotNull
    private int idade;
    @NotNull @NotEmpty
    private String nome;
    @NotNull @NotEmpty
    private String posicao;
}
