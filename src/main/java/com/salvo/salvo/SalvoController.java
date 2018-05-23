package com.salvo.salvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @Autowired
    private GamePlayerRepository gamePlayerRepository;

    @Autowired
    private ShipRepository shipRepository;

    //Gets Game Information - Should call makePlayersDTO for specific player information
    private Map<String, Object> makeGamesDTO(Game game) {
        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        dto.put("game_id", game.getGameId());
        dto.put("gameDate", game.getGameDate());
        dto.put("gamePlayers", game.getGamePlayers().stream()
                .map(gamePlayer -> makeGamePlayersDTO(gamePlayer))
                .collect(Collectors.toList()));
        return dto;
    }

    //Gets Player Information for each game - calls makeShipsDTO for each player's ships
    private Map<String, Object> makeGamePlayersDTO(GamePlayer gamePlayer){
        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        dto.put("gamePlayer_id", gamePlayer.getGamePlayerId());
        dto.put("players", makePlayersDTO(gamePlayer.getPlayer()));
        dto.put("ships", gamePlayer.getShips().stream().map(ship -> makeShipsDTO(ship)).collect((Collectors.toList())));
        return dto;
    }

    //Gets Each Player's Information
    private Map<String, Object> makePlayersDTO(Player player){
        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        dto.put("player_id", player.getPlayerId());
        dto.put("firstName", player.getFirstName());
        dto.put("userName", player.getUserName());
        return dto;
    }

    //Gets Each Ship;s Information
    private Map<String, Object> makeShipsDTO(Ship ship) {
        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        dto.put("id", ship.getShipId());
        dto.put("shipType", ship.getShipType());
        dto.put("locations", ship.getLocations());
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

    //Game View Mapping - get games & ships (with locations) for gamePlayer
    @RequestMapping("/games/gamePlayers")
    public List<Map<String, Object>> games() {
        return gameRepository
                .findAll()
                .stream()
                .map(game -> makeGamesDTO(game))
                .collect(Collectors.toList());
    }

//    public List<Map<String, Object>> ships() {
//        return shipRepository
//                .findAll()
//                .stream()
//                .map(ship -> makeShipsDTO(ship))
//                .collect(Collectors.toList());
//    }

    @RequestMapping("/game_view/{nn}")
    public Map<String, Object> gameViewInfo(@PathVariable Long nn) {
        GamePlayer currentGP = gamePlayerRepository.findOne(nn);

        Map<String, Object> gamePlayerMap = new HashMap<>();

        gamePlayerMap.put("gamePlayerID", currentGP.getGamePlayerId());
        gamePlayerMap.put("creationDate", currentGP.getCreationDate());
        gamePlayerMap.put("playerID", currentGP.getPlayer().getPlayerId());
        gamePlayerMap.put("playerUsername", currentGP.getPlayer().getUserName());
        gamePlayerMap.put("playerShips", currentGP.getShips());

        return gamePlayerMap;
    }

}