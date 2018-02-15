package com.oceanos.mapmodule.model.options;

import java.util.HashMap;
import java.util.Map;

public class LayerOptions {
    private Map<String, Object> options;

    public LayerOptions(){
        options = new HashMap<>();
    }

    public void setOptions(Map<String, Object> options) {
        this.options = options;
    }

    public void add(String key, Object value){
        options.put(key, value);
    }

    public Map<String, Object> getOptions() {
        return options;
    }
}
