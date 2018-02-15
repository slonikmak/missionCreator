package com.oceanos.mapmodule.events;

import java.util.EventListener;

public interface MapEventListener extends EventListener {
    void handle(MapEvent event);
}
