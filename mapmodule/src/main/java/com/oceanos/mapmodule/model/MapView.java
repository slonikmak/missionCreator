package com.oceanos.mapmodule.model;

import com.mohamnag.fxwebview_debugger.DevToolsDebuggerServer;
import com.oceanos.mapmodule.events.EventType;
import com.oceanos.mapmodule.events.MapEventListener;
import com.oceanos.mapmodule.events.MouseEvent;
import com.oceanos.mapmodule.geometry.LatLng;
import com.oceanos.mapmodule.jsbridge.JavaToJSBridge;
import com.oceanos.mapmodule.jsbridge.JsToJavaBridge;
import com.oceanos.mapmodule.model.MapLayer;
import com.oceanos.mapmodule.model.Marker;
import com.oceanos.mapmodule.model.options.LayerOptions;
import com.oceanos.mapmodule.repository.Repository;
import javafx.concurrent.Worker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MapView extends MapLayer {

    //FIXME: move init to constructor
    Map<EventType, MapEventListener> listeners = new HashMap<>();

    private AnchorPane view;

    private WebView webView;
    private WebEngine webEngine;
    private JSObject window;

    private JavaToJSBridge javaToJsBridge;
    private JsToJavaBridge jsToJavaBridge;
    private Repository repository;

    public MapView() {
        //FIXME: set id from JS
        super(0);

        repository = new Repository();
        intView();
        initWebView();
    }

    private void intView() {
        view = new AnchorPane();
        view.setMaxWidth(Integer.MAX_VALUE);
        view.setMaxHeight(Integer.MAX_VALUE);
    }

    private void initWebView() {

        webView = new WebView();
        view.getChildren().add(webView);

        webView.setMaxHeight(Integer.MAX_VALUE);
        webView.setMaxWidth(Integer.MAX_VALUE);
        AnchorPane.setBottomAnchor(webView, 0d);
        AnchorPane.setTopAnchor(webView, 0d);
        AnchorPane.setRightAnchor(webView, 0d);
        AnchorPane.setLeftAnchor(webView, 0d);
        webEngine = webView.getEngine();
        webEngine.setJavaScriptEnabled(true);
        webEngine.load(getClass().getResource("/com/oceanos/mapmodule/html/mapview.html").toExternalForm());

       /* webEngine.setOnError((e) -> {
            System.out.println("error");
        });*/

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
                                javaToJsBridge = new JavaToJSBridge();
                                javaToJsBridge.setRepository(repository);

                                jsToJavaBridge = new JsToJavaBridge();
                                jsToJavaBridge.setJavaToJSBridge(javaToJsBridge);
                                jsToJavaBridge.setRepository(repository);
                                jsToJavaBridge.setMapView(this);
                                window.setMember("javaController", jsToJavaBridge);
                                //window.setMember("console", applicationContext.getBean(JsToJavaBridge.class));
                                JSObject jsToJava = (JSObject) webEngine.executeScript("jsToJavaBridge");
                                //jsToJava.call("echo","echo");
                                javaToJsBridge.setJsObject(jsToJava);
                                javaToJsBridge.echo("echo");
                                //id = javaToJsBridge.getMapId();


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
            if (newValue.getClass().equals(Marker.class)) {
                System.out.printf("[JAVA] click marker %d %f %f\n", newValue.getId(), ((Marker) newValue).getLatLng().getLat(), ((Marker) newValue).getLatLng().getLng());
                //listeners.get(Marker.class).handle(newValue);
                //((Marker) newValue).fireEvent(EventType.CLICK);
            }
            //listener.handle(newValue);
        });
    }


    //TODO: delegate add functions to another object
    public Marker addMarker() {
        return addMarker(51.505, -0.09);
    }

    public Marker addMarker(double lat, double lng, Map<String, Object> options) {
        Marker marker = new Marker(lat, lng, options, javaToJsBridge);
        repository.addLayer(marker);
        return marker;
    }

    public Marker addMarker(double lat, double lng) {
        return addMarker(lat, lng, new HashMap<>());
    }

    public PolyLine addPolyLine(List<LatLng> latLngs){
        return new PolyLine(latLngs, javaToJsBridge);
    }

    public Circle addCircle(LatLng latLng, double radius, LayerOptions options){
        return new Circle(javaToJsBridge, latLng, radius, options);
    }

    public Circle addCircle(LatLng latLng, double radius){
        return new Circle(javaToJsBridge,latLng, radius);
    }

    public Repository getRepository() {
        return repository;
    }


    public JavaToJSBridge getJavaToJsBridge() {
        return javaToJsBridge;
    }

    public AnchorPane getView() {
        return this.view;
    }

    public Object exec(String script){
        return webEngine.executeScript(script);
    }

    public Object runScript(java.nio.file.Path pathToFile) throws IOException {
        String s = Files.lines(pathToFile).reduce("", (x, y)->x+y);
        return exec(s);
    }


}
