package com.oceanos.mapmodule;

import javafx.concurrent.Worker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;

public class MapView extends AnchorPane{
    private WebView webView;
    private WebEngine webEngine;
    private JSObject window;

    public MapView(){
        super();

        setMaxWidth(Integer.MAX_VALUE);
        setMaxHeight(Integer.MAX_VALUE);

        initWebView();

        getChildren().add(webView);

        webView.setMaxHeight(Integer.MAX_VALUE);
        webView.setMaxWidth(Integer.MAX_VALUE);

        setBottomAnchor(webView, 0d);
        setTopAnchor(webView, 0d);
        setRightAnchor(webView, 0d);
        setLeftAnchor(webView, 0d);

    }

    private void initWebView(){

        webView = new WebView();
        webEngine = webView.getEngine();
        webEngine.setJavaScriptEnabled(true);
        webEngine.load(getClass().getResource("/com/oceanos/mapmodule/html/mapview.html").toExternalForm());


        webEngine.getLoadWorker()
                .stateProperty()
                .addListener((ov, oldState, newState) -> {
                            if (newState == Worker.State.SUCCEEDED) {
                                System.out.println("Change");
                                window = (JSObject) webEngine.executeScript("window");
                                //window.setMember("javaController", controller);
                                //jsBridge = new JsBridge(window, repository);
                                //jsBridge.initJavaController();
                                //repository.setJsBridge(jsBridge);

                                //jsBridge.resizeMap(webView.getWidth(), webView.getHeight());

                                //setZoom((int) window.call("getZoom"));
                       /* // all next classes are from org.w3c.dom domain
                        org.w3c.dom.events.EventListener listener = (ev) -> {
                            System.out.println("#" + (org.w3c.dom.Element) ev.getTarget());
                        };

                        org.w3c.dom.Document doc = webEngine.getDocument();
                        org.w3c.dom.Element el = doc.getElementById("mapid");
                        ((org.w3c.dom.events.EventTarget) el).addEventListener("click", listener, false);*/
                                //jsBridge.setPosition();
                            }

                            //setMapUrl();
                        }
                );
    }
}
