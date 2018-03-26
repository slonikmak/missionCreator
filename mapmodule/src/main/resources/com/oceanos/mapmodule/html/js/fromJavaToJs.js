//
//Объект, содержащий функции для обработки сообщения из JAVA


var fromJavaToJs = {

    echo: function (msg) {
        fromJsToJava.logMsg(msg)
    },

    //FIXME: options must be prepared json string
    addMarker: function (lat, lng, options) {

        var marker = L.marker([lat, lng], JSON.parse(options)).addTo(obj.map);
        marker.on("click", function (event) {
            fromJsToJava.clickMarker(marker._leaflet_id);
        });
        marker.on("move", function (event) {
            fromJsToJava.moveMarker(marker);
        });
        //javaBridge.logMsg("add marker "+id+" "+lat+" "+lng);
        return marker._leaflet_id;
    },

    changeMarker: function (id, newLat, newLng) {
        fromJsToJava.logMsg("change marker " + id + " " + newLat + " " + newLng);
        group.getLayer(id).setLatLng(L.latLng(newLat, newLng));
    },

    bindPopup: function (id, msg) {
        group.getLayer(id).bindPopup(msg);
    },

    bindTooltip: function (id, msg) {
        group.getLayer(id).bindTooltip("id " + id + " msg " + msg);
    },

    addPolyLine: function (l) {
        var latlngs = JSON.parse(l);
        var polyline = L.polyline(latlngs, {color: 'blue'}).addTo(obj.map);
// zoom the map to the polyline
        obj.map.fitBounds(polyline.getBounds());
        return polyline._leaflet_id;
    },

    addPointToLine: function (id, lat, lng) {
        group.getLayer(id).addLatLng(L.latLng(lat, lng));
        group.getLayer(id).redraw();
    },

    startPolyline: function () {
        var line = obj.map.editTools.startPolyline();

        line.on('editable:vertex:new', function (e) {
        });

        return line._leaflet_id;
    },
    getMapId: function () {
        return obj.map._leaflet_id;
    },

    showPopup: function (id) {
        //console.log("show " + group.getLayer(id).getPopup().getContent());
    },

    addCircle: function (lat, lng, radius, options) {

        var circle = L.circle([lat, lng], radius, JSON.parse(options)).addTo(obj.map);
        return circle._leaflet_id;
    },

    changeCircle: function (id, lat, lng) {
        var circle = group.getLayer(id);
        circle.setLatLng(L.latLng(lat, lng));
        circle.redraw();
    },

    //todo: удалить или сделать пригодным для разных типов
    bindCoords: function (from, to) {
        var circle = group.getLayer(to);
        var marker = group.getLayer(from);

        marker.on("move", function (event) {
            //console.log("latlng "+circle._leaflet_id)
            circle.setLatLng(marker.getLatLng());
            circle.redraw();
        })
    },
    setTooltipContent: function (id, content) {
        group.getLayer(id);
    }


};