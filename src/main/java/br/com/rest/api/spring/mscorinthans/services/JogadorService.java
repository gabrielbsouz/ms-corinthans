package br.com.rest.api.spring.mscorinthans.services;

import br.com.rest.api.spring.mscorinthans.models.SoccerPlayer;

import java.util.List;

public interface JogadorService {

    public List<SoccerPlayer> listaDeJogadores();

    public SoccerPlayer buscarJogador(Long id);
}
