$(document).ready(function () {
    var games = [];
    var jsonURL = "";

    //Get Game Player URL Parameter and Add to JSON request
    $.urlParam = function(gp){
        var results = new RegExp('(gp=\\d*)').exec(window.location.href);
        results = results[1];
        results = results.toString().split("=");
        urlParam = results[1];
        if (urlParam == null){
            return null; // //Add Error or Start Game page
        }
        else{
            jsonURL = 'http://localhost:8080/api/game_view/' + urlParam;
        }
    }

    $.urlParam();

    $.getJSON(jsonURL, function (responseJSON) {
        games = responseJSON;
        // var date = new Date;
        // date = date.toLocaleDateString();

        playerName = games.playerFirstName + " " + games.playerLastName;
        opponentName = games.enemyFirstName + " " + games.enemyLastName;

        $("#player").html("PLAYER: " + playerName);
        $("#opponent").html("OPPONENT: " + opponentName);

        function getShipLocations(){
            var ship = games.playerShips;
            $.each(ship, function (index,ship) {
                $.each(ship.locations, function (i, loc) {
                    $("#" + loc).css("background-color", "blue");
                    $("#" + loc).css("border-radius", "8px");
                })
            })
        }

        function getSalvoes(){
            var salvo = games.playerSalvoes;
            $.each(salvo, function (index,salvo) {
                $.each(salvo.cells, function (i, cell) {
                    opponent_cell = "Enemy_" + cell;
                    $("#" + opponent_cell).css("background-color", "darkgray");
                    $("#" + opponent_cell).css("border-radius", "0px");
                })
            })
        }

        function getEnemySalvoes(){
            var enemySalvo = games.enemySalvoes;
            $.each(enemySalvo, function (index,enemySalvo) {
                $.each(enemySalvo.cells, function (i, cell) {
                    $("#" + cell).css("background-color", "lightyellow");
                    $("#" + cell).css("border-radius", "0px");
                })
            })
        }

        //Create Table Headers 1 - 10
        function getHeadersHtml() {
            var colheaders = "";
            for (var i = 0; i < 10; i++) {
                var col = 1 + i;
                var colheader = "<th> " +  col + " </th>";
                colheaders = colheaders +  colheader;
            }
            return "<tr><th class='table-light'></th>" + colheaders + "</tr>"
        }

        //Create Table Rows A - J
        function getRowsHtml() {
            var cell = "";
            var rows = "";
            var letters = ["A","B","C","D","E","F","G","H","I","J"];
            $.each(letters, function (index, letters) {
                var row_cell = "";
                var row = "<tr><td class='table-primary'> " +  letters + " </td>";
                for (var i = 0; i < 10; i++) {
                    cell = letters + (1 + i);
                    row_cell = "<td id="+cell+"> </td>"
                    row = row + row_cell;
                }
                row = row + "</tr>";
                rows = rows +  row;
            });

            return rows
        }

        //Create OpponentTable Rows A - J
        function getOpponentRowsHtml() {
            var opponent_cell = "";
            var opponent_rows = "";
            var letters = ["A","B","C","D","E","F","G","H","I","J"];
            $.each(letters, function (index, letters) {
                var opponent_row_cell = "";
                var opponent_row = "<tr><td class='table-primary'> " +  letters + " </td>";
                for (var i = 0; i < 10; i++) {
                    opponent_cell = "Enemy_" + letters + (1 + i);
                    opponent_row_cell = "<td id="+opponent_cell+"> </td>"
                    opponent_row = opponent_row + opponent_row_cell;
                }
                opponent_row = opponent_row + "</tr>";
                opponent_rows = opponent_rows +  opponent_row;
            });
            return opponent_rows
        }

        $("#table-headers").html(getHeadersHtml());
        $("#opponent-table-headers").html(getHeadersHtml());
        $("#table-rows").html(getRowsHtml());
        $("#opponent-table-rows").html(getOpponentRowsHtml());
        getShipLocations();
        getSalvoes();
        getEnemySalvoes();

    });
})