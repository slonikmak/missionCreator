package com.oceanos.mapmodule.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MapLayersGroup extends MapLayer{
    private ObservableList<MapLayer> layers = FXCollections.observableArrayList();


    public void addMapLayer(MapLayer layer){
        layers.add(layer);
        layer.setJavaToJSBridge(this.javaToJSBridge);
        //TODO: Move to javaJsBridge
        if (layer instanceof Marker){
            System.out.println("marker");
            layer.id = javaToJSBridge.addMarker(((Marker) layer).getLatLng().lat, ((Marker) layer).getLatLng().lng, layer.getOptions());
        } else if (layer instanceof Circle){
            layer.id = javaToJSBridge.addCircle((Circle) layer);
        } else if (layer instanceof PolyLine){
            layer.id = javaToJSBridge.addPolyLine((PolyLine) layer);
        }
    }

    public ObservableList<MapLayer> getLayers(){
        return layers;
    }
}
