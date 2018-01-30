package com.oceanos.mapmodule.jsbridge;

import org.springframework.stereotype.Component;

@Component
public class JsToJavaBridge {

    public void log(String msg){
        System.out.println(msg);
    }


    public void error(String text) {
        System.err.println(text);
    }

}
