package br.com.rest.api.spring.mscorinthans.services.impl;

import br.com.rest.api.spring.mscorinthans.exceptions.JogadorNaoEncontradoException;
import br.com.rest.api.spring.mscorinthans.mappers.JogadorMapper;
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

    @Autowired
    private JogadorMapper jogadorMapper;

    public JogadorServiceImpl(SoccerPlayerRepository soccerPlayerRepository) {
        this.soccerPlayerRepository = soccerPlayerRepository;
    }

    @Override
    public List<SoccerPlayer> listaDeJogadores() {

        return soccerPlayerRepository.findAll();
    }

    @Override
    public SoccerPlayer incluirJogador(SoccerPlayer soccerPlayer) {

        return soccerPlayerRepository.save(soccerPlayer);
    }

    @Override
    public SoccerPlayer buscarJogador(Long id) {

        return soccerPlayerRepository.findById(id)
                .orElseThrow(() -> new JogadorNaoEncontradoException("Jogador com o id: "+ id + " não foi encontrado!"));
    }

    @Override
    public SoccerPlayer alterarJogador(Long id, SoccerPlayer soccerPlayer) {

        soccerPlayerRepository.findById(id)
                .map(jogador -> {
                    jogador.setName(soccerPlayer.getName());
                    jogador.setPosition(soccerPlayer.getPosition());
                    jogador.setHeight(soccerPlayer.getHeight());
                    jogador.setAge(soccerPlayer.getAge());
                    jogador.setHometown(soccerPlayer.getHometown());
                    return soccerPlayerRepository.save(jogador);
                }).orElseThrow(() -> new JogadorNaoEncontradoException("Jogador com o id: " + id + " não encontrado!"));

        return soccerPlayer;
    }
}
