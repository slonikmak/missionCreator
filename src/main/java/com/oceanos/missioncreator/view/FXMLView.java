package com.oceanos.missioncreator.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @autor slonikmak on 23.09.2017.
 */
public class FXMLView {
    private static ApplicationContext context;

    private Parent node;
    private String fxmlExtension = ".fxml";

    public FXMLView(){
        initView();
    }

    private void initView(){
        FXMLLoader loader = new FXMLLoader();
        loader.setControllerFactory(context::getBean);
        loader.setLocation(getClass().getResource(getFXMLFileName()));
        try {
            node = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getFXMLFileName(){
        String className = getClass().getSimpleName().replace("View","").toLowerCase();
        return "/" + className + "/" + className + fxmlExtension;
    }


    public Parent getNode(){
        return node;
    }

    public static void init(String contextPth){
        context = new ClassPathXmlApplicationContext(contextPth);
    }
}
