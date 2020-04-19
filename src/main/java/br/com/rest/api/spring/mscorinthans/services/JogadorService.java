package br.com.rest.api.spring.mscorinthans.services;

import br.com.rest.api.spring.mscorinthans.models.SoccerPlayer;

import java.util.List;

public interface JogadorService {

    public List<SoccerPlayer> listaDeJogadores();

    public SoccerPlayer incluirJogador(SoccerPlayer soccerPlayer);

    public SoccerPlayer buscarJogador(Long id);

    public SoccerPlayer alterarJogador(Long id, SoccerPlayer soccerPlayer);

    public void deletarJogador(Long id);
}
