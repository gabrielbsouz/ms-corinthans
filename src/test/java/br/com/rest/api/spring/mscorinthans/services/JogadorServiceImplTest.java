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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class JogadorServiceImplTest {

    private static final Long ID_VALIDO = 1L;
    private static final Long ID_NAO_ENCONTRADO = 100L;
    private static final String NOME_VALIDO = "Teste Nome";
    private static final String POSICAO_VALIDA = "Teste Posicao";
    private static final String ALTURA_VALIDA = "1,95";
    private static final int IDADE_VALIDA = 30;
    private static final String CIDADE_NATAL_VALIDA = "Teste Cidade";

    @MockBean
    private SoccerPlayerRepository soccerPlayerRepository;

    private JogadorService jogadorService;

    private SoccerPlayer sc;
    private SoccerPlayer sc1;
    private List<SoccerPlayer> soccerPlayerList = new ArrayList<>();

    @Before
    public void setUp(){

        jogadorService = new JogadorServiceImpl(soccerPlayerRepository);

        sc = new SoccerPlayer();
        sc.setId(ID_VALIDO);
        sc.setName(NOME_VALIDO);
        sc.setPosition(POSICAO_VALIDA);
        sc.setHeight(ALTURA_VALIDA);
        sc.setAge(IDADE_VALIDA);
        sc.setHometown(CIDADE_NATAL_VALIDA);

        sc1 = new SoccerPlayer();
        sc1.setId(2L);
        sc1.setName(NOME_VALIDO);
        sc1.setPosition(POSICAO_VALIDA);
        sc1.setHeight(ALTURA_VALIDA);
        sc1.setAge(IDADE_VALIDA);
        sc1.setHometown(CIDADE_NATAL_VALIDA);

        soccerPlayerList.add(sc);
        soccerPlayerList.add(sc1);
    }

    @Test
    public void listaDeJogadoresSucesso(){

        when(soccerPlayerRepository.findAll()).thenReturn(soccerPlayerList);

        List<SoccerPlayer> soccerPlayers = jogadorService.listaDeJogadores();

        assertThat(soccerPlayers).isEqualTo(soccerPlayerList);
    }

    @Test
    public void incluirJogadorSucesso(){

        jogadorService.incluirJogador(sc);

        verify(soccerPlayerRepository).save(sc);
    }

    @Test
    public void buscarJogadorSucesso(){

        when(soccerPlayerRepository.findById(ID_VALIDO)).thenReturn(Optional.of(sc));

        SoccerPlayer soccerPlayer = jogadorService.buscarJogador(ID_VALIDO);

        assertThat(soccerPlayer).isEqualTo(sc);
    }

    @Test
    public void alterarJogadorSucesso(){

        when(soccerPlayerRepository.findById(ID_VALIDO)).thenReturn(Optional.of(sc));
        when(soccerPlayerRepository.save(sc)).thenReturn(sc);

        SoccerPlayer soccerPlayer = jogadorService.alterarJogador(ID_VALIDO, sc);

        assertThat(soccerPlayer).isEqualTo(sc);
    }

    @Test
    public void deletarJogadorSucesso(){

        when(soccerPlayerRepository.findById(ID_VALIDO)).thenReturn(Optional.of(sc));

        jogadorService.deletarJogador(ID_VALIDO);

        verify(soccerPlayerRepository).deleteById(ID_VALIDO);
    }

    @Test(expected = JogadorNaoEncontradoException.class)
    public void buscarJogadorIdNaoEncontrado(){

        when(soccerPlayerRepository.findById(ID_NAO_ENCONTRADO)).thenReturn(Optional.empty());

        SoccerPlayer soccerPlayer = jogadorService.buscarJogador(ID_NAO_ENCONTRADO);

        assertThat(soccerPlayer).isEqualTo(Optional.empty());
    }

    @Test(expected = JogadorNaoEncontradoException.class)
    public void alterarJogadorIdNaoEncontrado(){

        when(soccerPlayerRepository.findById(ID_NAO_ENCONTRADO)).thenReturn(Optional.empty());

        SoccerPlayer soccerPlayer = jogadorService.alterarJogador(ID_NAO_ENCONTRADO, sc);

        assertThat(soccerPlayer).isEqualTo(sc);
    }

    @Test(expected = JogadorNaoEncontradoException.class)
    public void deletarJogadorIdNaoEncontrado() {

        when(soccerPlayerRepository.findById(ID_NAO_ENCONTRADO)).thenReturn(Optional.empty());

        jogadorService.deletarJogador(ID_NAO_ENCONTRADO);

        verify(soccerPlayerRepository).deleteById(ID_NAO_ENCONTRADO);
    }
}
