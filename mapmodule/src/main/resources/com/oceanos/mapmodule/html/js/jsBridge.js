var jsToJavaBridge = {


    echo: function (msg) {
        javaBridge.logMsg(msg)
    },

    addMarker: function (lat, lng) {

            var marker = L.marker([lat, lng]).addTo(obj.map)
                .bindPopup('A pretty CSS3 popup.<br> Easily customizable.')
                .openPopup();
        javaBridge.logMsg("add marker "+id+" "+lat+" "+lng);
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
        console.log(l);
        console.log(JSON.parse(l));
    }



};