var javaBridge = {

    logMsg: function (msg) {
        javaController.log("From js: "+msg)
    },

    addMarker: function (id, lat, lng) {
        javaController.addMarker(id, lat, lng);
    },

    clickMarker: function (id) {
        javaController.clickMarker(id);
    },

    changeMarker: function (id, lat, lng) {
        javaController.changeMarker(id, lat, lng);
    }

};