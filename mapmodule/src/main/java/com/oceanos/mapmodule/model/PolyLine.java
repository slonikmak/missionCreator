package com.oceanos.mapmodule.model;

import com.oceanos.mapmodule.geometry.LatLng;
import com.oceanos.mapmodule.jsbridge.JavaToJSBridge;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class PolyLine extends ObservableLayer<LatLng> {

    ObservableList<LatLng> latLngs = FXCollections.observableArrayList();
    JavaToJSBridge javaToJSBridge;

    public PolyLine(List<LatLng> latLngs, JavaToJSBridge javaToJSBridge) {
        super(javaToJSBridge.addPolyLine(latLngs));
        this.javaToJSBridge = javaToJSBridge;
    }

    @Override
    public LatLng getValue() {
        return null;
    }

    public void addLatLng(LatLng latLng){
        latLngs.add(latLng);
        javaToJSBridge.addPointToLine(id, latLng);
    }

    public void setJavaToJSBridge(JavaToJSBridge javaToJSBridge) {
        this.javaToJSBridge = javaToJSBridge;
    }
}
