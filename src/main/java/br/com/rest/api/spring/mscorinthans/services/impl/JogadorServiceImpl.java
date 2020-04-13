package br.com.rest.api.spring.mscorinthans.services.impl;

import br.com.rest.api.spring.mscorinthans.models.SoccerPlayer;
import br.com.rest.api.spring.mscorinthans.repositories.SoccerPlayerRepository;
import br.com.rest.api.spring.mscorinthans.services.JogadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JogadorServiceImpl implements JogadorService {

    @Autowired
    private SoccerPlayerRepository soccerPlayerRepository;

    @Override
    public Optional<SoccerPlayer> buscarJogador(Long id) {
        return soccerPlayerRepository.findById(id);
    }
}
