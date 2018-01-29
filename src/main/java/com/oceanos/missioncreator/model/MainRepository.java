package com.oceanos.missioncreator.model;

import com.oceanos.missioncreator.interfaces.ComponentController;
import org.springframework.stereotype.Component;

@Component
public class MainRepository implements ComponentController{
    private static int count = 0;
    private String mainText = "MAIN";
    private String sampleText = "SAMPLE";

    public MainRepository(){
        count++;
    }

    public String getMainText() {
        return mainText+" "+count;
    }

    public String getSampleText() {
        return sampleText+" "+count;
    }

}
