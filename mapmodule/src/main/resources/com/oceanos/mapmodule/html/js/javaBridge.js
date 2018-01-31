var javaBridge = {

    logMsg: function (msg) {
        javaController.log("From js: "+msg)
    },

    addMarker: function (id, lat, lon) {
        javaController.addMarker(id, lat, lon);
    }

};