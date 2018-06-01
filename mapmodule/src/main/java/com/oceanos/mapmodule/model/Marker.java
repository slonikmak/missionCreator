package com.oceanos.mapmodule.model;

import com.oceanos.mapmodule.events.EventType;
import com.oceanos.mapmodule.events.MapEventListener;
import com.oceanos.mapmodule.geometry.LatLng;
import com.oceanos.mapmodule.jsbridge.JavaToJsBridge;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.Map;


public class Marker extends MapLayer {


    private PopUp popup;
    private Tooltip tooltip;
    private ObjectProperty<LatLng> latLng;

    Marker(){

    }

    Marker(int id, double lat, double lng){
        super(id);
        latLng = new SimpleObjectProperty<>(new LatLng(lat, lng));
    }


    Marker(double lat, double lng, Map<String, Object> options, JavaToJsBridge javaToJSBridge){
        super(javaToJSBridge.addMarker(lat, lng, options));
        this.javaToJsBridge = javaToJSBridge;
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
        javaToJsBridge.bindPopup(this);
    }

    public void bindTooltip(String msg){
        this.tooltip = new Tooltip(id, msg);
        javaToJsBridge.bindTooltip(this);
    }

    public void showPopup(){
        javaToJsBridge.showPopup(this);
    }

    public PopUp getPopup(){
        return this.popup;
    }

    public Tooltip getTooltip() {
        return tooltip;
    }

    public void setOnClick(MapEventListener listener){
        addEventListener(EventType.CLICK, listener);
    }

    /*//public void setJavaToJsBridge(JavaToJSBridge javaToJsBridge) {
        this.javaToJsBridge = javaToJsBridge;
    }*/

}
