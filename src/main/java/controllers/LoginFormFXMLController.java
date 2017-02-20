/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import helpdesk.MainApp;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    TextField idTFLogin;

    @FXML
    TextField idTFPassword;

    @FXML
    private void okDialog(ActionEvent actionEvent) {
        // Проверяем корректность ввода имени и пароля
        if ((idTFLogin.getText().length() > 0) && (idTFPassword.getText().length() > 0)) {
            // Сохраняем имя и пароль в бине
            this.main.setUserName(idTFLogin.getText());
            this.main.setUserPass(idTFPassword.getText());
            Stage stage = (Stage) btnOk.getScene().getWindow();
            stage.close();
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText("Поля имя и пароль не могут быть пустыми!");
            alert.setTitle("Ошибка!");
            alert.showAndWait();
        }
    }

    @FXML
    private void closeDialog(ActionEvent actionEvent) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Приложение будет закрыто");
        alert.setContentText("Вы действительно хотите выйти из приложения ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            System.out.println("Ok\n");
            Stage stage = (Stage) btnClose.getScene().getWindow();
            stage.close();
        } else {
            System.out.println("Cancel\n");
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
