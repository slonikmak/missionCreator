package com.oceanos.mapmodule.model;

import com.oceanos.mapmodule.geometry.LatLng;
import com.oceanos.mapmodule.jsbridge.JavaToJSBridge;
import com.oceanos.mapmodule.model.options.LayerOptions;


public class Circle extends Path {

    private LatLng latLng;
    private double radius;


    public Circle(JavaToJSBridge javaToJSBridge, LatLng latLng, double radius, LayerOptions options) {
        super(javaToJSBridge.addCircle(latLng, radius, options));
        this.javaToJSBridge = javaToJSBridge;
        this.options = options;
        this.latLng = latLng;
        this.radius = radius;
    }

   /* public Circle(JavaToJSBridge javaToJSBridge, LatLng latLng, double radius){
        this(javaToJSBridge, latLng, radius, new LayerOptions());
    }*/

    public void setLatLng(LatLng latLng){
        //System.out.println(latLng.lat);
        this.latLng = latLng;
        javaToJSBridge.changeCircle(this.id, latLng);
    }


}
