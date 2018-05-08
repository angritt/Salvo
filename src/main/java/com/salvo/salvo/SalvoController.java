package com.salvo.salvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RequestMapping("/api")
@RestController
public class SalvoController {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private PlayerRepository playerRepository;

    //Gets Player Information for each game
    private Map<String, Object> makePlayersDTO(Player player){
        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        dto.put("id", player.getPlayerId());
        dto.put("userName", player.getUserName());
//        dto.put("GamePlayers", makeGamePlayersDTO(game.getPlayers()));
        return dto;
    }

//    //Gets Game Information - Should call makePlayersDTO for specific player information
    private Map<String, Object> makeGamesDTO(Game game) {
        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        dto.put("id", game.getGameId());
        dto.put("GameDate", game.getGameDate());
        dto.put("GamePlayers", getPlayers());
//        dto.put("GamePlayers", game.getPlayers());
        return dto;
    }

    @RequestMapping("/games")
    public List<Map<String, Object>> getGames() {
        return gameRepository
                .findAll()
                .stream()
                .map(game -> makeGamesDTO(game))
                .collect(Collectors.toList());
    }

    public List<Map<String, Object>> getPlayers() {
        return playerRepository
                .findAll()
                .stream()
                .map(player -> makePlayersDTO(player))
                .collect(Collectors.toList());
    }


}