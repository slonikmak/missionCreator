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

public class JavaToJSBridge {

    private Repository repository;
    private JSObject jsObject;
    private Gson gson;

    public JavaToJSBridge(){
        gson = new Gson();
    }

    public void setRepository(Repository repository){
        this.repository = repository;
    }

    public void setJsObject(JSObject jsObject) {
        this.jsObject = jsObject;
    }

    public void echo(String msg){
        jsObject.call("echo", msg);
    }

    public int addMarker(double lat, double lng, Map<String, Object> options){
        int id = (int) jsObject.call("addMarker", lat,lng, gson.toJson(options));
        System.out.println(repository.getLayers().size());
        return id;
    }

    public int addCircle(LatLng latLng, double radius, LayerOptions options){
        return (int) jsObject.call("addCircle", latLng.lat, latLng.lng, radius, gson.toJson(options.getOptions()));
    }

    public void changeMarker(int id, double lat, double lng) {

        System.out.printf("[JAVA] From Java To Js: change marker: %d %f %f", id, lat, lng);
        jsObject.call("changeMarker", id, lat, lng);
        /*try {
            throw new Exception("ooooo"){

            };
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    public void changeMarker(Marker marker){
        System.out.printf("[JAVA] From Java To Js: change marker: %d %f %f \n", marker.getId(), marker.getLatLng().getLat(), marker.getLatLng().getLng());
        jsObject.call("changeMarker", marker.getId(), marker.getLatLng().getLat(), marker.getLatLng().getLng());
    }

    public void bindPopup(Marker marker){
        jsObject.call("bindPopup", marker.getId(), marker.getPopup().getText());
    }

    public void bindTooltip(Marker marker){
        jsObject.call("bindTooltip", marker.getId(), marker.getTooltip().getContent());
    }

    public void addMarker(){

    }

    public int addPolyLine(List<LatLng> latLngs){

        return (int) jsObject.call("addPolyLine", gson.toJson(latLngs));
    }

    public void addPointToLine(int id, LatLng latLng){
        jsObject.call("addPointToLine", id, latLng.lat, latLng.lng);
    }

    public void startPolyLine() {
        Object o = jsObject.call("startPolyline");
        System.out.println("obj:"+o);
    }

    public int getMapId(){
        return (int) jsObject.call("getMapId");
    }


    public void showPopup(Marker marker) {
        System.out.println("[JAVA] show popup");
        jsObject.call("showPopup", marker.getId());
    }

    public void changeCircle(int id, LatLng latLng){
        jsObject.call("changeCircle", id, latLng.lat, latLng.lng);
    }

    public void bindCoords(int from, int to){
        jsObject.call("bindCoords", from, to);
    }


    public void setTooltipContent(int id, String content) {
        jsObject.call("setTooltipContent", id, content);
    }
}
