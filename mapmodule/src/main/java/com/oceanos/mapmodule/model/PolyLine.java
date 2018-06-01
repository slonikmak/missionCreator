package com.oceanos.mapmodule.model;

import com.oceanos.mapmodule.geometry.LatLng;
import com.oceanos.mapmodule.jsbridge.JavaToJsBridge;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class PolyLine extends Path {

    ObservableList<LatLng> latLngs = FXCollections.observableArrayList();

    public PolyLine(List<LatLng> latLngs, JavaToJsBridge javaToJSBridge) {
        super(javaToJSBridge.addPolyLine(latLngs));
        this.javaToJSBridge = javaToJSBridge;
    }


    public void addLatLng(LatLng latLng){
        latLngs.add(latLng);
        javaToJSBridge.addPointToLine(id, latLng);
    }


}
