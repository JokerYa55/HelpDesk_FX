/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import helpdesk.MainApp;
import helpdesk.MainApp;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author vasil
 */
public class LoginFormFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    public MainApp main;
    private Stage dialogStage;

    @FXML
    Button btnClose;

    @FXML
    Button btnOk;

    @FXML
    private void okDialog(ActionEvent actionEvent) {
        Stage stage = (Stage) btnOk.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void closeDialog(ActionEvent actionEvent) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Приложение будет закрыто");
        alert.setContentText("Вы действительно хотите выйти из приложения ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            System.out.println("Ok");
            Stage stage = (Stage) btnClose.getScene().getWindow();
            stage.close();
        } else {
            System.out.println("Cancel");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO        
    }

    public void setMain(MainApp main) {
        this.main = main;
    }

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

}
