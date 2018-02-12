package com.oceanos.mapmodule.events;

public enum EventType {
    CLICK("click");

    String name;

    EventType(String name){
        this.name = name;
    }
}
