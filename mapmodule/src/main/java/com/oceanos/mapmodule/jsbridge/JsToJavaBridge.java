package com.oceanos.mapmodule.jsbridge;

import com.oceanos.mapmodule.model.Marker;
import com.oceanos.mapmodule.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JsToJavaBridge {

    @Autowired
    Repository repository;

    public void log(String msg){
        System.out.println(msg);
    }


    public void error(String text) {
        System.err.println(text);
    }

    public void addMarker(int id, double lat, double lng){
        System.out.println("Marker added: "+id+" " +lat+" "+lng);
        repository.addLayer(new Marker(id, lat, lng));
    }

}
