package com.oceanos.mapmodule.events;

import com.oceanos.mapmodule.geometry.LatLng;

public class MouseEvent extends MapEvent{
    private LatLng latLng;

    public MouseEvent(EventType type, LatLng latLng) {
        this.type = type;
        this.latLng = latLng;
    }

    public LatLng getLatLng() {
        return latLng;
    }
}
