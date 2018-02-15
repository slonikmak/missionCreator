package com.oceanos.mapmodule.events;

import com.oceanos.mapmodule.model.MapLayer;

public class LayerEvent extends MapEvent {
    MapLayer source;

    public MapLayer getSource() {
        return source;
    }

    public void setSource(MapLayer source) {
        this.source = source;
    }
}
