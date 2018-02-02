package com.oceanos.mapmodule.jsbridge;

import com.oceanos.mapmodule.model.Marker;
import com.oceanos.mapmodule.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JsToJavaBridge {

    @Autowired
    Repository repository;
    @Autowired
    JavaToJSBridge javaToJSBridge;

    public void log(String msg){
        System.out.println(msg);
    }


    public void error(String text) {
        System.err.println(text);
    }

    public void addMarker(int id, double lat, double lng){

        System.out.println("add marker from JS");
        Marker marker = new Marker(id, lat, lng);
        repository.addLayer(marker);
        marker.getGeometry().getLatLngs().get(0).latProperty().addListener((a,b,c)->javaToJSBridge.changeMarker(
                marker.getId(),
                (Double) c,
                marker.getGeometry().getLatLngs().get(0).getLng()));
    }

    public void clickMarker(int id){
        System.out.println("Marker clicked: "+id);
        repository.setCurrentLayer(id);
    }

    public void changeMarker(int id, double lat, double lng){
        repository.getLayers().filtered(l->l.getId()==id).get(0).getGeometry().getLatLngs().get(0).setLatLng(lat,lng);
        System.out.println("change marker");
    }

}
