var jsToJavaBridge = {


    echo: function (msg) {
        javaBridge.logMsg(msg)
    },

    addMarker: function (lat, lng) {

            var marker = L.marker([lat, lng]).addTo(obj.map)
                .bindPopup('A pretty CSS3 popup.<br> Easily customizable.')
                .openPopup();
        //javaBridge.logMsg("add marker "+id+" "+lat+" "+lng);
            return marker._leaflet_id;
    },

    changeMarker: function (id, newLat,newLng) {
        javaBridge.logMsg("change marker "+id+" "+newLat+" "+newLng);
        group.getLayer(id).setLatLng(L.latLng(newLat, newLng));
    },

    bindPopup: function (id, msg) {
        group.getLayer(id).bindTooltip(id+msg+group.getLayer(id).getLatLng().lat);

    },

    addLine: function (l) {
        console.log(JSON.parse(l));
        var latlngs = JSON.parse(l);
        var polyline = L.polyline(latlngs, {color: 'blue'}).addTo(obj.map);
// zoom the map to the polyline
        obj.map.fitBounds(polyline.getBounds());
        return polyline._leaflet_id;
    },

    startPolyline: function () {
        var line = obj.map.editTools.startPolyline();

        line.on('editable:vertex:new', function (e) {
            console.log("edit")
        })

        return line._leaflet_id;
    }



};