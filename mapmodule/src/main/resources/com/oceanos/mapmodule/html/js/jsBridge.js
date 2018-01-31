var jsToJavaBridge = {


    echo: function (msg) {
        javaBridge.logMsg(msg)
    },

    addMarker: function (lat, lon) {

        javaBridge.logMsg("add marker ["+lat+", "+lon+"]");

            var marker = L.marker([lat, lon]).addTo(obj.map)
                .bindPopup('A pretty CSS3 popup.<br> Easily customizable.')
                .openPopup();
            return marker._leaflet_id;
    },

    changeMarker: function (id, newLat,newLng) {

    }


};