package com.oceanos.mapmodule;

import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
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

        Button btn2 = new Button("change");

        /*btn.setOnAction((e)->{
            mapView.getRepository().getLayers().get(0).pointsProperty().get(0).latProperty().add(0.001);
        });*/

        hBox.getChildren().add(btn);

        Label currentMarkerLabel = new Label();

        TextField lat = new TextField();
        TextField lng = new TextField();

        hBox.getChildren().add(currentMarkerLabel);
        hBox.getChildren().addAll(lat, lng);

        mapView.setOnMarkerClick((l)->{
            currentMarkerLabel.setText(String.valueOf(l.getId()));
            lat.setText(String.valueOf(l.getGeometry().getLatLngs().get(0).getLat()));
            lng.setText(String.valueOf(l.getGeometry().getLatLngs().get(0).getLng()));

            lat.textProperty().addListener((a,b,c)->{
                l.getGeometry().getLatLngs().get(0).setLat(Double.parseDouble(c));
            });

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
