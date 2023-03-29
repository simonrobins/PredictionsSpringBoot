const socket = new WebSocket('ws://localhost:8080/socket');

socket.onopen = function (event) {
    // Maybe set a flag here to indicate that the socket is open.
};

function updatePredictionWS(result, id) {
    var data = "prediction" + ":" + result.value + ":" + id;
    socket.send(data);
}

function updateHomeGoalsWS(goals, id) {
    var data = "home" + ":" + goals.value + ":" + id;
    socket.send(data);
}

function updateAwayGoalsWS(goals, id) {
    var data = "away" + ":" + goals.value + ":" + id;
    socket.send(data);
}

// /api/home/{fixtureId}/{homeGoals}
function updateHomeGoals(id, goals) {
    var url = result.form.action + "/home/" + id + "/" + goals.value;
    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", url, true);
    xhttp.send();
}

// /api/home/{fixtureId}/{awayGoals}
function updateAwayGoals(id, goals) {
    var url = result.form.action + "/away/" + id + "/" + goals.value;
    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", url, true);
    xhttp.send();
}

// /api/prediction/{predictionID}/{result}
function updatePrediction(id, result) {
    var url = result.form.action + "/result/" + id + "/" + result.value;
    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", url, true);
    xhttp.send();
}
