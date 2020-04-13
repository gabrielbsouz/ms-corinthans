package br.com.rest.api.spring.mscorinthans.services;

import br.com.rest.api.spring.mscorinthans.models.SoccerPlayer;

import java.util.Optional;

public interface JogadorService {

    Optional<SoccerPlayer> buscarJogador(Long id);

}
