package br.com.rest.api.spring.mscorinthans.services.impl;

import br.com.rest.api.spring.mscorinthans.exceptions.JogadorNaoEncontradoException;
import br.com.rest.api.spring.mscorinthans.models.SoccerPlayer;
import br.com.rest.api.spring.mscorinthans.repositories.SoccerPlayerRepository;
import br.com.rest.api.spring.mscorinthans.services.JogadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JogadorServiceImpl implements JogadorService {

    @Autowired
    private SoccerPlayerRepository soccerPlayerRepository;

    public JogadorServiceImpl(SoccerPlayerRepository soccerPlayerRepository) {
        this.soccerPlayerRepository = soccerPlayerRepository;
    }

    @Override
    public List<SoccerPlayer> listaDeJogadores() {
        return soccerPlayerRepository.findAll();
    }

    @Override
    public SoccerPlayer buscarJogador(Long id) {

        return soccerPlayerRepository.findById(id)
                .orElseThrow(() -> new JogadorNaoEncontradoException("Jogador com o id: "+ id + " não foi encontrado!"));
    }
}
