package br.com.rest.api.spring.mscorinthans.mappers;

import br.com.rest.api.spring.mscorinthans.dto.Jogador;
import br.com.rest.api.spring.mscorinthans.models.SoccerPlayer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JogadorMapper {

    Jogador soccerPlayerToJogador(SoccerPlayer soccerPlayer);

}
