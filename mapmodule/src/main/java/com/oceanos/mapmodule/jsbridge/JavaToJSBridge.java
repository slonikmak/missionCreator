package com.oceanos.mapmodule.jsbridge;

import com.oceanos.mapmodule.model.Marker;
import com.oceanos.mapmodule.repository.Repository;
import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JavaToJSBridge {

    @Autowired
    private Repository repository;
    private JSObject jsObject;

    public void setJsObject(JSObject jsObject) {
        this.jsObject = jsObject;
    }

    public void echo(String msg){
        jsObject.call("echo", msg);
    }

    public void addMarker(double lat, double lng){
        int id = (int) jsObject.call("addMarker", lat,lng);
        repository.addLayer(new Marker(id, lat, lng));
        System.out.println(repository.getLayers().size());

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

    public void addMarker(){

    }

    public void addLine(){
        SimpleObj o = new SimpleObj("name",5);
        System.out.println(o);
        jsObject.call("addLine", o);
    }

    public class SimpleObj{
        String name;
        int age;

        public SimpleObj(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "{\"" +
                    "name\":\"" + name  +
                    "\", \"age\":" + age +
                    '}';
        }
    }
}
