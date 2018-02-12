package com.oceanos.mapmodule.events;

import com.oceanos.mapmodule.geometry.LatLng;

public class MouseEvent {
    private EventType type;
    private LatLng latLng;

    public MouseEvent(EventType type, LatLng latLng) {
        this.type = type;
        this.latLng = latLng;
    }

    public EventType getType() {
        return type;
    }

    public LatLng getLatLng() {
        return latLng;
    }
}
