var obj = {};

$(function () {

    var map = L.map('map').setView([51.505, -0.09], 13);

    obj.map = map;

    jsToJavaBridge.map = map;

    L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
        attribution: '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
    }).addTo(map);




});