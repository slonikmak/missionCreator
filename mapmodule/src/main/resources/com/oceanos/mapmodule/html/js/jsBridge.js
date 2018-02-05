var jsToJavaBridge = {


    echo: function (msg) {
        javaBridge.logMsg(msg)
    },

    addMarker: function (lat, lng) {

        javaBridge.logMsg("add marker ["+lat+", "+lng+"]");

            var marker = L.marker([lat, lng]).addTo(obj.map)
                .bindPopup('A pretty CSS3 popup.<br> Easily customizable.')
                .openPopup();
            return marker._leaflet_id;
    },

    changeMarker: function (id, newLat,newLng) {
        javaBridge.logMsg("change marker "+id+" "+newLat+" "+newLng);
        //group.getLayer(id).setLatLng(L.latLng(newLat, newLng))
    }


};