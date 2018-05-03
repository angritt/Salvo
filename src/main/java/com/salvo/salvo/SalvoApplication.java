package com.salvo.salvo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Date;

@SpringBootApplication
public class SalvoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalvoApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(PlayerRepository playerRepository, GameRepository gameRepository) {
		return (args) -> {
			// save a few players
			playerRepository.save(new Player("Jack", "Bauer", "J.B@exaple.com"));
			playerRepository.save(new Player("Chloe", "O'Brian", "COB@example.com"));
			playerRepository.save(new Player("Kim", "Bauer","Ms.Bauer@example.com"));
			playerRepository.save(new Player("David", "Palmer", "NoGolf@example.com"));
			playerRepository.save(new Player("Michelle", "Dessler", "NotKessler@example.com"));


			gameRepository.save(new Game());
			gameRepository.save(new Game());
			gameRepository.save(new Game());

		};
	}

}
