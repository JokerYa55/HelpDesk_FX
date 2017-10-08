/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visualComponents;

import beans_JPA.TIncident;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 *
 * @author vasil
 */
public class incidentPanel extends AnchorPane {

    // Панель для размещения элементов данных инцидента
    private final GridPane gridPane = new GridPane();
    private final Label labelDate = new Label("Дата инцидента");
    private DatePicker dateIncident;
    private final Label labelFirm = new Label("Фирма");
    private TextField fFirm;
    private final Label labelService = new Label("Сервис");
    private TextField fService;
    private final Label labelComment = new Label("Комментарий");
    private TextField fComment;
    private final Label labelDateCreated = new Label("Дата создания инцидента");
    private DatePicker dateCreated;
    private final Label labelUser = new Label("Пользователь");
    private TextField fUser;
    private TIncident incident;
    private commentPanel cPanel;
    private incidentButtonPanel buttonPanel;
    
    public incidentPanel(TIncident incident) {
        this.incident = incident;
    }

}
