package com.oceanos.mapmodule.model;

import com.oceanos.mapmodule.geometry.PointGeometry;

public class Marker extends MapLayer {

    public Marker(int id, double lat, double lng){
        super(id, new PointGeometry(lat, lng));
    }

    public double getLat(){
        return getGeometry().getLatLngs().get(0).getLat();
    }

    public double getLng(){
        return getGeometry().getLatLngs().get(0).getLng();
    }

    public void setLat(double lat){
        getGeometry().getLatLngs().get(0).setLat(lat);
    }

    public void setLng(double lng){
        getGeometry().getLatLngs().get(0).setLng(lng);
    }

}
