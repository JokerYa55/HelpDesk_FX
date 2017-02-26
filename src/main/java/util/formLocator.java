/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import interfaces.controllerInterface;
import javafx.scene.Scene;

/**
 *
 * @author vasil
 */
public class formLocator implements AutoCloseable{
    private Scene scene;
    private controllerInterface controller;

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public controllerInterface getController() {
        return controller;
    }

    public void setController(controllerInterface controller) {
        this.controller = controller;
    }

    @Override
    public void close() throws Exception {
        
    }
    
}
