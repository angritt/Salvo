$(document).ready(function(){
    var games = [];
    var gameRow = "";
    var gameRows = "";

    $.getJSON('http://localhost:8080/api/games', function(responseJSON) {
        games = responseJSON;

    //Setup variables and template to fill in gamesList
        $.each(games, function(index,games) {
            gameId = games.id;
            gameDate = Date(games.gameDate);
            gamePlayer_1 = "";
            gamePlayer_2 = "";

            for (var i=0; i < games.gamePlayers.length; i++){
                gamePlayer = games.gamePlayers[i].first;

                if (gamePlayer_1 === "" || null){
                    gamePlayer_1 = gamePlayer;
                } else {
                    gamePlayer_2 = gamePlayer;
                }
            }

            gameRow = "<li>" + gameId + " " + gameDate + " " + gamePlayer_1 + " vs. " + gamePlayer_2 + "</li>";
            gameRows = gameRows + gameRow;

        });

        document.getElementById('gamesList').innerHTML = gameRows;
    });
})