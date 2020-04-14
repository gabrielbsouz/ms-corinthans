package br.com.rest.api.spring.mscorinthans.dto;

import lombok.Data;

@Data
public class DetalheJogador {

    private String nome;
    private String posicao;
    private String altura;
    private int idade;
    private String cidadeNatal;

}
