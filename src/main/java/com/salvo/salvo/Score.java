package com.salvo.salvo;

import javax.persistence.*;

@Entity
public class Score {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    protected Double score;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="player_id")
    private Player player;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="game_id")
    private Game game;

    public Score() {}

    public Score(Game game, Player player, Double score){
        this.game = game;
        this.player = player;
        this.score = score;
    }

    public long getScoreId() {
        return id;
    }

    public Double getScore() {
        return score;
    }

    public Game getGame() {
        return game;
    }


    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setGame(Game game) {
        this.game = game;
    }

}
