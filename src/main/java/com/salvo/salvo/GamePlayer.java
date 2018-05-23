package com.salvo.salvo;

import javax.persistence.*;
import java.util.*;

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
        return this.creationDate;
    }

    @OneToMany(mappedBy = "gamePlayer", fetch = FetchType.EAGER)
    private Set<Ship> ships = new HashSet<>();

    @ElementCollection
    @Column(name="location")
    private List<String> locations = new ArrayList<>();

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

    public Set<Ship> getShips() {
        return ships;
    }

    public GamePlayer setShips(Ship ship) {
        this.ships.add(ship);
        return null;
    }

    public void addShip(Ship ship){
        this.ships.add(ship);
        ship.setGamePlayer(this);
    }

}
