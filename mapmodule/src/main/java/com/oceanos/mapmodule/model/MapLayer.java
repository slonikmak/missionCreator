package com.oceanos.mapmodule.model;



public abstract class MapLayer{
    private String name;
    private int id;

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
