/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.sprUser;
import interfaces.controllerInterface;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javax.sql.DataSource;
import org.apache.log4j.Logger;

/**
 * FXML Controller class
 *
 * @author vasil
 */
public class SprFirmController implements Initializable, controllerInterface {

    /**
     * Initializes the controller class.
     */
    private final Logger log = Logger.getLogger(SprFirmController.class);
    private Stage dialogStage;
    private sprUser currentUser;
    private DataSource dataSource;

    @FXML
    TableView idTVMainData;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        idTVMainData.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                log.debug(oldValue);
                log.debug(newValue);
            }
        });
    }

    @Override
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @Override
    public void setCurrentUser(sprUser currentUser) {
        this.currentUser = currentUser;
    }

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void initForm() {
        log.debug("initForm()");
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
