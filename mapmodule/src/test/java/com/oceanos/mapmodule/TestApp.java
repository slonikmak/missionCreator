package com.oceanos.mapmodule;

import com.oceanos.mapmodule.editor.JSEditor;
import com.oceanos.mapmodule.events.EventType;
import com.oceanos.mapmodule.events.MouseEvent;
import com.oceanos.mapmodule.geometry.LatLng;
import com.oceanos.mapmodule.model.*;
import com.oceanos.mapmodule.model.options.LayerOptions;
import javafx.application.Application;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TestApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        BorderPane borderPane = new BorderPane();

        MapView mapView = new MapView();



        borderPane.setCenter(mapView.getView());

        HBox hBox = new HBox();

        Button btn = new Button("add");

        Button testBtn = new Button("test");

        Button addLineBtn = new Button("add line");

        Button startLineBtn = new Button("start");

        mapView.redyProperty().addListener((a,b,c)->{

            MapLayersGroup mainGroup = mapView.createLayersGroup();
            startLineBtn.setOnAction((e)->{
                mapView.getJavaToJsBridge().startPolyLine();
            });

            addLineBtn.setOnAction((e)->{
                List<LatLng> list = new ArrayList();
                list.add(new LatLng(45.51, -122.68));
                list.add(new LatLng(37.77, -122.43));
                PolyLine polyLine = new PolyLine(list);
                mainGroup.addMapLayer(polyLine);
                btn.setOnAction((event)->{
                    polyLine.addLatLng(new LatLng(46, -123));
                });

            });

            testBtn.setOnAction((e)->{
                //System.out.println(mapView.exec("group.getLayers().length"));
                try {
                    mapView.runScript(Paths.get("C:\\Users\\User\\Desktop\\script.txt"));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            });

            hBox.getChildren().add(btn);
            hBox.getChildren().add(testBtn);
            hBox.getChildren().add(addLineBtn);
            hBox.getChildren().add(startLineBtn);

            Label currentMarkerLabel = new Label();

            TextField lat = new TextField();
            TextField lng = new TextField();

            hBox.getChildren().add(currentMarkerLabel);
            hBox.getChildren().addAll(lat, lng);

        /*mapView.setOnMarkerClick((l)->{
            currentMarkerLabel.setText(String.valueOf(l.getId()));
            lat.setText(String.valueOf(((Marker)l).getLatLng().getLat()));
            lng.setText(String.valueOf(((Marker)l).getLatLng().getLng()));


        });*/

            mapView.addEventListener(EventType.CLICK, (e)->{
                Map<String,Object> opt = new HashMap<>();
                opt.put("draggable", true);
                Marker m = new Marker(((MouseEvent)e).getLatLng().lat, ((MouseEvent)e).getLatLng().lng);
                m.setOptions(opt);
                //Marker marker = mapView.addMarker(((MouseEvent)e).getLatLng().lat, ((MouseEvent)e).getLatLng().getLng(), opt);

                mainGroup.addMapLayer(m);
                m.bindTooltip("Tooltip \n\t");
                m.bindPopup("PopUp");

                m.addEventListener(EventType.CLICK, event -> {
                    System.out.println("click");
                });

                LayerOptions options = new LayerOptions();
                options.add("color", "red");
                options.add("weight", 5);
                options.add("fillColor", "green");

                Circle circle = new Circle(m.getLatLng(), 200, options);
                mainGroup.addMapLayer(circle);
                //Circle circle = mapView.addCircle(marker.getLatLng(), 200, options);
                //mapView.getJavaToJsBridge().bindCoords(marker.getId(), circle.getId());

                m.addEventListener(EventType.MOVE, (event)->{
                    //fixme: менять tooltip а не делать новый
                    m.bindTooltip(m.getId()+ " \n"+m.getLatLng().lat);
                    circle.setLatLng(m.getLatLng());
                });


            });

            lat.textProperty().addListener((a1,b1,c1)->{
                ((Marker)mapView.getRepository().currentLayerProperty().getValue()).setLatLng(new LatLng(Double.parseDouble(c1), ((Marker)mapView.getRepository().currentLayerProperty().getValue()).getLatLng().getLng()));
            });
        });


        borderPane.setBottom(hBox);

        /*VBox scriptBox = new VBox();
        JSEditor editor = new JSEditor();
        Button runScriptBtn = new Button("run");
        hBox.getChildren().add(editor.getView());
        hBox.getChildren().add(runScriptBtn);

        borderPane.setTop(scriptBox);

        runScriptBtn.setOnAction((e)->{
            System.out.println(editor.getScript());
            mapView.exec(editor.getScript());
        });*/


        Scene scene = new Scene(borderPane, 1000, 800);

        primaryStage.setScene(scene);

        primaryStage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
}
