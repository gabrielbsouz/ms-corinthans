package br.com.rest.api.spring.mscorinthans.controllers;

import br.com.rest.api.spring.mscorinthans.dto.DetalheJogador;
import br.com.rest.api.spring.mscorinthans.mappers.JogadorMapper;
import br.com.rest.api.spring.mscorinthans.models.SoccerPlayer;
import br.com.rest.api.spring.mscorinthans.services.JogadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/api/corinthans")
public class JogadorController {

    @Autowired
    private JogadorService jogadorService;

    @Autowired
    private JogadorMapper jogadorMapper;

    @GetMapping("/v1/jogadores/{id}")
    public ResponseEntity<DetalheJogador> consultarJogador(@PathVariable Long id){

        SoccerPlayer model = jogadorService.buscarJogador(id);

        return ResponseEntity.ok(jogadorMapper.soccerPlayerToDetalheJogador(model));
    }
}
