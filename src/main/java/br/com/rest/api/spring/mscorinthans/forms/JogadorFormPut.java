package br.com.rest.api.spring.mscorinthans.forms;

import br.com.rest.api.spring.mscorinthans.dto.Posicao;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class JogadorFormPut {

    @NotNull @NotEmpty
    private String altura;
    @NotNull @NotEmpty
    private String cidadeNatal;
    @NotNull
    private int idade;
    @NotNull @NotEmpty
    private String nome;
    @NotNull
    private Posicao posicao;
}
