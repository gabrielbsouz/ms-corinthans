package br.com.rest.api.spring.mscorinthans.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class SoccerPlayer {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Position position;
    private String height;
    private int age;
    private String hometown;

}
