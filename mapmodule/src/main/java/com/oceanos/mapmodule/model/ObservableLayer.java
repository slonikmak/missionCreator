package com.oceanos.mapmodule.model;

import com.oceanos.mapmodule.geometry.LatLng;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public abstract class ObservableLayer<LatLng> extends MapLayer implements ObservableValue<LatLng> {

    private ChangeListener<? super LatLng> changeListener = null;

    public ObservableLayer(int id) {
        super(id);
    }


    @Override
    public void addListener(ChangeListener<? super LatLng> listener) {
        changeListener = listener;
    }

    @Override
    public void removeListener(ChangeListener<? super LatLng> listener) {
        changeListener = null;
    }

    @Override
    public void addListener(InvalidationListener listener) {

    }

    @Override
    public void removeListener(InvalidationListener listener) {

    }

}
