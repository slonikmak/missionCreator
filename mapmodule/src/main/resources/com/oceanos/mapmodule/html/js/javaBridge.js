var javaBridge = {

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
    }

};