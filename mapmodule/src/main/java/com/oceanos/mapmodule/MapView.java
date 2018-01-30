package com.oceanos.mapmodule;

import com.mohamnag.fxwebview_debugger.DevToolsDebuggerServer;
import com.oceanos.mapmodule.jsbridge.JavaToJSBridge;
import com.oceanos.mapmodule.jsbridge.JsToJavaBridge;
import javafx.application.Platform;
import javafx.concurrent.Worker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class MapView extends AnchorPane{
    private WebView webView;
    private WebEngine webEngine;
    private JSObject window;
    private ApplicationContext applicationContext;

    private JavaToJSBridge javaToJsBridge;

    public MapView(){
        super();

        applicationContext = new ClassPathXmlApplicationContext("appContext.xml");

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
                                //webView.getEngine().executeScript("if (!document.getElementById('FirebugLite')){E = document['createElement' + 'NS'] && document.documentElement.namespaceURI;E = E ? document['createElement' + 'NS'](E, 'script') : document['createElement']('script');E['setAttribute']('id', 'FirebugLite');E['setAttribute']('src', 'https://getfirebug.com/' + 'firebug-lite.js' + '#startOpened');E['setAttribute']('FirebugLite', '4');(document['getElementsByTagName']('head')[0] || document['getElementsByTagName']('body')[0]).appendChild(E);E = new Image;E['setAttribute']('src', 'https://getfirebug.com/' + '#startOpened');}");


                                try {
                                    DevToolsDebuggerServer.startDebugServer(webEngine.impl_getDebugger(), 51742);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                window = (JSObject) webEngine.executeScript("window");
                                javaToJsBridge = applicationContext.getBean(JavaToJSBridge.class);
                                window.setMember("javaController", applicationContext.getBean(JsToJavaBridge.class));
                                window.setMember("console", applicationContext.getBean(JsToJavaBridge.class));
                                JSObject jsToJava = (JSObject) webEngine.executeScript("jsToJavaBridge");
                                //jsToJava.call("echo","echo");
                                javaToJsBridge.setJsObject(jsToJava);
                                javaToJsBridge.echo("echo");

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



        new Thread(()->{
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Platform.runLater(()->javaToJsBridge.addMarker(51.505, -0.09));
        }).start();
    }


}
