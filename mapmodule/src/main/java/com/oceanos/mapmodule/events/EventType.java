package com.oceanos.mapmodule.events;

public enum EventType {
    CLICK("click"), MOVE("move"), ADD("add");

    String name;

    EventType(String name){
        this.name = name;
    }
}
