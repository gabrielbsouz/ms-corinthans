package br.com.rest.api.spring.mscorinthans.mappers;

import br.com.rest.api.spring.mscorinthans.dto.DetalheJogador;
import br.com.rest.api.spring.mscorinthans.dto.Jogador;
import br.com.rest.api.spring.mscorinthans.models.SoccerPlayer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface JogadorMapper {

    @Mappings({
            @Mapping(target="nome", source="soccerPlayer.name"),
            @Mapping(target="posicao", source="soccerPlayer.position")
    })
    Jogador soccerPlayerToJogador(SoccerPlayer soccerPlayer);

    List<Jogador> soccerPlayersToJogadores(List<SoccerPlayer> soccerPlayers);

    @Mappings({
            @Mapping(target="nome", source="soccerPlayer.name"),
            @Mapping(target="posicao", source="soccerPlayer.position"),
            @Mapping(target="altura", source="soccerPlayer.height"),
            @Mapping(target="idade", source="soccerPlayer.age"),
            @Mapping(target="cidadeNatal", source="soccerPlayer.hometown")
    })
    DetalheJogador soccerPlayerToDetalheJogador(SoccerPlayer soccerPlayer);

    @Mappings({
            @Mapping(target="name", source="detalheJogador.nome"),
            @Mapping(target="position", source="detalheJogador.posicao"),
            @Mapping(target="height", source="detalheJogador.altura"),
            @Mapping(target="age", source="detalheJogador.idade"),
            @Mapping(target="hometown", source="detalheJogador.cidadeNatal")
    })
    SoccerPlayer detalheJogadorToSoccerPlayer(DetalheJogador detalheJogador);
}
