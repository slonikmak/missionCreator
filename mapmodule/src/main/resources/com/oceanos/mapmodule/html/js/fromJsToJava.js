//Объукт, содержащий функции для сообщений в JAVA

var fromJsToJava = {

    logMsg: function (msg) {
        javaController.log("[JS] "+msg)
    },

    addMarker: function (id, lat, lng) {
        javaController.addMarker(id, lat, lng);
    },

    clickMarker: function (id) {
        javaController.clickMarker(id);
    },

    changeMarker: function (id, lat, lng) {
        javaController.changeMarker(id, lat, lng);
    },


    clickToMap: function (latLng) {
        javaController.clickToMap(latLng.lat, latLng.lng);
    },

    moveMarker: function (marker) {
        javaController.changeMarker(marker._leaflet_id, marker.getLatLng().lat, marker.getLatLng().lng);
    },

    clickLayer: function (layer, lat, lng) {
        javaController.clickLayer(layer._leaflet_id, lat, lng);
    }

};