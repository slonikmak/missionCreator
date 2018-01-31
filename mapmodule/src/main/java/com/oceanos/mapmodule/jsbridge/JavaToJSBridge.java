package com.oceanos.mapmodule.jsbridge;

import com.oceanos.mapmodule.repository.Repository;
import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JavaToJSBridge {

    @Autowired
    private Repository repository;
    private JSObject jsObject;

    public void setJsObject(JSObject jsObject) {
        this.jsObject = jsObject;
    }

    public void echo(String msg){
        jsObject.call("echo", msg);
    }

    public void addMarker(double lat, double lon){
        jsObject.call("addMarker", lat,lon);
    }

    public void addMarker(){

    }
}
