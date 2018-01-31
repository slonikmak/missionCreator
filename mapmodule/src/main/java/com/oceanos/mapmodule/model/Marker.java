package com.oceanos.mapmodule.model;

public class Marker extends MapLayer {

    public Marker(int id, double lat, double lng){
        this.id = id;
        this.points.add(new Point(lat, lng));
    }

}
