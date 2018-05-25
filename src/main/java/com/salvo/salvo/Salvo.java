package com.salvo.salvo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Salvo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String turn;

    public long getSalvoId() {
        return id;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gamePlayer_id")
    private GamePlayer gamePlayer;

    public Salvo() {
    }

    public Salvo(String turn, List<String> cell) {
        this.turn = turn;
        this.cells = cell;
    }

    @ElementCollection
    @Column(name = "cells")
    private List<String> cells = new ArrayList<>();

    public List<String> getCells() {
        return cells;
    }

    public String getTurn() {
        return turn;
    }

    public void setCells(List<String> cells) {
        this.cells = cells;
    }

    public void setGamePlayer(GamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;
    }
}
