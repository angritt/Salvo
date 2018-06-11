$(document).ready(function () {
    var games = [];
    var jsonURL = "";
    var players_ships = [];
    var enemy_ships = [];

    //Get Game Player URL Parameter and Add to JSON request
    $.urlParam = function (gp) {
        var results = new RegExp('(gp=\\d*)').exec(window.location.href);
        if (results == null) {
            setScoreboard()
        }
        else {  //Create GamePlayer View Page
            results = results[1];
            results = results.toString().split("=");
            urlParam = results[1];
            jsonURL = 'http://localhost:8080/api/game_view/' + urlParam;
        }
    }
    $.urlParam();

    //Create GamePlayer Scoreboard
    function setScoreboard(){
        var scoresURL = 'http://localhost:8080/api/games';
        var gamePlayersArr = [];
        var userName;
        var playerScore;

        $.getJSON(scoresURL, function (responseJSON) {
            scoreBoard = responseJSON;
console.log(scoreBoard);
            // Get Array of GamePlayers
            // for (var i =0; i < scoreBoard.length; i++){
            //     // gamePlayersArr.push(scoreBoard[i].gamePlayers);
            //
            //     // $.each(scoreBoard[i].gamePlayers, function (j, gp) {
            //     //     console.log(gp);
            //     //     // console.log(gp.player);
            //     //     console.log(player.player_id);
            //     //
            //     //
            //     //     userName = gp.playerScore.player.userName;
            //     //     playerScore = gp.playerScore.score;
            //     //
            //     //     // playerScore  = { [userName]: gp.playerScore.score };
            //     //     // var arr = [playerScore];
            //     //
            //     //     // arr.forEach(function (playerScore, key, value) {
            //     //     //     if (key = key){
            //     //     //         value = value+value;
            //     //     //     }
            //     //     //     // document.write(value.toString() + "<br />");
            //     //     // });
            //     //
            //     //     // console.log(arr);
            //     // })
            // }
        });
    }


    // Make JSON request for GamePlayer pages
    $.getJSON(jsonURL, function (responseJSON) {
        games = responseJSON;
        // var date = new Date;
        // date = date.toLocaleDateString();

        playerName = games.playerFirstName + " " + games.playerLastName;
        opponentName = games.enemyFirstName + " " + games.enemyLastName;

        $("#player").html("PLAYER: " + playerName);
        $("#opponent").html("OPPONENT: " + opponentName);

        //Shows the locations of the Player's ships
        function getShipLocations() {
            var ship = games.playerShips;
            $.each(ship, function (index, ship) {
                $.each(ship.locations, function (i, loc) {
                    $("#" + loc).css("background-color", "blue");
                    $("#" + loc).css("border-radius", "6px");
                    players_ships.push(loc);
                })
            })
        }

        //Gets the locations of the enemy ships, but does not reveal them
        function getEnemyShipLocations() {
            var ship = games.enemyShips;
            $.each(ship, function (index, ship) {
                $.each(ship.locations, function (i, loc) {
                    opponent_loc = "Enemy_" + loc;
                    enemy_ships.push(opponent_loc);
                })
            })
        }

        //Shots Player has made against Opponent - Hits in red - All salvos show turn number
        function getSalvoes() {
            var salvo = games.enemySalvoes;
            $.each(salvo, function (index, salvo) {
                $.each(salvo.cells, function (i, cell) {
                    opponent_cell = "Enemy_" + cell;
                    $("#" + opponent_cell).css("background-color", "gray");
                    $("#" + opponent_cell).css("border-radius", "0px");
                    $("#" + opponent_cell).css("text-align", "center");
                    $("#" + opponent_cell).css("color", "white");
                    $("#" + opponent_cell).text(salvo.turn);
                    $.each(enemy_ships, function (i, loc) {
                        if (opponent_cell == loc) {
                            $("#" + opponent_cell).css("background-color", "lightblue");
                            $("#" + opponent_cell).css("border-radius", "45%");
                            $("#" + opponent_cell).css("border-color", "lightblue");
                            $("#" + opponent_cell).css("border-width", "2px");
                            $("#" + opponent_cell).css("background-color", "red");
                        }
                    })
                })
            })
        }

        //Checks if enemy scored hits on player ships and marks hit locations
        function getEnemySalvoes() {
            var enemySalvo = games.enemySalvoes;
            $.each(enemySalvo, function (index, enemySalvo) {
                $.each(enemySalvo.cells, function (i, cell) {
                    $.each(players_ships, function (i, loc) {
                        if (cell == loc) {
                            $("#" + cell).css("background-color", "lightblue");
                            $("#" + cell).css("border-radius", "45%");
                            $("#" + cell).css("border-color", "lightblue");
                            $("#" + cell).css("border-width", "2px");
                            $("#" + cell).css("background-color", "red")
                            $("#" + cell).css("text-align", "center");
                            $("#" + cell).css("color", "white");
                            $("#" + cell).text(enemySalvo.turn);
                        }
                    })
                })
            })
        }

        //Create Table Headers 1 - 10 for both players
        function getHeadersHtml() {
            var colheaders = "";
            for (var i = 0; i < 10; i++) {
                var col = 1 + i;
                var colheader = "<th> " + col + " </th>";
                colheaders = colheaders + colheader;
            }
            return "<tr><th class='table-light'></th>" + colheaders + "</tr>"
        }

        //Create Table Rows A - J
        function getRowsHtml() {
            var cell = "";
            var rows = "";
            var letters = ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J"];
            $.each(letters, function (index, letters) {
                var row_cell = "";
                var row = "<tr><td class='table-primary'> " + letters + " </td>";
                for (var i = 0; i < 10; i++) {
                    cell = letters + (1 + i);
                    row_cell = "<td id=" + cell + "> </td>"
                    row = row + row_cell;
                }
                row = row + "</tr>";
                rows = rows + row;
            });
            return rows
        }

        //Create OpponentTable Rows A - J
        function getOpponentRowsHtml() {
            var opponent_cell = "";
            var opponent_rows = "";
            var letters = ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J"];
            $.each(letters, function (index, letters) {
                var opponent_row_cell = "";
                var opponent_row = "<tr><td class='table-primary'> " + letters + " </td>";
                for (var i = 0; i < 10; i++) {
                    opponent_cell = "Enemy_" + letters + (1 + i);
                    opponent_row_cell = "<td id=" + opponent_cell + "> </td>"
                    opponent_row = opponent_row + opponent_row_cell;
                }
                opponent_row = opponent_row + "</tr>";
                opponent_rows = opponent_rows + opponent_row;
            });
            return opponent_rows
        }

        $("#table-headers").html(getHeadersHtml());
        $("#opponent-table-headers").html(getHeadersHtml());
        $("#opponent-table-rows").html(getOpponentRowsHtml());
        $("#table-rows").html(getRowsHtml());

        getShipLocations();
        getEnemyShipLocations();
        getSalvoes();
        getEnemySalvoes();

    });
})