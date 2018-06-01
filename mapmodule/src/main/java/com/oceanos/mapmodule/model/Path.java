package com.oceanos.mapmodule.model;

import com.oceanos.mapmodule.jsbridge.JavaToJsBridge;
import com.oceanos.mapmodule.model.options.LayerOptions;


public abstract class Path extends MapLayer {
    LayerOptions options;
    JavaToJsBridge javaToJSBridge;


    public Path(int id) {
        super(id);
    }

    public void setStyle(LayerOptions options){
        this.options = options;
    }

    public void setJavaToJsBridge(JavaToJsBridge javaToJsBridge) {
        this.javaToJSBridge = javaToJsBridge;
    }
}
