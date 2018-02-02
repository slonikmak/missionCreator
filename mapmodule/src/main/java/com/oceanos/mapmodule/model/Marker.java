package com.oceanos.mapmodule.model;

import com.oceanos.mapmodule.geometry.PointGeometry;

public class Marker extends MapLayer {

    public Marker(int id, double lat, double lng){
        super(id, new PointGeometry(lat, lng));
    }

}
