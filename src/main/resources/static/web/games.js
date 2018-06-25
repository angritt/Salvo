$(document).ready(function () {
    var games = [];
    var jsonURL = "";
    var players_ships = [];
    var enemy_ships = [];

    //Create GamePlayer Scoreboard
    function setScoreboard() {
        var scoresURL = 'http://localhost:8080/api/scores';
        var userName;
        var score;
        var wins;
        var ties;
        var losses;

        $.getJSON(scoresURL, function (responseJSON) {
            scoreBoard = responseJSON;
            var rows = "";
            var row = "";

            for (key in scoreBoard) {
                userName = key;
                score = scoreBoard[key].total;
                wins = scoreBoard[key].wins;
                ties = scoreBoard[key].ties;
                losses = scoreBoard[key].losses;

                if (userName != "none") {
                    row = "<tr><td>" + userName + "</td><td>" + score + "</td><td>" + wins + "</td><td>" + losses + "</td><td>" + ties + "</td></tr>";
                    rows = rows + row;
                }
            }

            $("#table-rows").html(rows);

        });
    }
    setScoreboard();

    $( "#login" ).click(function(evt) {
        evt.preventDefault();
        var form = evt.target.form;

        console.log(form["username"].value);
        console.log(form["password"].value);
        $.post("/api/login",
            { name: form["username"].value,
                pwd: form["password"].value })
            .done(console.log("Success!"))
            .fail(console.log("FAIL"));
    })

    function logout(evt) {
        evt.preventDefault();
        $.post("/api/logout")
            // .done(...)
            // .fail(...);
    }

});