package com.oceanos.mapmodule.model;

public class PopUp extends MapLayer {
    private String text;

    public PopUp(int id) {
        super(id);
    }

    public PopUp(int id, String text){
        this(id);
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
