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
                                      ShipRepository shipRepository,
                                      SalvoRepository salvoRepository) {

        return (args) -> {

            //Make Players - These are the players of all games, each can play many games but games only have 2 players
            Player p1 = new Player("Jack", "Bauer", "j.bauer@ctu.gov");
            Player p2 = new Player("Chloe", "O'Brian", "c.obrian@ctu.gov");
            Player p3 = new Player("Tony", "Almeida", "t.almeida@ctu.gov");
            Player p4 = new Player("Kim", "Bauer", "kim_bauer@gmail.com");
            Player p5 = new Player("David", "Palmer", "d.palmer@whitehouse.gov");

            //Make Games - These are all the games
            Game g1 = new Game();
            Game g2 = new Game();
            Game g3 = new Game();
            Game g4 = new Game();
            Game g5 = new Game();
            Game g6 = new Game();
            Game g7 = new Game();
            Game g8 = new Game();

            // save players
            playerRepository.save(p1);
            playerRepository.save(p2);
            playerRepository.save(p3);
            playerRepository.save(p4);
            playerRepository.save(p5);

            //save games
            gameRepository.save(g1);
            gameRepository.save(g2);
            gameRepository.save(g3);
            gameRepository.save(g4);
            gameRepository.save(g5);
            gameRepository.save(g6);
            gameRepository.save(g7);
            gameRepository.save(g8);

            //Make Ships with locations for each player of each game (game + player + ship + location
            // Game 1
            Ship gp1Ship1 = new Ship("Destroyer", Arrays.asList("H2", "H3", "H4"));
            Ship gp1Ship2 = new Ship("Submarine", Arrays.asList("E1", "F1", "G1"));
            Ship gp1Ship3 = new Ship("PatrolBoat", Arrays.asList("B4", "B5"));
            Ship gp2Ship1 = new Ship("Destroyer", Arrays.asList("B5", "C5", "D5"));
            Ship gp2Ship2 = new Ship("PatrolBoat", Arrays.asList("F1", "F2"));
            // Game 2
            Ship gp3Ship1 = new Ship("Destroyer", Arrays.asList("B5", "C5", "D5"));
            Ship gp3Ship2 = new Ship("PatrolBoat", Arrays.asList("C6", "C7"));
            Ship gp4Ship1 = new Ship("Submarine", Arrays.asList("A2", "A3", "A4"));
            Ship gp4Ship2 = new Ship("PatrolBoat", Arrays.asList("G6", "H6"));
            // Game 3
            Ship gp5Ship1 = new Ship("Destroyer", Arrays.asList("B5", "C5", "D5"));
            Ship gp5Ship2 = new Ship("PatrolBoat", Arrays.asList("C6", "C7"));
            Ship gp6Ship1 = new Ship("Submarine", Arrays.asList("A2", "A3", "A4"));
            Ship gp6Ship2 = new Ship("Patrol Boat", Arrays.asList("G6", "H6"));
            // Game 4
            Ship gp7Ship1 = new Ship("Destroyer", Arrays.asList("B5", "C5", "D5"));
            Ship gp7Ship2 = new Ship("PatrolBoat", Arrays.asList("C6", "C7"));
            Ship gp8Ship1 = new Ship("Submarine", Arrays.asList("A2", "A3", "A4"));
            Ship gp8Ship2 = new Ship("Patrol Boat", Arrays.asList("G6", "H6"));

            // Game 5
            Ship gp9Ship1 = new Ship("Destroyer", Arrays.asList("B5", "C5", "D5"));
            Ship gp9Ship2 = new Ship("PatrolBoat", Arrays.asList("C6", "C7"));
            Ship gp10Ship1 = new Ship("Submarine", Arrays.asList("A2", "A3", "A4"));
            Ship gp10Ship2 = new Ship("Patrol Boat", Arrays.asList("G6", "H6"));

            // Game 6 - Only One Player per Testbed
            Ship gp11Ship1 = new Ship("Destroyer", Arrays.asList("B5", "C5", "D5"));
            Ship gp11Ship2 = new Ship("PatrolBoat", Arrays.asList("C6", "C7"));

            // Game 7 - Left Empty per Testbed

            // Game 8
            Ship gp15Ship1 = new Ship("Destroyer", Arrays.asList("B5", "C5", "D5"));
            Ship gp15Ship2 = new Ship("PatrolBoat", Arrays.asList("C6", "C7"));
            Ship gp16Ship1 = new Ship("Submarine", Arrays.asList("A2", "A3", "A4"));
            Ship gp16Ship2 = new Ship("Patrol Boat", Arrays.asList("G6", "H6"));

            //Make Salvoes with locations for each player of each game (game + player + salvoes + location
            Salvo gp1Salvo1 = new Salvo("1", Arrays.asList("B5", "C5", "F1"));
            Salvo gp2Salvo1 = new Salvo("1", Arrays.asList("B4", "B5", "B6"));
            Salvo gp1Salvo2 = new Salvo("2", Arrays.asList("F2", "D5"));
            Salvo gp2Salvo2 = new Salvo("2", Arrays.asList("E1", "H3", "A2"));

            Salvo gp3Salvo1 = new Salvo("1", Arrays.asList("A2", "A4", "A6"));
            Salvo gp4Salvo1 = new Salvo("1", Arrays.asList("B5", "D5", "C7"));
            Salvo gp3Salvo2 = new Salvo("2", Arrays.asList("H3", "H6"));
            Salvo gp4Salvo2 = new Salvo("2", Arrays.asList("C5", "C6"));

            Salvo gp5Salvo1 = new Salvo("1", Arrays.asList("G6", "H6", "A4"));
            Salvo gp6Salvo1 = new Salvo("1", Arrays.asList("H1", "H2", "H3"));
            Salvo gp5Salvo2 = new Salvo("2", Arrays.asList("A2", "A3", "D8"));
            Salvo gp6Salvo2 = new Salvo("2", Arrays.asList("E1", "F2", "G3"));

            Salvo gp7Salvo1 = new Salvo("1", Arrays.asList("A3", "A4", "F7"));
            Salvo gp8Salvo1 = new Salvo("1", Arrays.asList("B5", "C6", "H1"));
            Salvo gp7Salvo2 = new Salvo("2", Arrays.asList("A2", "G6", "H6"));
            Salvo gp8Salvo2 = new Salvo("2", Arrays.asList("C5", "C7", "D5"));

            Salvo gp9Salvo1 = new Salvo("1", Arrays.asList("A1", "A2", "F3"));
            Salvo gp10Salvo1 = new Salvo("1", Arrays.asList("B5", "B6", "C7"));
            Salvo gp9Salvo2 = new Salvo("2", Arrays.asList("G6", "G7", "G8"));
            Salvo gp10Salvo2 = new Salvo("2", Arrays.asList("C6", "D6", "E6"));
            Salvo gp10Salvo3 = new Salvo("3", Arrays.asList("H1", "H8"));


            //Assign 2 players to each game, ships and salvoes to each player // game + player = game player
            GamePlayer gp1 = new GamePlayer(g1, p1);
            GamePlayer gp2 = new GamePlayer(g1, p2);
            gp1.addShip(gp1Ship1);
            gp1.addShip(gp1Ship2);
            gp1.addShip(gp1Ship3);
            gp2.addShip(gp2Ship1);
            gp2.addShip(gp2Ship2);
            gp1.addSalvo(gp1Salvo1);
            gp1.addSalvo(gp1Salvo2);
            gp2.addSalvo(gp2Salvo1);
            gp2.addSalvo(gp2Salvo2);

            GamePlayer gp3 = new GamePlayer(g2, p1);
            GamePlayer gp4 = new GamePlayer(g2, p2);
            gp3.addShip(gp3Ship1);
            gp3.addShip(gp3Ship2);
            gp4.addShip(gp4Ship1);
            gp4.addShip(gp4Ship2);
            gp3.addSalvo(gp3Salvo1);
            gp3.addSalvo(gp3Salvo2);
            gp4.addSalvo(gp4Salvo1);
            gp4.addSalvo(gp4Salvo2);

            GamePlayer gp5 = new GamePlayer(g3, p2);
            GamePlayer gp6 = new GamePlayer(g3, p3);
            gp5.addShip(gp5Ship1);
            gp5.addShip(gp5Ship2);
            gp6.addShip(gp6Ship1);
            gp6.addShip(gp6Ship2);
            gp5.addSalvo(gp5Salvo1);
            gp5.addSalvo(gp5Salvo2);
            gp6.addSalvo(gp6Salvo1);
            gp6.addSalvo(gp6Salvo2);

            GamePlayer gp7 = new GamePlayer(g4, p2);
            GamePlayer gp8 = new GamePlayer(g4, p1);
            gp7.addShip(gp7Ship1);
            gp7.addShip(gp7Ship2);
            gp8.addShip(gp8Ship1);
            gp8.addShip(gp8Ship2);
            gp7.addSalvo(gp7Salvo1);
            gp7.addSalvo(gp7Salvo2);
            gp8.addSalvo(gp8Salvo1);
            gp8.addSalvo(gp8Salvo2);

            GamePlayer gp9 = new GamePlayer(g5, p3);
            GamePlayer gp10 = new GamePlayer(g5, p1);
            gp9.addShip(gp9Ship1);
            gp9.addShip(gp9Ship2);
            gp10.addShip(gp10Ship1);
            gp10.addShip(gp10Ship2);
            gp9.addSalvo(gp9Salvo1);
            gp9.addSalvo(gp9Salvo2);
            gp10.addSalvo(gp10Salvo1);
            gp10.addSalvo(gp10Salvo2);
            gp10.addSalvo(gp10Salvo3);

            GamePlayer gp11 = new GamePlayer(g6, p4);
            GamePlayer gp12 = new GamePlayer(g6, null);
            gp11.addShip(gp11Ship1);
            gp11.addShip(gp11Ship2);

            GamePlayer gp13 = new GamePlayer(g7, null);
            GamePlayer gp14 = new GamePlayer(g7, null);

            GamePlayer gp15 = new GamePlayer(g8, p3);
            GamePlayer gp16 = new GamePlayer(g8, p4);
            gp15.addShip(gp15Ship1);
            gp15.addShip(gp15Ship2);
            gp16.addShip(gp16Ship1);
            gp16.addShip(gp16Ship2);


            //Save players and their ships
            gamePlayerRepository.save(gp1);
            shipRepository.save(gp1Ship1);
            shipRepository.save(gp1Ship2);
            shipRepository.save(gp1Ship3);
            salvoRepository.save(gp1Salvo1);
            salvoRepository.save(gp1Salvo2);

            gamePlayerRepository.save(gp2);
            shipRepository.save(gp2Ship1);
            shipRepository.save(gp2Ship2);
            salvoRepository.save(gp2Salvo1);
            salvoRepository.save(gp2Salvo2);


            gamePlayerRepository.save(gp3);
            shipRepository.save(gp3Ship1);
            shipRepository.save(gp3Ship2);
            salvoRepository.save(gp3Salvo1);
            salvoRepository.save(gp3Salvo2);

            gamePlayerRepository.save(gp4);
            shipRepository.save(gp4Ship1);
            shipRepository.save(gp4Ship2);
            salvoRepository.save(gp4Salvo1);
            salvoRepository.save(gp4Salvo2);

            gamePlayerRepository.save(gp5);
            shipRepository.save(gp5Ship1);
            shipRepository.save(gp5Ship2);
            salvoRepository.save(gp5Salvo1);
            salvoRepository.save(gp5Salvo2);

            gamePlayerRepository.save(gp6);
            shipRepository.save(gp6Ship1);
            shipRepository.save(gp6Ship2);
            salvoRepository.save(gp6Salvo1);
            salvoRepository.save(gp6Salvo2);

            gamePlayerRepository.save(gp7);
            shipRepository.save(gp7Ship1);
            shipRepository.save(gp7Ship2);
            salvoRepository.save(gp7Salvo1);
            salvoRepository.save(gp7Salvo2);

            gamePlayerRepository.save(gp8);
            shipRepository.save(gp8Ship1);
            shipRepository.save(gp8Ship2);
            salvoRepository.save(gp8Salvo1);
            salvoRepository.save(gp8Salvo2);

            gamePlayerRepository.save(gp9);
            shipRepository.save(gp9Ship1);
            shipRepository.save(gp9Ship2);
            salvoRepository.save(gp9Salvo1);
            salvoRepository.save(gp9Salvo2);

            gamePlayerRepository.save(gp10);
            shipRepository.save(gp10Ship1);
            shipRepository.save(gp10Ship2);
            salvoRepository.save(gp10Salvo1);
            salvoRepository.save(gp10Salvo2);

            gamePlayerRepository.save(gp11);
            shipRepository.save(gp11Ship1);
            shipRepository.save(gp11Ship2);

            gamePlayerRepository.save(gp12);

            gamePlayerRepository.save(gp13);

            gamePlayerRepository.save(gp14);

            gamePlayerRepository.save(gp15);
            shipRepository.save(gp15Ship1);
            shipRepository.save(gp15Ship2);

            gamePlayerRepository.save(gp16);
            shipRepository.save(gp16Ship1);
            shipRepository.save(gp16Ship2);
        };
    }

}
