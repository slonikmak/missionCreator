package com.oceanos.mapmodule.repository;

import com.oceanos.mapmodule.model.MapLayer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

@org.springframework.stereotype.Repository
public class Repository {

    ObservableList<MapLayer> layers = FXCollections.observableArrayList();

    public void addLayer(MapLayer layer){
        layers.add(layer);
    }

    public ObservableList<MapLayer> getLayers() {
        return layers;
    }
}
