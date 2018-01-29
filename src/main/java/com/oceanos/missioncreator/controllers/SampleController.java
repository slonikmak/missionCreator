package com.oceanos.missioncreator.controllers;

import com.oceanos.missioncreator.interfaces.ComponentController;
import com.oceanos.missioncreator.model.MainRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @autor slonikmak on 23.09.2017.
 */
@Component
public class SampleController implements ComponentController {
    @Autowired
    private MainRepository repository;


    @FXML
    Label sampleLabel;

    public void initialize(){
        sampleLabel.setText(repository.getSampleText());
    }
}

