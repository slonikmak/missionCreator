package com.oceanos.missioncreator.interfaces;

import javax.annotation.PostConstruct;

/**
 * @autor slonikmak on 23.09.2017.
 */
public interface ComponentController{
    @PostConstruct
    default void postConstruct(){
        System.out.println(getClass().getName()+" constructed");
    }
}
