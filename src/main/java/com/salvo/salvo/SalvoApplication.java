package com.salvo.salvo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class SalvoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SalvoApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(PlayerRepository playerRepository,
                                      GameRepository gameRepository,
                                      GamePlayerRepository gamePlayerRepository,
                                      ShipRepository shipRepository) {

        return (args) -> {

        //Make Players - These are the players of all games, each can play many games but games only have 2 players
        Player p1 = new Player("Jack", "Bauer", "j.bauer@ctu.gov");
        Player p2 = new Player("Chloe", "O'Brian", "c.obrian@ctu.gov");
        Player p3 = new Player("Tony", "Almeida", "t.almeida@ctu.gov");
        Player p4 = new Player("David", "Palmer", "d.palmer@whitehouse.gov");

        //Make Games - These are all the games
        Game g1 = new Game();
        Game g2 = new Game();
        Game g3 = new Game();
        Game g4 = new Game();
        Game g5 = new Game();
        Game g6 = new Game();

        //Make Ships with locations for each player of each game (game + player + ship + location)
        Ship gp1Ship1 = new Ship("Battleship", Arrays.asList("A1", "A2", "A3", "A4"));
        Ship gp1Ship2 = new Ship("Destroyer", Arrays.asList("B1", "B2", "B3"));
        Ship gp1Ship3 = new Ship("Cruiser", Arrays.asList("C1", "C2"));
        Ship gp2Ship1 = new Ship("Cruiser", Arrays.asList("C1", "C2"));
        Ship gp2Ship2 = new Ship("Destroyer", Arrays.asList("B1", "B2", "B3"));
        Ship gp2Ship3 = new Ship("Sunk", Arrays.asList("00","00"));

        Ship gp3Ship1 = new Ship("Battleship", Arrays.asList("C1", "D1", "E1", "F1"));
        Ship gp3Ship2 = new Ship("Destroyer", Arrays.asList("D3", "D4", "D5"));
        Ship gp3Ship3 = new Ship("Cruiser", Arrays.asList("C3", "C4"));
        Ship gp4Ship1 = new Ship("Cruiser", Arrays.asList("D1", "D2"));
        Ship gp4Ship2 = new Ship("Destroyer", Arrays.asList("B6", "C6", "D6"));
        Ship gp4Ship3 = new Ship("Battleship", Arrays.asList("F3", "F4", "F5", "F6"));

        Ship gp5Ship1 = new Ship("Battleship", Arrays.asList("C2", "D2", "E2", "F2"));
        Ship gp5Ship2 = new Ship("Destroyer", Arrays.asList("D3", "D4", "D5"));
        Ship gp5Ship3 = new Ship("Cruiser", Arrays.asList("C7", "C8"));
        Ship gp6Ship1 = new Ship("Cruiser", Arrays.asList("D5", "D6"));
        Ship gp6Ship2 = new Ship("Destroyer", Arrays.asList("B3", "C3", "D3"));
        Ship gp6Ship3 = new Ship("Battleship", Arrays.asList("H3", "H4", "H5", "H6"));

        // save players
        playerRepository.save(p1);
        playerRepository.save(p2);
        playerRepository.save(p3);
        playerRepository.save(p4);

        //save games
        gameRepository.save(g1);
        gameRepository.save(g2);
        gameRepository.save(g3);
        gameRepository.save(g4);
        gameRepository.save(g5);
        gameRepository.save(g6);


        //Assign 2 players to each game and ships to each player // game + player = game player
        GamePlayer gp1 = new GamePlayer(g1,p1);
        GamePlayer gp2 = new GamePlayer(g1,p2);
        gp1.addShip(gp1Ship1);
        gp1.addShip(gp1Ship2);
        gp1.addShip(gp1Ship3);
        gp2.addShip(gp2Ship1);
        gp2.addShip(gp2Ship2);
        gp2.addShip(gp2Ship3);

        GamePlayer gp3 = new GamePlayer(g2,p1);
        GamePlayer gp4 = new GamePlayer(g2,p2);
        gp3.addShip(gp3Ship1);
        gp3.addShip(gp3Ship2);
        gp3.addShip(gp3Ship3);
        gp4.addShip(gp4Ship1);
        gp4.addShip(gp4Ship2);
        gp4.addShip(gp4Ship3);

        GamePlayer gp5 = new GamePlayer(g3,p1);
        GamePlayer gp6 = new GamePlayer(g3,p2);
        gp5.addShip(gp5Ship1);
        gp5.addShip(gp5Ship2);
        gp5.addShip(gp5Ship3);
        gp6.addShip(gp6Ship1);
        gp6.addShip(gp6Ship2);
        gp6.addShip(gp6Ship3);

        //Save players and their ships
        gamePlayerRepository.save(gp1);
        shipRepository.save(gp1Ship1);
        shipRepository.save(gp1Ship2);
        shipRepository.save(gp1Ship3);

        gamePlayerRepository.save(gp2);
        shipRepository.save(gp2Ship1);
        shipRepository.save(gp2Ship2);
        shipRepository.save(gp2Ship3);

        gamePlayerRepository.save(gp3);
        shipRepository.save(gp3Ship1);
        shipRepository.save(gp3Ship2);
        shipRepository.save(gp3Ship3);

        gamePlayerRepository.save(gp4);
        shipRepository.save(gp4Ship1);
        shipRepository.save(gp4Ship2);
        shipRepository.save(gp4Ship3);

        gamePlayerRepository.save(gp5);
        shipRepository.save(gp5Ship1);
        shipRepository.save(gp5Ship2);
        shipRepository.save(gp5Ship3);

        gamePlayerRepository.save(gp6);
        shipRepository.save(gp6Ship1);
        shipRepository.save(gp6Ship2);
        shipRepository.save(gp6Ship3);


//            GamePlayer gp7 = new GamePlayer(g4,p1);
//            gp7.setShips(st1);
//            gp7.setShips(st3);
//            GamePlayer gp8 = new GamePlayer(g4,p2);
//            gp8.setShips(st1);
//            gp8.setShips(st2);
//            gp8.setShips(st3);
//            gamePlayerRepository.save(gp7);
//            gamePlayerRepository.save(gp8);
//
//            GamePlayer gp9 = new GamePlayer(g5,p3);
//            gp9.setShips(st1);
//            gp9.setShips(st2);
//            GamePlayer gp10 = new GamePlayer(g5,p1);
//            gp10.setShips(st1);
//            gp10.setShips(st2);
//            gp10.setShips(st3);
//            gamePlayerRepository.save(gp9);
//            gamePlayerRepository.save(gp10);
//
//            GamePlayer gp11 = new GamePlayer(g6,p4);
//            gp11.setShips(st1);
//            gp11.setShips(st2);
//            gp11.setShips(st3);
//            gamePlayerRepository.save(gp11);

        };
    }

}
