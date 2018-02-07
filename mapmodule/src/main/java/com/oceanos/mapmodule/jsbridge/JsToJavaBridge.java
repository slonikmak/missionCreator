package com.oceanos.mapmodule.jsbridge;

import com.oceanos.mapmodule.geometry.LatLng;
import com.oceanos.mapmodule.model.Marker;
import com.oceanos.mapmodule.repository.Repository;
import javafx.beans.value.ChangeListener;
import javafx.collections.ListChangeListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JsToJavaBridge {

    @Autowired
    Repository repository;
    @Autowired
    JavaToJSBridge javaToJSBridge;

    public void log(String msg) {
        System.out.println(msg);
    }


    public void error(String text) {
        System.err.println(text);
    }

    public void addMarker(int id, double lat, double lng) {

        System.out.printf("[JAVA] add marker from JS %d %f %f\n", id, lat, lng);
        Marker marker = new Marker(id, lat, lng);
        repository.addLayer(marker);
        marker.setJavaToJSBridge(javaToJSBridge);


        //TODO: move MapLayer
        marker.getGeometry().getLatLngs().addListener((ListChangeListener<LatLng>) change -> {

                    /*System.out.println("Detected a change! ");*/
                    while (change.next()) {
                        /*System.out.println("Was added? " + change.wasAdded());
                        System.out.println("Was removed? " + change.wasRemoved());
                        System.out.println("Was replaced? " + change.wasReplaced());
                        System.out.println("Was permutated? " + change.wasPermutated());
                        System.out.println("Was updated?"+ change.wasUpdated());*/
                        if (change.wasUpdated()) {
                            System.out.println("[JAVA] Marker changes "+marker.getId());
                            javaToJSBridge.changeMarker(marker);
                        }
                    }
                }
        );
    }

    public void clickMarker(int id) {
        System.out.println("[JAVA] Marker clicked: " + id);
        repository.setCurrentLayer(id);
    }

    public void changeMarker(int id, double lat, double lng) {
        repository.getLayers().filtered(l -> l.getId() == id).get(0).getGeometry().getLatLngs().get(0).setLatLng(lat, lng);
        System.out.printf("[JAVA] From Js to Java change marker %d %f %f\n", id, lat, lng);
    }



}
