package com.oceanos.mapmodule.events;

/**
 * Main class of all Events
 */
public class MapEvent {
    EventType type;

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
