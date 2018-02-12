package com.oceanos.mapmodule.model;

public class Tooltip extends MapLayer{
    String content;

    public Tooltip(int id) {
        super(id);
    }

    public Tooltip(int id, String content) {
        this(id);
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
