// /api/home/{fixtureId}/{homeGoals}
function updateHomeGoals(id, goals) {
    var url = "/api/missing/home/" + id + "/" + goals.value;
    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", url, true);
    xhttp.send();
}

// /api/home/{fixtureId}/{awayGoals}
function updateAwayGoals(id, goals) {
    var url = "/api/missing/away/" + id + "/" + goals.value;
    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", url, true);
    xhttp.send();
}

// /api/predictions/{result}/{id}
function updatePrediction(id, result) {
    var url = "/api/predictions/" + id + "/" + result.value;
    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", url, true);
    xhttp.send();
}
