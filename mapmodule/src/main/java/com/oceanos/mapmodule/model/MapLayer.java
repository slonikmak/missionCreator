package com.oceanos.mapmodule.model;

import com.oceanos.mapmodule.geometry.GeometryType;
import com.oceanos.mapmodule.geometry.MapGeometry;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public abstract class MapLayer {
    private MapGeometry geometry;
    int id;

    public MapLayer(int id, MapGeometry geometry){
        this.id = id;
        this.geometry = geometry;
    }

    public GeometryType getType() {
        return this.geometry.getType();
    }

    public MapGeometry getGeometry() {
        return geometry;
    }

    public int getId() {
        return id;
    }
}
