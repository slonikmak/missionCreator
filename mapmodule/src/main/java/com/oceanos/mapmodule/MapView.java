package com.oceanos.mapmodule;

import com.mohamnag.fxwebview_debugger.DevToolsDebuggerServer;
import com.oceanos.mapmodule.jsbridge.JavaToJSBridge;
import com.oceanos.mapmodule.jsbridge.JsToJavaBridge;
import com.oceanos.mapmodule.model.MapLayer;
import com.oceanos.mapmodule.model.Marker;
import com.oceanos.mapmodule.repository.Repository;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;


public class MapView extends AnchorPane{
    MapEventListener listener;

    //FIXME: move init to constructor
    Map<Class<? extends MapLayer>, MapEventListener> listeners = new HashMap<>();

    private WebView webView;
    private WebEngine webEngine;
    private JSObject window;
    private ApplicationContext applicationContext;

    private JavaToJSBridge javaToJsBridge;
    private Repository repository;

    public MapView(){
        super();



        applicationContext = new ClassPathXmlApplicationContext("appContext.xml");

        repository = applicationContext.getBean(Repository.class);

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

        webEngine.setOnError((e)->{
            System.out.println("error");
        });

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
                                javaToJsBridge = applicationContext.getBean(JavaToJSBridge.class);
                                window.setMember("javaController", applicationContext.getBean(JsToJavaBridge.class));
                                //window.setMember("console", applicationContext.getBean(JsToJavaBridge.class));
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

        repository.currentLayerProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) return;
            if (newValue.getClass().equals(Marker.class)){
                System.out.printf("[JAVA] click marker %d %f %f\n", newValue.getId(), ((Marker)newValue).getLatLng().getLat(), ((Marker)newValue).getLatLng().getLng());
                listeners.get(Marker.class).handle(newValue);
            }
            //listener.handle(newValue);
        });



    }

    public void addMarker(){
        javaToJsBridge.addMarker(51.505, -0.09);
    }

    public Repository getRepository(){
        return repository;
    }

    public void setOnMarkerClick(MapEventListener listener){
        listeners.put(Marker.class, listener);
    }



    public JavaToJSBridge getJavaToJsBridge(){
        return javaToJsBridge;
    }



    public interface MapEventListener{
        void handle(MapLayer layer);
    }

}
