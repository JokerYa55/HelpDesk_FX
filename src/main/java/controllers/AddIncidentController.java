/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import DAO.sprFirmDAO;
import DAO.sprServiceDAO;
import DAO.tIncidentDAO;
import beans.sprFirm;
import beans.sprService;
import beans.sprUser;
import beans.tIncident;
import beans_JPA.TSprUsers;
import interfaces.controllerInterface;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import util.utils;
import static util.utils.NOW_LOCAL_DATE;

/**
 * FXML Controller class
 *
 * @author vasil
 */
public class AddIncidentController implements Initializable, controllerInterface {

    /**
     * Initializes the controller class.
     */
    private final Logger log = Logger.getLogger(AddIncidentController.class);
    private Stage dialogStage;
    private TSprUsers currentUser;
    private DataSource dataSource;
    private utils.btnStatus formResult = utils.btnStatus.btnCancel; 

    @FXML
    DatePicker idDPFDate;

    @FXML
    ComboBox idCBFirm;

    @FXML
    ComboBox idCBService;

    @FXML
    TextField idTFComment;

    @FXML
    DatePicker idDPDateCreated;

    @FXML
    TextField idTFUser;

    @FXML
    Button idBtnSave;

    @FXML
    Button idBtnCancel;

    @FXML
    public void btnSaveClick(ActionEvent actionEvent) {
        // Нажатие на кнопку сохранить
        log.info("btnSaveClick -> " + actionEvent);
        tIncident item = new tIncident();
        item.setFComment(idTFComment.getText());

        LocalDate localDate = idDPFDate.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        Date date = Date.from(instant);
        item.setFDate(date);
        item.setFFirmId(((sprFirm) idCBFirm.getSelectionModel().getSelectedItem()).getId());
        item.setFServiceId(((sprService) idCBService.getSelectionModel().getSelectedItem()).getId());
        item.setFComment(idTFComment.getText());
        item.setFDateCreated(date);
        item.setFUserId(currentUser.getId());
        item.setFIncidentStatusId(new Long(1));
        log.info(item.toString());
        (new tIncidentDAO(dataSource)).addItem(item);
        formResult = utils.btnStatus.btnOK;
        this.dialogStage.close();
    }

    @FXML
    public void btnCancelClick(ActionEvent actionEvent) {
        // Нажатие на кнопку сохранить
        log.info("btnCancelClick -> " + actionEvent);
        this.dialogStage.close();
    }

    private ObservableList<sprFirm> getFirmList() {
        ObservableList<sprFirm> firmList = FXCollections.observableArrayList();
        List<sprFirm> tList = (new sprFirmDAO(dataSource).getItemList());
        tList.forEach((item) -> {
            firmList.add(item);
        });
        return firmList;
    }

    private ObservableList<sprService> getServiceList() {
        ObservableList<sprService> serviceList = FXCollections.observableArrayList();
        List<sprService> tList = (new sprServiceDAO(dataSource).getItemList());
        tList.forEach((item) -> {
            serviceList.add(item);
        });
        return serviceList;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        log.info("initialize -> " + url);

    }

    @Override
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @Override
    public void setCurrentUser(TSprUsers currentUser) {
        this.currentUser = currentUser;
    }

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void initForm() {
        try {
            idDPFDate.setValue(NOW_LOCAL_DATE());
            idCBFirm.getItems().addAll(getFirmList());
            idCBService.getItems().addAll(getServiceList());
            idDPDateCreated.setValue(NOW_LOCAL_DATE());
            idTFUser.setText("Test");
        } catch (Exception e) {
            log.error(e);
        }
    }

    public utils.btnStatus getFormResult() {
        return formResult;
    }

}
