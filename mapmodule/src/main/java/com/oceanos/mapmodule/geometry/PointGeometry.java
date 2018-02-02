package com.oceanos.mapmodule.geometry;


import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class PointGeometry extends MapGeometry {

    private ObjectProperty<LatLng> latLng;



    public PointGeometry() {
        super(GeometryType.POINT);
        latLng = new SimpleObjectProperty<>();
    }

    public PointGeometry(double lat, double lng){
        this();
        setLatLng(new LatLng(lat, lng));
    }

    public LatLng getLatLng(){
        return getLatLngs().get(0);
    }

    public void setLatLng(LatLng latLng){
        this.getLatLngs().set(0, latLng);
    }
}
