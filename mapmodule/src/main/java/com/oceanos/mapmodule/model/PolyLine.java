package com.oceanos.mapmodule.model;

import com.oceanos.mapmodule.geometry.LatLng;
import com.oceanos.mapmodule.jsbridge.JavaToJSBridge;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class PolyLine extends Path {

    ObservableList<LatLng> latLngs = FXCollections.observableArrayList();

    public PolyLine(){

    }

    public PolyLine(List<LatLng> latLngs) {
        this.latLngs.addAll(latLngs);
    }
    public void addLatLng(LatLng latLng){
        latLngs.add(latLng);
        javaToJSBridge.addPointToLine(id, latLng);
    }
    public ObservableList<LatLng> getLatLngs() {
        return latLngs;
    }
}
