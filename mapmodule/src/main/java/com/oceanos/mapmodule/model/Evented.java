package com.oceanos.mapmodule.model;

import com.oceanos.mapmodule.events.EventType;
import com.oceanos.mapmodule.events.MapEvent;
import com.oceanos.mapmodule.events.MapEventListener;

import java.util.HashMap;
import java.util.Map;

public abstract class Evented {
    Map<EventType, MapEventListener> eventListeners;

    public Evented(){
        eventListeners = new HashMap<>();
    }

    public void addEventListener(EventType type, MapEventListener listener){
        eventListeners.put(type, listener);
    }

    public void removeEventListener(MapEventListener listener){
        //TODO: add body
    }

    public void fireEvent(EventType type, MapEvent event){
        eventListeners.get(type).handle(event);
    }
}
