package com.oceanos.mapmodule.model;



public abstract class MapLayer{
    String name;
    int id;

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
}
