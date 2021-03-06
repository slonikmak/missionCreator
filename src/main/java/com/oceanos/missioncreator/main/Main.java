package com.oceanos.missioncreator.main;

import com.oceanos.missioncreator.controllers.MainView;
import com.oceanos.missioncreator.view.FXMLView;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @autor slonikmak on 23.09.2017.
 */
public class Main extends Application {

    //private ApplicationContext context;


    @Override
    public void start(Stage primaryStage) throws Exception {
        MainView view = new MainView();
        Parent parent = view.getNode();
        primaryStage.setScene(new Scene(parent));
        primaryStage.show();

    }

    @Override
    public void init(){
        FXMLView.init("appContext.xml");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
