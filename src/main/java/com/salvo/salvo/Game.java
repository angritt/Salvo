package com.salvo.salvo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private Date gameDate;


    public Game(){
        this.gameDate = new Date();
    }

//    public Date addHour(){
//        gameDate = Date.from(gameDate.toInstant().plusSeconds(3600));
//        return this.gameDate;
//    }

    public Date getGameDate() {
        return this.gameDate;
    }

    public void setGameDate(Date gameDate) {
        this.gameDate = gameDate;
    }

}
