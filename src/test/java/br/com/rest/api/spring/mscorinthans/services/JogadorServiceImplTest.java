package br.com.rest.api.spring.mscorinthans.services;

import br.com.rest.api.spring.mscorinthans.exceptions.JogadorNaoEncontradoException;
import br.com.rest.api.spring.mscorinthans.models.SoccerPlayer;
import br.com.rest.api.spring.mscorinthans.repositories.SoccerPlayerRepository;
import br.com.rest.api.spring.mscorinthans.services.impl.JogadorServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class JogadorServiceImplTest {

    private static final int ID_VALIDO = 1;
    private static final int ID_INVALIDO = 2;
    private static final String NOME_VALIDO = "Cássio";
    private static final String POSICAO_VALIDA = "GOLEIRO";
    private static final String ALTURA_VALIDA = "1,95";
    private static final int IDADE_VALIDA = 32;
    private static final String CIDADE_NATAL_VALIDA = "Veranópolis";

    @MockBean
    private SoccerPlayerRepository soccerPlayerRepository;

    private JogadorService jogadorService;

    private SoccerPlayer soccerPlayer;

    @Before
    public void setUp(){

        jogadorService = new JogadorServiceImpl(soccerPlayerRepository);

        soccerPlayer = new SoccerPlayer();
        soccerPlayer.setId((long) ID_VALIDO);
        soccerPlayer.setName(NOME_VALIDO);
        soccerPlayer.setPosition(POSICAO_VALIDA);
        soccerPlayer.setHeight(ALTURA_VALIDA);
        soccerPlayer.setAge(IDADE_VALIDA);
        soccerPlayer.setHometown(CIDADE_NATAL_VALIDA);
    }

    @Test
    public void buscarJogadorSucesso() {

        when(soccerPlayerRepository.findById((long) ID_VALIDO)).thenReturn(Optional.of(soccerPlayer));

        SoccerPlayer soccerPlayer = jogadorService.buscarJogador((long) ID_VALIDO);

        assertThat(soccerPlayer).isNotNull();
        assertThat(soccerPlayer.getName()).isEqualTo(NOME_VALIDO);
        assertThat(soccerPlayer.getPosition()).isEqualTo(POSICAO_VALIDA);
        assertThat(soccerPlayer.getHeight()).isEqualTo(ALTURA_VALIDA);
        assertThat(soccerPlayer.getAge()).isEqualTo(IDADE_VALIDA);
        assertThat(soccerPlayer.getHometown()).isEqualTo(CIDADE_NATAL_VALIDA);
    }

    @Test(expected = JogadorNaoEncontradoException.class)
    public void jogadorNaoEncontradoException(){

        when(soccerPlayerRepository.findById((long) ID_INVALIDO)).thenReturn(Optional.empty());

        jogadorService.buscarJogador((long) ID_INVALIDO);
    }

}
