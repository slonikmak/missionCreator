//Объукт, содержащий функции для сообщений в JAVA

var fromJsToJava = {
    prefix: "[JS] ",

    logMsg: function (msg) {
        javaController.log(this.prefix+msg)
    },

    addMarker: function (id, lat, lng) {
        javaController.log(this.prefix+" addMarker");
        javaController.addMarker(id, lat, lng);
    },

    clickMarker: function (id) {
        javaController.log(this.prefix+" clickMarker");
        javaController.clickMarker(id);
    },

    changeMarker: function (id, lat, lng) {
        javaController.log(this.prefix+" changeMarker");
        javaController.changeMarker(id, lat, lng);
    },


    clickToMap: function (latLng) {
        javaController.log(this.prefix+" clickToMap");
        javaController.clickToMap(latLng.lat, latLng.lng);
    },

    moveMarker: function (marker) {
        javaController.log(this.prefix+" moveMarker");
        javaController.changeMarker(marker._leaflet_id, marker.getLatLng().lat, marker.getLatLng().lng);
    },

    clickLayer: function (layer, lat, lng) {
        javaController.log(this.prefix+" clickLayer");
        javaController.clickLayer(layer._leaflet_id, lat, lng);
    }

};