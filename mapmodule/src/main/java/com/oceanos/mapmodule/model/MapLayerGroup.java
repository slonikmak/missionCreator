package com.oceanos.mapmodule.model;

import com.oceanos.mapmodule.geometry.LatLng;
import com.oceanos.mapmodule.model.options.LayerOptions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapLayerGroup extends MapLayer {
    ObservableList<MapLayer> layers = FXCollections.observableArrayList();

    public MapLayerGroup(int id) {
        super(id);
    }

    public Marker addMarker(double lat, double lng, Map<String, Object> options) {
        Marker marker = new Marker(lat, lng, options, javaToJsBridge);
        //repository.addLayer(marker);
        return marker;
    }

    public Marker addMarker() {
        return addMarker(51.505, -0.09);
    }

    public Marker addMarker(double lat, double lng) {
        return addMarker(lat, lng, new HashMap<>());
    }

    public PolyLine addPolyLine(List<LatLng> latLngs){
        return new PolyLine(latLngs, javaToJsBridge);
    }

    public Circle addCircle(LatLng latLng, double radius, LayerOptions options){
        return new Circle(this.javaToJsBridge, latLng, radius, options);
    }

}
