/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dialogUtil;

import javafx.stage.Stage;

/**
 *
 * @author vasil
 */
public class pWnd <T> {
    private Stage stage;
    private T controller;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public T getController() {
        return controller;
    }

    public void setController(T controller) {
        this.controller = controller;
    }
    
}
