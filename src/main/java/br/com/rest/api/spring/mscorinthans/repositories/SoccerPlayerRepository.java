package br.com.rest.api.spring.mscorinthans.repositories;

import br.com.rest.api.spring.mscorinthans.models.SoccerPlayer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SoccerPlayerRepository extends JpaRepository<Long, SoccerPlayer> {

    SoccerPlayer findByName(String nome);

}
