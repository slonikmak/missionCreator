var jsToJavaBridge = {


    echo: function (msg) {
        javaBridge.logMsg(msg)
    },

    addMarker: function (lat, lon) {

        javaBridge.logMsg("add marker ["+lat+", "+lon+"]");

        L.marker([lat, lon]).addTo(this.map)
            .bindPopup('A pretty CSS3 popup.<br> Easily customizable.')
            .openPopup();
    }


};