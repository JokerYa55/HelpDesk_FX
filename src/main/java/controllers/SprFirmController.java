/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import DAO.sprFirmDAO;
import beans.sprFirm;
import beans.sprUser;
import interfaces.controllerInterface;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
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
    TableView<sprFirm> idTVMainData;

    @FXML
    TableColumn<sprFirm, Long> idTCId;

    @FXML
    TableColumn<sprFirm, String> idTCFirmName;

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
        try {
            log.debug("initForm()");
            List<sprFirm> listItem = (new sprFirmDAO(dataSource)).getItemList();
            ObservableList<sprFirm> sprFirmList = FXCollections.observableArrayList(listItem);
            idTCId.setCellValueFactory(new PropertyValueFactory<>("Id"));
            idTCFirmName.setCellValueFactory(new PropertyValueFactory<>("Name"));
            idTVMainData.setItems(sprFirmList);
        } catch (Exception e) {
            log.error(e);
        }
    }

    @FXML
    private void btnAddClick(ActionEvent actionEvent) {
        try {
            log.debug(actionEvent);
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Добавить новую фирму");
            dialog.setHeaderText(null);
            dialog.setContentText("Введите наименование:");

            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                System.out.println("Your name: " + result.get());
                (new sprFirmDAO(dataSource)).addItem(new sprFirm(null, result.get()));
            }

        } catch (Exception e) {
            log.error(e);
        }

    }
}
