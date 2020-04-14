package br.com.rest.api.spring.mscorinthans.services;

import br.com.rest.api.spring.mscorinthans.models.SoccerPlayer;

public interface JogadorService {

    public SoccerPlayer buscarJogador(Long id);
}
