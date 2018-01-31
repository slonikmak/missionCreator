var obj = {};

var group = L.layerGroup();

$(function () {



    var map = L.map('map').setView([51.505, -0.09], 13);

    obj.map = map;


    L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
        attribution: '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
    }).addTo(map);


    map.on("layeradd ", function (e) {
        if (e.layer instanceof L.Marker){
            javaBridge.logMsg("add new Marker");
            group.addLayer(e.layer);
            javaBridge.addMarker(e.layer._leaflet_id, e.layer.getLatLng().lat,e.layer.getLatLng().lng);
        }
    });

    map.on("click",function (e) {
        javaBridge.logMsg("click")
    })





});