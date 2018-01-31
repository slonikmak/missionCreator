package com.oceanos.mapmodule.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public abstract class MapLayer {
    GeometryType type;
    ObservableList<Point> points = FXCollections.observableArrayList();
    int id;

    public GeometryType getType() {
        return this.type;
    }

    public ObservableList<Point> pointsProperty() {
        return points;
    }

    public int getId() {
        return id;
    }
}
