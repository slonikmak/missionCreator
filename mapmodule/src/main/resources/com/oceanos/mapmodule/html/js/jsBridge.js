var jsToJavaBridge = {


    echo: function (msg) {
        javaBridge.logMsg(msg)
    },

    addMarker: function (lat, lng, options) {
        console.log(JSON.parse(options));

        var marker = L.marker([lat, lng], JSON.parse(options)).addTo(obj.map);
        marker.on("click", function (event) {
            javaBridge.clickMarker(marker._leaflet_id);
        });
        marker.on("move", function (event) {
            javaBridge.moveMarker(marker);
        });
        //javaBridge.logMsg("add marker "+id+" "+lat+" "+lng);
        return marker._leaflet_id;
    },

    changeMarker: function (id, newLat, newLng) {
        javaBridge.logMsg("change marker " + id + " " + newLat + " " + newLng);
        group.getLayer(id).setLatLng(L.latLng(newLat, newLng));
    },

    bindPopup: function (id, msg) {
        group.getLayer(id).bindPopup("id " + id + " msg " + msg);
        console.log(group.getLayer(id).getPopup().getContent())
    },

    bindTooltip: function (id, msg) {
        group.getLayer(id).bindTooltip("id " + id + " msg " + msg);
        console.log(group.getLayer(id).getTooltip())
    },

    addPolyLine: function (l) {
        console.log(JSON.parse(l));
        var latlngs = JSON.parse(l);
        var polyline = L.polyline(latlngs, {color: 'blue'}).addTo(obj.map);
// zoom the map to the polyline
        obj.map.fitBounds(polyline.getBounds());
        return polyline._leaflet_id;
    },

    addPointToLine: function (id, lat, lng) {
        group.getLayer(id).addLatLng(L.latLng(lat,lng));
        group.getLayer(id).redraw();
        console.log(group.getLayer(id))
    },

    startPolyline: function () {
        var line = obj.map.editTools.startPolyline();

        line.on('editable:vertex:new', function (e) {
            console.log("edit")
        })

        return line._leaflet_id;
    },
    getMapId: function () {
        console.log(obj.map);
        return obj.map._leaflet_id;
    },

    showPopup: function (id) {
        console.log("show " + group.getLayer(id).getPopup().getContent());
    }


};