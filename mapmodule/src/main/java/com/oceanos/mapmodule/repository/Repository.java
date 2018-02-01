package com.oceanos.mapmodule.repository;

import com.oceanos.mapmodule.model.MapLayer;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Map;

@org.springframework.stereotype.Repository
public class Repository {

    ObjectProperty<MapLayer> currentLayer = new SimpleObjectProperty<>();

    ObservableList<MapLayer> layers = FXCollections.observableArrayList();

    public void addLayer(MapLayer layer){
        System.out.println("Layer added: "+layer.getId());
        layers.add(layer);
    }

    public ObservableList<MapLayer> getLayers() {
        return layers;
    }

    public void setCurrentLayer(MapLayer currentLayer) {
        this.currentLayer.set(currentLayer);
    }

    public void setCurrentLayer(int id){
        currentLayer.set(layers.filtered(l->l.getId()==id).get(0));
    }

    public ObjectProperty<MapLayer> currentLayerProperty() {
        return currentLayer;
    }
}
