package com.salvo.salvo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SalvoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SalvoApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(PlayerRepository playerRepository,
                                      GameRepository gameRepository,
                                      GamePlayerRepository gamePlayerRepository) {
        return (args) -> {
            // save a few players
            Player p1 = new Player("Jack", "Bauer", "j.bauer@ctu.gov");
            Player p2 = new Player("Chloe", "O'Brian", "c.obrian@ctu.gov");
            Player p3 = new Player("Tony", "Almeida", "t.almeida@ctu.gov");
            Player p4 = new Player("David", "Palmer", "d.palmer@whitehouse.gov");
            playerRepository.save(p1);
            playerRepository.save(p2);
            playerRepository.save(p3);
            playerRepository.save(p4);

            Game g1 = new Game();
            gameRepository.save(g1);
            Game g2 = new Game();
            gameRepository.save(g2);
            Game g3 = new Game();
            gameRepository.save(g3);
            Game g4 = new Game();
            gameRepository.save(g4);
            Game g5 = new Game();
            gameRepository.save(g5);
            Game g6 = new Game();
            gameRepository.save(g6);

            GamePlayer gp1 = new GamePlayer(g1,p1);
            GamePlayer gp2 = new GamePlayer(g1,p2);
            gamePlayerRepository.save(gp1);
            gamePlayerRepository.save(gp2);

            GamePlayer gp3 = new GamePlayer(g2,p1);
            GamePlayer gp4 = new GamePlayer(g2,p2);
            gamePlayerRepository.save(gp3);
            gamePlayerRepository.save(gp4);

            GamePlayer gp5 = new GamePlayer(g3,p2);
            GamePlayer gp6 = new GamePlayer(g3,p3);
            gamePlayerRepository.save(gp5);
            gamePlayerRepository.save(gp6);

            GamePlayer gp7 = new GamePlayer(g4,p1);
            GamePlayer gp8 = new GamePlayer(g4,p2);
            gamePlayerRepository.save(gp7);
            gamePlayerRepository.save(gp8);

            GamePlayer gp9 = new GamePlayer(g5,p3);
            GamePlayer gp10 = new GamePlayer(g5,p1);
            gamePlayerRepository.save(gp9);
            gamePlayerRepository.save(gp10);

            GamePlayer gp11 = new GamePlayer(g6,p4);
            gamePlayerRepository.save(gp11);

        };
    }

}
