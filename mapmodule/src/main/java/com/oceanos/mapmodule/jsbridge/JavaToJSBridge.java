package com.oceanos.mapmodule.jsbridge;

import com.google.gson.Gson;
import com.oceanos.mapmodule.geometry.LatLng;
import com.oceanos.mapmodule.model.Marker;
import com.oceanos.mapmodule.repository.Repository;
import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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

    public int addMarker(double lat, double lng){
        int id = (int) jsObject.call("addMarker", lat,lng);
        System.out.println(repository.getLayers().size());
        return id;
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
        jsObject.call("bindPopup", marker.getId(), marker.getPopup());
    }

    public void bindTooltip(Marker marker){
        jsObject.call("bindTooltip", marker.getId(), marker.getTooltip());
    }

    public void addMarker(){

    }

    public void addLine(){
        //System.out.println(o);
        List<LatLng> list = new ArrayList();
        list.add(new LatLng(45.51, -122.68));
        list.add(new LatLng(37.77, -122.43));

        jsObject.call("addLine", gson.toJson(list));
    }

    public void startPolyLine() {
        Object o = jsObject.call("startPolyline");
        System.out.println(o);
    }

    public int getMapId(){
        return (int) jsObject.call("getMapId");
    }


    public void showPopup(Marker marker) {
        System.out.println("[JAVA] show popup");
        jsObject.call("showPopup", marker.getId());
    }


}
