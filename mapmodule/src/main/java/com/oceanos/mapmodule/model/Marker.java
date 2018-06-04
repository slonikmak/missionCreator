package com.oceanos.mapmodule.model;

import com.oceanos.mapmodule.events.EventType;
import com.oceanos.mapmodule.events.MapEventListener;
import com.oceanos.mapmodule.events.MouseEvent;
import com.oceanos.mapmodule.geometry.LatLng;
import com.oceanos.mapmodule.jsbridge.JavaToJSBridge;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.HashMap;
import java.util.Map;


public class Marker extends MapLayer {


    private JavaToJSBridge javaToJSBridge;
    private PopUp popup;
    private Tooltip tooltip;
    private ObjectProperty<LatLng> latLng;

    public Marker(double lat, double lng){
        this.latLng = new SimpleObjectProperty<>(new LatLng(lat,lng));
    }

    Marker(int id, double lat, double lng){
        this(lat, lng);
        this.id = id;
    }


    Marker(double lat, double lng, Map<String, Object> options, JavaToJSBridge javaToJSBridge){
        this(lat, lng);
        this.javaToJSBridge = javaToJSBridge;
        this.latLng = new SimpleObjectProperty<>(new LatLng(lat, lng));
    }

    public LatLng getLatLng() {
        return latLng.get();
    }

    public ObjectProperty<LatLng> latLngProperty() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
       // this.changeListener.changed(this, this.latLng.getValue(), latLng);
        this.latLng.set(latLng);
    }

    public void bindPopup(String msg){
        this.popup = new PopUp(id, msg);
        javaToJSBridge.bindPopup(this);
    }

    public void bindTooltip(String msg){
        this.tooltip = new Tooltip(id, msg);
        javaToJSBridge.bindTooltip(this);
    }

    public void showPopup(){
        javaToJSBridge.showPopup(this);
    }

    public PopUp getPopup(){
        return this.popup;
    }

    public Tooltip getTooltip() {
        return tooltip;
    }

    /*//public void setJavaToJSBridge(JavaToJSBridge javaToJSBridge) {
        this.javaToJSBridge = javaToJSBridge;
    }*/




}
