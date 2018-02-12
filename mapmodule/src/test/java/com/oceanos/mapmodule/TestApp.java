package com.oceanos.mapmodule;

import com.oceanos.mapmodule.events.EventType;
import com.oceanos.mapmodule.geometry.LatLng;
import com.oceanos.mapmodule.model.MapView;
import com.oceanos.mapmodule.model.Marker;
import com.oceanos.mapmodule.model.PolyLine;
import javafx.application.Application;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

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



        Button btn2 = new Button("bind Popup");

        Button addLineBtn = new Button("add line");

        Button startLineBtn = new Button("start");

        startLineBtn.setOnAction((e)->{
            mapView.getJavaToJsBridge().startPolyLine();
        });

        addLineBtn.setOnAction((e)->{
            List<LatLng> list = new ArrayList();
            list.add(new LatLng(45.51, -122.68));
            list.add(new LatLng(37.77, -122.43));
            PolyLine line = mapView.addPolyLine(list);
            btn.setOnAction((event)->{
                line.addLatLng(new LatLng(46, -123));
            });

            final LatLng latLng = new LatLng(37.77, -122.43);

            new Thread(()->{
                while (true){
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    Platform.runLater(()->{
                        latLng.lat+=1;
                        latLng.lng+=1;
                        line.addLatLng(latLng);
                    });
                }

            }).start();

        });

        btn2.setOnAction((e)->{
            ((Marker)mapView.getRepository().currentLayerProperty().getValue()).bindPopup("OOOOOO");
        });

        hBox.getChildren().add(btn);
        hBox.getChildren().add(btn2);
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
            Marker marker = mapView.addMarker(e.getLatLng().lat, e.getLatLng().getLng(), opt);
            marker.bindTooltip("Tooltip");
            marker.addEventListner(EventType.MOVE, (event)->{
                marker.bindTooltip(marker.getId()+ " \n"+marker.getLatLng().lat);
            });
        });

        lat.textProperty().addListener((a,b,c)->{
            ((Marker)mapView.getRepository().currentLayerProperty().getValue()).setLatLng(new LatLng(Double.parseDouble(c), ((Marker)mapView.getRepository().currentLayerProperty().getValue()).getLatLng().getLng()));
        });
        borderPane.setBottom(hBox);


        Scene scene = new Scene(borderPane, 500, 500);

        primaryStage.setScene(scene);

        primaryStage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
}
