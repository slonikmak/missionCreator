package com.oceanos.mapmodule;

import com.oceanos.mapmodule.editor.JSEditor;
import com.oceanos.mapmodule.events.EventType;
import com.oceanos.mapmodule.events.MouseEvent;
import com.oceanos.mapmodule.geometry.LatLng;
import com.oceanos.mapmodule.model.Circle;
import com.oceanos.mapmodule.model.MapView;
import com.oceanos.mapmodule.model.Marker;
import com.oceanos.mapmodule.model.PolyLine;
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
        });

        /*testBtn.setOnAction((e)->{
            //System.out.println(mapView.exec("group.getLayers().length"));
            try {
                mapView.runScript(Paths.get("C:\\Users\\User\\Desktop\\script.txt"));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });*/

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
            Marker marker = mapView.addMarker(((MouseEvent)e).getLatLng().lat, ((MouseEvent)e).getLatLng().getLng(), opt);
            marker.bindTooltip("Tooltip \n\t");
            marker.bindPopup("PopUp");
            marker.setOnClick((event)-> System.out.println("click"));


        });

        lat.textProperty().addListener((a,b,c)->{
            ((Marker)mapView.getRepository().currentLayerProperty().getValue()).setLatLng(new LatLng(Double.parseDouble(c), ((Marker)mapView.getRepository().currentLayerProperty().getValue()).getLatLng().getLng()));
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
