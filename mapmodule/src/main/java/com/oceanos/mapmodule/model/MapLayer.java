package com.oceanos.mapmodule.model;


import com.oceanos.mapmodule.jsbridge.JavaToJsBridge;

import java.util.Map;

public abstract class MapLayer extends Evented{
    protected String name;
    protected int id;
    protected Map<String, Object> options;
    JavaToJsBridge javaToJsBridge;

    public MapLayer(){

    }

    public MapLayer(int id){
        this.id = id;
    }

    public MapLayer(int id, JavaToJsBridge javaToJsBridge){
        this.id = id;
        this.javaToJsBridge = javaToJsBridge;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Object> getOptions() {
        return options;
    }

    public void setOptions(Map<String, Object> options) {
        this.options = options;
    }

    //TODO: move to constructor
    public void setJavaToJsBridge(JavaToJsBridge javaToJsBridge) {
        this.javaToJsBridge = javaToJsBridge;
    }

    public JavaToJsBridge getJavaToJsBridge() {
        return javaToJsBridge;
    }
}
