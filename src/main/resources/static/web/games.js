$(document).ready(function () {
    var games = [];

    $.getJSON('http://localhost:8080/api/games', function (responseJSON) {

    // $.getJSON('http://localhost:8080/api/game_view/3', function (responseJSON) {

        games = responseJSON;

        $("#players").html("PLAYERS");

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

        $("#table-headers").html(getHeadersHtml());

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
                console.log(row  + " Row ");
                rows = rows +  row;
            });

            return rows
        }

        $("#table-rows").html(getRowsHtml());

        // function getColumnsHtml(row) {
        //     return row.elements.map(function(element) {
        //         return "<td>" + element.distance.text + "</td>";
        //     }).join("")
        // }
        //
        // function getRowsHtml(games) {
        //     return games.rows.map(function(row, i) {
        //         return "<tr><th>" + data.origin_addresses[i] + "</th>" +
        //             getColumnsHtml(row) + "</tr>";
        //     }).join("");
        // }
        //
        // function renderRows(games) {
        //     var html = getRowsHtml(games);
        //     document.getElementById("table-rows").innerHTML = html;
        // }


        // //Setup variables and template to fill in gamesList
        // $.each(games, function (index, games) {
        //     gameId = games.game_id;
        //     gameDate = Date(games.gameDate);
        //     gamePlayer_1 = "";
        //     gamePlayer_2 = "";
        //
        //     for (var i = 0; i < games.gamePlayers.length; i++) {
        //         gamePlayer = games.gamePlayers[i].players.firstName;
        //         userName = games.gamePlayers[i].players.userName;
        //
        //         if (gamePlayer_1 === "" || null) {
        //             gamePlayer_1 = gamePlayer;
        //         } else {
        //             gamePlayer_2 = gamePlayer;
        //         }
        //     }

        // //Setup variables and template to fill in gamesList
        // $.each(games, function (index, games) {
        //     gameId = games.game_id;
        //     gameDate = Date(games.gameDate);
        //     gamePlayer_1 = "";
        //     gamePlayer_2 = "";
        //
        //     for (var i = 0; i < games.gamePlayers.length; i++) {
        //         gamePlayer = games.gamePlayers[i].players.firstName;
        //         userName = games.gamePlayers[i].players.userName;
        //
        //         if (gamePlayer_1 === "" || null) {
        //             gamePlayer_1 = gamePlayer;
        //         } else {
        //             gamePlayer_2 = gamePlayer;
        //         }
        //     }
        //
        //     gameRow = "<tr><td>" + gameId + "</td><td>" + gameDate + "</td><td>" + gamePlayer_1 + " vs. " + gamePlayer_2 + "</td></tr>";
        //     gameRows = gameRows + gameRow;
        //
        // });

            // $("tbody").html(gameRows);

        // document.getElementById('gamesList').innerHTML = gameRows;
    });
})