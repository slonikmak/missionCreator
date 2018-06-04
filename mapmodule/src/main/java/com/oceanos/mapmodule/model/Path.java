package com.oceanos.mapmodule.model;

import com.oceanos.mapmodule.jsbridge.JavaToJSBridge;
import com.oceanos.mapmodule.model.options.LayerOptions;


public abstract class Path extends MapLayer {
    LayerOptions options;
    JavaToJSBridge javaToJSBridge;

    public Path(){}

    public Path(int id) {
        super(id);
    }

    public void setStyle(LayerOptions options){
        this.options = options;
    }

    public void setJavaToJSBridge(JavaToJSBridge javaToJSBridge) {
        this.javaToJSBridge = javaToJSBridge;
    }
}
