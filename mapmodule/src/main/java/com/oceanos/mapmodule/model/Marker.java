package com.oceanos.mapmodule.model;

import com.oceanos.mapmodule.geometry.LatLng;
import com.oceanos.mapmodule.jsbridge.JavaToJSBridge;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Marker extends ObservableLayer<LatLng>{

    private JavaToJSBridge javaToJSBridge;
    private String popup;
    private ObjectProperty<LatLng> latLng;

    public Marker(int id, double lat, double lng){
        super(id);
        latLng = new SimpleObjectProperty<>(new LatLng(lat, lng));
    }

    public LatLng getLatLng() {
        return latLng.get();
    }

    public ObjectProperty<LatLng> latLngProperty() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng.set(latLng);
    }

    public void bindPopup(String msg){
        this.popup = msg;
        javaToJSBridge.bindPopup(this);
    }

    public String getPopup(){
        return this.popup;
    }

    public void setJavaToJSBridge(JavaToJSBridge javaToJSBridge) {
        this.javaToJSBridge = javaToJSBridge;
    }

    @Override
    public LatLng getValue() {
        return latLng.getValue();
    }


    enum Event{
        MOVE, CLICK, ADD, REMOVE
    }
}
