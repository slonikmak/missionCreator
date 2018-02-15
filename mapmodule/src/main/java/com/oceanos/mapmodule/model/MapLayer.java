package com.oceanos.mapmodule.model;


import java.util.Map;

public abstract class MapLayer extends Evented{
    String name;
    int id;
    Map<String, Object> options;

    public MapLayer(int id){
        this.id = id;
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

}
