package com.oceanos.mapmodule.events;

import com.oceanos.mapmodule.geometry.LatLng;

import java.awt.geom.Point2D;

/**
 * Main class of all Events
 */
public class MapEvent {
    EventType type;
    LatLng latLng;
    Point2D layerPoint;
    int target;

    /**
     *
     * @return eventType
     */
    public EventType getType() {
        return type;
    }

    /**
     *
     * @param type eventType
     *             @EventType
     */
    public void setType(EventType type) {
        this.type = type;
    }
}
