/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import DAO.sprServiceDAO;
import beans.sprService;
import beans.sprUser;
import interfaces.controllerInterface;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
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
public class SprServiceController implements Initializable, controllerInterface {

    /**
     * Initializes the controller class.
     */
    private final Logger log = Logger.getLogger(SprServiceController.class);
    private Stage dialogStage;
    private sprUser currentUser;
    private DataSource dataSource;

    @FXML
    TableColumn<sprService, Long> idTCId;

    @FXML
    TableColumn<sprService, String> idTCServiceName;

    @FXML
    TableView<sprService> idTVMainData;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try {
            log.debug("initForm()");
            List<sprService> listItem = (new sprServiceDAO(dataSource)).getItemList();
            ObservableList<sprService> sprFirmList = FXCollections.observableArrayList(listItem);
            idTCId.setCellValueFactory(new PropertyValueFactory<>("Id"));
            idTCServiceName.setCellValueFactory(new PropertyValueFactory<>("Name"));
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
                (new sprServiceDAO(dataSource)).addItem(new sprService(null, result.get()));
            }
        } catch (Exception e) {
            log.error(e);
        }

    }

}
