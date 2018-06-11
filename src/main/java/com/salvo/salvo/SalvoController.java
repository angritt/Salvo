package com.salvo.salvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    private GamePlayerRepository gamePlayerRepository;

    @Autowired
    private ShipRepository shipRepository;

    @Autowired
    private SalvoRepository salvoRepository;

    @Autowired
    private SalvoRepository scoreRepository;

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

    //Gets Player Information for each game
    private Map<String, Object> makeGamePlayersDTO(GamePlayer gamePlayer) {
        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        dto.put("gamePlayer_id", gamePlayer.getGamePlayerId());
        dto.put("player", makePlayersDTO(gamePlayer.getPlayer()));
        dto.put("playerScore", gamePlayer.getScore().score);

        return dto;
    }

    //Gets Each Score Information
    private Map<String, Object> makeScoresDTO(Score score) {
        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        dto.put("id", score.getScoreId());
        dto.put("score", score.getScore());
        dto.put("player", score.getPlayer());
        return dto;
    }

    //Gets Each Player's Information
    private Map<String, Object> makePlayersDTO(Player player) {
        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        dto.put("player_id", player.getPlayerId());
        dto.put("username", player.getUserName());
        return dto;
    }

    //Gets Each Ships Information
    private Map<String, Object> makeShipsDTO(Ship ship) {
        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        dto.put("id", ship.getShipId());
        dto.put("shipType", ship.getShipType());
        dto.put("locations", ship.getLocations());
        return dto;
    }

    //Gets Each Salvoes Information
    private Map<String, Object> makeSalvoesDTO(Salvo salvo) {
        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        dto.put("id", salvo.getSalvoId());
        dto.put("salvoTurn", salvo.getTurn());
        dto.put("cells", salvo.getCells());
        return dto;
    }

    //Game Mapping - get details for each game
    @RequestMapping("/games")
    public List<Map<String, Object>> getGames() {
        return gameRepository
                .findAll()
                .stream()
                .map(game -> makeGamesDTO(game))
                .collect(Collectors.toList());
    }

    //Score Mapping - get details for each score
    @RequestMapping("/scores")
    public Map<String, Object> getScores() {
        Map<String, Object> allPlayers = new HashMap<>();
        List<GamePlayer> allGamePlayers = gamePlayerRepository.findAll();

        for (int i = 0; i < allGamePlayers.size(); i++) {
            String userName = allGamePlayers.get(i).getPlayer().getUserName();

            if (!allPlayers.containsKey(userName)) {
                if (allGamePlayers.get(i).getPlayer().getScores() != null) {
                    Map<String, Object> playerScore = new HashMap<>();

                    //the following 3 lines make a count of the respective values
                    playerScore.put("wins", allGamePlayers.get(i).getPlayer().getScores().stream().filter(score -> score.getScore() != null).filter(score -> score.getScore() == 1.0).count());
                    playerScore.put("losses", allGamePlayers.get(i).getPlayer().getScores().stream().filter(score -> score.getScore() != null).filter(score -> score.getScore() == 0.0).count());
                    playerScore.put("ties", allGamePlayers.get(i).getPlayer().getScores().stream().filter(score -> score.getScore() != null).filter(score -> score.getScore() == 0.5).count());

                    //make the "count" into a string; parse this string to a double, so "total" stays double (it stays double also with int, but whatever))
                    double total_wins = Double.parseDouble(playerScore.get("wins").toString());
                    double total_losses = Double.parseDouble(playerScore.get("losses").toString());
                    double total_ties = Double.parseDouble(playerScore.get("ties").toString());
                    double total = (total_wins * 1) + (total_ties * 0.5);

                    playerScore.put("total", total);

                    allPlayers.put(userName, playerScore);
                }

            }
        }
        return allPlayers;
    }

    //Game Player Mapping - get details for each gamePlayer
    @RequestMapping("/games/gamePlayers")
    public List<Map<String, Object>> games() {
        return gameRepository
                .findAll()
                .stream()
                .map(game -> makeGamesDTO(game))
                .collect(Collectors.toList());
    }

    //Game View Mapping - get complete information for each game
    @RequestMapping("/game_view/{nn}")
    public Map<String, Object> gameViewInfo(@PathVariable Long nn) {
        GamePlayer currentGP = gamePlayerRepository.findOne(nn);

        Map<String, Object> gamePlayerMap = new HashMap<>();

        gamePlayerMap.put("gamePlayerID", currentGP.getGamePlayerId());
        gamePlayerMap.put("creationDate", currentGP.getCreationDate());
        gamePlayerMap.put("gameID", currentGP.getGame().getGameId());
        gamePlayerMap.put("playerID", currentGP.getPlayer().getPlayerId());
        gamePlayerMap.put("playerUsername", currentGP.getPlayer().getUserName());
        gamePlayerMap.put("playerFirstName", currentGP.getPlayer().getFirstName());
        gamePlayerMap.put("playerLastName", currentGP.getPlayer().getLastName());
        gamePlayerMap.put("playerShips", currentGP.getShips());
        gamePlayerMap.put("playerSalvoes", currentGP.getSalvoes());
        gamePlayerMap.put("playerScore", currentGP.getScore().getScoreId());

        gamePlayerMap.put("enemyFirstName", getEnemyPlayer(currentGP).getPlayer().getFirstName());
        gamePlayerMap.put("enemyLastName", getEnemyPlayer(currentGP).getPlayer().getLastName());
        gamePlayerMap.put("enemySalvoes", getEnemyPlayer(currentGP).getSalvoes());
        gamePlayerMap.put("enemyShips", getEnemyPlayer(currentGP).getShips());
        gamePlayerMap.put("enemyScore", getEnemyPlayer(currentGP).getScore().getScoreId());

        return gamePlayerMap;
    }

    private GamePlayer getEnemyPlayer(GamePlayer gamePlayer) {
        Long playerId = gamePlayer.getId();
        Game game = gamePlayer.getGame();

        Set<GamePlayer> gamePlayers = game.getGamePlayers();

        GamePlayer enemyPlayer = gamePlayers.stream()
                .filter(gp -> gp.getId() != playerId).findAny().orElse(null);

        return enemyPlayer;
    }

}