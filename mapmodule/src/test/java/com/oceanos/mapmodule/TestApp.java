package com.oceanos.mapmodule;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class TestApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        BorderPane borderPane = new BorderPane();

        MapView mapView = new MapView();

        borderPane.setCenter(mapView);

        Button btn = new Button("add");

        btn.setOnAction((e)->mapView.addMarker());

        Button btn2 = new Button("change");

        /*btn.setOnAction((e)->{
            mapView.getRepository().getLayers().get(0).pointsProperty().get(0).latProperty().add(0.001);
        });*/

        borderPane.setBottom(btn);


        Scene scene = new Scene(borderPane, 500, 500);

        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
