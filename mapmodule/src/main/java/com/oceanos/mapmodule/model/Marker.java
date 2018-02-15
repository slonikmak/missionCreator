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
    private String popup;
    private Tooltip tooltip;
    private ObjectProperty<LatLng> latLng;

    Marker(int id, double lat, double lng){
        super(id);
        latLng = new SimpleObjectProperty<>(new LatLng(lat, lng));
    }


    Marker(double lat, double lng, Map<String, Object> options, JavaToJSBridge javaToJSBridge){
        super(javaToJSBridge.addMarker(lat, lng, options));
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
        this.popup = msg;
        javaToJSBridge.bindPopup(this);
    }

    public void bindTooltip(String msg){
        this.tooltip = new Tooltip(id, msg);
        javaToJSBridge.bindTooltip(this);
    }

    public void showPopup(){
        javaToJSBridge.showPopup(this);
    }

    public String getPopup(){
        return this.popup;
    }

    public Tooltip getTooltip() {
        return tooltip;
    }

    public void setJavaToJSBridge(JavaToJSBridge javaToJSBridge) {
        this.javaToJSBridge = javaToJSBridge;
    }




}
