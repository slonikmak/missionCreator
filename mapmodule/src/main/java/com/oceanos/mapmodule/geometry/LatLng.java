package com.oceanos.mapmodule.geometry;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class LatLng {
    private DoubleProperty lat;
    private DoubleProperty lng;

    public LatLng(double lat, double lng){
        this.lat = new SimpleDoubleProperty(lat);
        this.lng = new SimpleDoubleProperty(lng);

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

    public double getLng() {
        return lng.get();
    }

    public DoubleProperty lngProperty() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng.set(lng);
    }

    public void setLatLng(double lat, double lng){
        this.lat.setValue(lat);
        this.lng.setValue(lng);
    }
}
