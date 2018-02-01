var obj = {};

var group = L.layerGroup();

$(function () {



    var map = L.map('map').setView([51.505, -0.09], 13);

    obj.map = map;


    //http://{s}.tiles.mapbox.com/v3/gvenech.m13knc8e/{z}/{x}/{y}.png
    //http://{s}.tile.osm.org/{z}/{x}/{y}.png
    L.tileLayer('http://{s}.tiles.mapbox.com/v3/gvenech.m13knc8e/{z}/{x}/{y}.png', {
        attribution: '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
    }).addTo(map);


    map.on("layeradd ", function (e) {
        if (e.layer instanceof L.Marker){
            javaBridge.logMsg("add new Marker");
            group.addLayer(e.layer);
            map.panTo(e.layer.getLatLng());
            //javaBridge.addMarker(e.layer._leaflet_id, e.layer.getLatLng().lat,e.layer.getLatLng().lng);
        }
    });

    map.on("click",function (e) {

            javaBridge.logMsg("click")
            var marker = L.marker(e.latlng,{draggable:true}).addTo(map);

            javaBridge.logMsg("try add from js "+marker._leaflet_id+" "+e.latlng.lat);
            javaBridge.addMarker(marker._leaflet_id, e.latlng.lat, e.latlng.lng);

            marker.on('click', function (event) {
                console.log("click marker");
                javaBridge.clickMarker(marker._leaflet_id);
            });

            marker.on('dragend', function (event) {
                javaBridge.changeMarker(marker._leaflet_id, e.latlng.lat, e.latlng.lng)
            })

    })





});