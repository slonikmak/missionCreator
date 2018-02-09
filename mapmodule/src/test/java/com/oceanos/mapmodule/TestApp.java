package com.oceanos.mapmodule;

import com.oceanos.mapmodule.geometry.LatLng;
import com.oceanos.mapmodule.model.Marker;
import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class TestApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        BorderPane borderPane = new BorderPane();

        MapView mapView = new MapView();

        borderPane.setCenter(mapView);

        HBox hBox = new HBox();



        Button btn = new Button("add");

        btn.setOnAction((e)->mapView.addMarker());

        Button btn2 = new Button("bind Popup");

        Button addLineBtn = new Button("add line");

        addLineBtn.setOnAction((e)->{
            mapView.getJavaToJsBridge().addLine();
        });

        btn2.setOnAction((e)->{
            ((Marker)mapView.getRepository().currentLayerProperty().getValue()).bindPopup("OOOOOO");
        });

        hBox.getChildren().add(btn);
        hBox.getChildren().add(btn2);
        hBox.getChildren().add(addLineBtn);

        Label currentMarkerLabel = new Label();

        TextField lat = new TextField();
        TextField lng = new TextField();

        hBox.getChildren().add(currentMarkerLabel);
        hBox.getChildren().addAll(lat, lng);

        mapView.setOnMarkerClick((l)->{
            currentMarkerLabel.setText(String.valueOf(l.getId()));
            lat.setText(String.valueOf(((Marker)l).getLatLng().getLat()));
            lng.setText(String.valueOf(((Marker)l).getLatLng().getLng()));


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
