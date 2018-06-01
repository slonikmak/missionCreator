package com.oceanos.mapmodule.jsbridge;

import com.google.gson.Gson;
import com.oceanos.mapmodule.geometry.LatLng;
import com.oceanos.mapmodule.model.MapLayer;
import com.oceanos.mapmodule.model.Marker;
import com.oceanos.mapmodule.model.options.LayerOptions;
import com.oceanos.mapmodule.repository.Repository;
import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JavaToJsBridge {
    private final String prefix = "[JAVA-JS]";

    private Repository repository;
    private JSObject jsObject;
    private Gson gson;

    public JavaToJsBridge(){
        gson = new Gson();
    }

    public void setRepository(Repository repository){
        this.repository = repository;
    }

    public void setJsObject(JSObject jsObject) {
        this.jsObject = jsObject;
    }

    public void echo(String msg){
        System.out.println(prefix+ " echo: "+msg);
        jsObject.call("echo", msg);
    }

    public int addMarker(double lat, double lng, Map<String, Object> options){
        System.out.println(prefix+" addMarker");
        int id = (int) jsObject.call("addMarker", lat,lng, gson.toJson(options));
        return id;
    }

    public int addCircle(LatLng latLng, double radius, LayerOptions options){
        System.out.println(prefix+" addCircle");
        return (int) jsObject.call("addCircle", latLng.lat, latLng.lng, radius, gson.toJson(options.getOptions()));
    }

    public void changeMarker(int id, double lat, double lng) {
        System.out.printf(prefix+" From Java To Js: change marker: %d %f %f", id, lat, lng);
        jsObject.call("changeMarker", id, lat, lng);
        /*try {
            throw new Exception("ooooo"){

            };
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    public void changeMarker(Marker marker){
        System.out.printf(prefix+" From Java To Js: change marker: %d %f %f \n", marker.getId(), marker.getLatLng().getLat(), marker.getLatLng().getLng());
        jsObject.call("changeMarker", marker.getId(), marker.getLatLng().getLat(), marker.getLatLng().getLng());
    }

    public void bindPopup(Marker marker){
        System.out.println(prefix + " bindPopup");
        jsObject.call("bindPopup", marker.getId(), marker.getPopup().getText());
    }

    public void bindTooltip(Marker marker){
        System.out.println(prefix + " bindTooltip");
        jsObject.call("bindTooltip", marker.getId(), marker.getTooltip().getContent());
    }

    public void addMarker(){

    }

    public int addPolyLine(List<LatLng> latLngs){
        System.out.println(prefix+" addPolyline");
        return (int) jsObject.call("addPolyLine", gson.toJson(latLngs));
    }

    public void addPointToLine(int id, LatLng latLng){
        System.out.println(prefix+" addPointToLine");
        jsObject.call("addPointToLine", id, latLng.lat, latLng.lng);
    }

    public void startPolyLine() {
        System.out.println(prefix+"startPolyline");
        Object o = jsObject.call("startPolyline");
        System.out.println("obj:"+o);
    }

    public int getMapId(){
        return (int) jsObject.call("getMapId");
    }


    public void showPopup(Marker marker) {
        System.out.println(prefix+" showPopup");
        jsObject.call("showPopup", marker.getId());
    }

    public void changeCircle(int id, LatLng latLng){
        System.out.println(prefix+ " changeCircle");
        jsObject.call("changeCircle", id, latLng.lat, latLng.lng);
    }

    public void bindCoords(int from, int to){
        jsObject.call("bindCoords", from, to);
    }


    public void setTooltipContent(int id, String content) {
        System.out.println(prefix + "setTooltipContent");
        jsObject.call("setTooltipContent", id, content);
    }
}
