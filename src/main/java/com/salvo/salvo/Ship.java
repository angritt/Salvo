package com.salvo.salvo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@Entity
public class Ship {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String shipType;

    public long getShipId() {
        return this.id;
    }

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "gamePlayer_id")
    private GamePlayer gamePlayer;


    public Ship(){};

    public Ship(String shipType, List<String> location) {
        this.locations = location;
        this.shipType = shipType;
    }

    @ElementCollection
    @Column(name="locations")
    private List<String> locations = new ArrayList<>();

    public String getShipType() {
        return shipType;
    }

    public List<String> getLocations() {
        return locations;
    }

    public void setGamePlayer(GamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;
    }
}