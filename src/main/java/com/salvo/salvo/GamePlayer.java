package com.salvo.salvo;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class GamePlayer {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    public long getGamePlayerId() {
        return this.id;
    }

    private Date creationDate = new Date();

    public Date getCreationDate() {
        return creationDate;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="player_id")
    private Player player;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="game_id")
    private Game game;

    public GamePlayer() {}

    public GamePlayer(Game game, Player player){
        this.player = player;
        this.game = game;
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Game getGame() {
        return this.game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

}
