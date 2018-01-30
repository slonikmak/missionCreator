package com.oceanos.mapmodule.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class PointModel {
    private DoubleProperty lat;
    private DoubleProperty lon;

    public PointModel(){
        lat = new SimpleDoubleProperty();
        lon = new SimpleDoubleProperty();
    }

    public PointModel(double lat, double lon) {
        this();
        this.lat.setValue(lat);
        this.lon.setValue(lon);
    }

    public double getLat() {
        return lat.get();
    }

    public DoubleProperty latProperty() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat.set(lat);
    }

    public double getLon() {
        return lon.get();
    }

    public DoubleProperty lonProperty() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon.set(lon);
    }
}
