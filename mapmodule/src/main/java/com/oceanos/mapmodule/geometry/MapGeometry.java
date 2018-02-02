package com.oceanos.mapmodule.geometry;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public abstract class MapGeometry {
    private ObservableList<LatLng> geometry;
    private GeometryType type;

    public MapGeometry(GeometryType type){
        this.geometry = FXCollections.observableArrayList();
        this.type = type;
    }

    public ObservableList<LatLng> getLatLngs() {
        return geometry;
    }

    //FIXME close for PointGeometry
    public void setLatLngs(ObservableList<LatLng> geometry) {
        this.geometry = geometry;
    }

    public GeometryType getType() {
        return type;
    }
}
