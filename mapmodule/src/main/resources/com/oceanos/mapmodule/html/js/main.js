var obj = {};

var group = L.layerGroup();

$(function () {



    var map = L.map('map',{editable: true}).setView([51.505, -0.09], 13);

    obj.map = map;

    addToolBar(map);


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
        if (e.layer instanceof L.Polyline){
            javaBridge.logMsg("add new PolyLine");
            group.addLayer(e.layer);
        }
    });

    //TODO: перенести методы создания и изменения слоёв в отдельный объект
    map.on("click",function (e) {
        javaBridge.clickToMap(e.latlng);
/*

            //console.log(e.getSome());

            javaBridge.logMsg("click")
            var marker = L.marker(e.latlng,{draggable:true}).addTo(map);

            javaBridge.logMsg("try add from js "+marker._leaflet_id+" "+e.latlng.lat);
            javaBridge.addMarker(marker._leaflet_id, e.latlng.lat, e.latlng.lng);

            marker.on('click ', function (event) {

            });

            marker.on('mousedown', function () {
                console.log("click marker");
                javaBridge.clickMarker(marker._leaflet_id);
            })

            marker.on('move', function () {
                marker.getTooltip().setContent("lat "+marker.getLatLng().lat)
            })



            marker.on('moveend', function (event) {
                javaBridge.logMsg("change marker "+event.sourceTarget._leaflet_id +" "+ event.sourceTarget.getLatLng().lat +"  "+ event.sourceTarget.getLatLng().lng);
                javaBridge.changeMarker(event.sourceTarget._leaflet_id, event.sourceTarget.getLatLng().lat, event.sourceTarget.getLatLng().lng)
            })*/

    })





});

