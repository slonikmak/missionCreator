package com.oceanos.mapmodule.editor;

import javafx.concurrent.Worker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;

public class JSEditor {
    private WebView webView;
    private WebEngine webEngine;
    private AnchorPane view;
    JSObject window;

    public JSEditor(){
        view = new AnchorPane();
        webView = new WebView();
        webEngine = webView.getEngine();

        view.getChildren().add(webView);
        view.setMaxWidth(Integer.MAX_VALUE);
        view.setMaxHeight(Integer.MAX_VALUE);

        webView.setMaxHeight(Integer.MAX_VALUE);
        webView.setMaxWidth(Integer.MAX_VALUE);
        AnchorPane.setBottomAnchor(webView, 0d);
        AnchorPane.setTopAnchor(webView, 0d);
        AnchorPane.setRightAnchor(webView, 0d);
        AnchorPane.setLeftAnchor(webView, 0d);

        view.setPrefHeight(200);
        view.setPrefWidth(400);

        webEngine.setJavaScriptEnabled(true);
        webEngine.load(getClass().getResource("/com/oceanos/mapmodule/html/codeEditor.html").toExternalForm());

        webEngine.getLoadWorker()
                .stateProperty()
                .addListener((ov, oldState, newState) -> {
                            if (newState == Worker.State.SUCCEEDED) {
                                //webEngine.executeScript("if (!document.getElementById('FirebugLite')){E = document['createElement' + 'NS'] && document.documentElement.namespaceURI;E = E ? document['createElement' + 'NS'](E, 'script') : document['createElement']('script');E['setAttribute']('id', 'FirebugLite');E['setAttribute']('src', 'https://getfirebug.com/' + 'firebug-lite.js' + '#startOpened');E['setAttribute']('FirebugLite', '4');(document['getElementsByTagName']('head')[0] || document['getElementsByTagName']('body')[0]).appendChild(E);E = new Image;E['setAttribute']('src', 'https://getfirebug.com/' + '#startOpened');}");


                                /*try {
                                    DevToolsDebuggerServer.startDebugServer(webEngine.impl_getDebugger(), 51742);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }*/
                                window = (JSObject) webEngine.executeScript("window");

                            }
                        }
                );

    }


    public String getScript(){

        return (String) window.eval("editor.getValue()");
    }

    public AnchorPane getView(){
        return view;
    }
}
