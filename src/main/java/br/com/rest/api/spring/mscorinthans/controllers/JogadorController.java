package br.com.rest.api.spring.mscorinthans.controllers;

import br.com.rest.api.spring.mscorinthans.dto.DetalheJogador;
import br.com.rest.api.spring.mscorinthans.dto.Jogador;
import br.com.rest.api.spring.mscorinthans.forms.JogadorFormPost;
import br.com.rest.api.spring.mscorinthans.forms.JogadorFormPut;
import br.com.rest.api.spring.mscorinthans.mappers.JogadorMapper;
import br.com.rest.api.spring.mscorinthans.models.SoccerPlayer;
import br.com.rest.api.spring.mscorinthans.services.JogadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/rest/api/corinthans")
public class JogadorController {

    @Autowired
    private JogadorService jogadorService;

    @Autowired
    private JogadorMapper jogadorMapper;

    @GetMapping("/v1/jogadores")
    public ResponseEntity<List<Jogador>> listarJogadores(){

        List<SoccerPlayer> model = jogadorService.listaDeJogadores();

        return ResponseEntity.ok(jogadorMapper.soccerPlayersToJogadores(model));
    }

    @PostMapping("/v1/jogadores")
    public ResponseEntity<Jogador> cadastrarJogador(@Valid @RequestBody JogadorFormPost jogadorFormPost, UriComponentsBuilder uriCB){

        SoccerPlayer soccerPlayer = jogadorMapper.jogadorFormPostToSoccerPlayer(jogadorFormPost);

        jogadorService.incluirJogador(soccerPlayer);

        Jogador jogador = jogadorMapper.soccerPlayerToJogador(soccerPlayer);

        URI uri = uriCB.buildAndExpand().toUri();

        return ResponseEntity.created(uri).body(jogador);
    }

    @GetMapping("/v1/jogadores/{id}")
    public ResponseEntity<DetalheJogador> consultarJogador(@PathVariable Long id){

        SoccerPlayer model = jogadorService.buscarJogador(id);

        return ResponseEntity.ok(jogadorMapper.soccerPlayerToDetalheJogador(model));
    }

    @PutMapping("/v1/jogadores/{id}")
    public ResponseEntity<Jogador> atualizarJogador(@PathVariable Long id, @Valid @RequestBody JogadorFormPut jogadorFormPut){

        SoccerPlayer soccerPlayer = jogadorMapper.jogadorFormPutToSoccerPlayer(jogadorFormPut);

        jogadorService.alterarJogador(id,soccerPlayer);

        Jogador jogador = jogadorMapper.soccerPlayerToJogador(soccerPlayer);

        return ResponseEntity.ok(jogador);
    }

    @DeleteMapping("/v1/jogadores/{id}")
    public ResponseEntity<?> excluirJogador(@PathVariable Long id){

        jogadorService.deletarJogador(id);

        return ResponseEntity.noContent().build();
    }
}
