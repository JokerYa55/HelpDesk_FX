/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visualComponents;

import beans_JPA.TIncident;
import beans_JPA.TIncidentComment;
import java.util.LinkedList;
import java.util.List;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import static util.utils.getLocalDate;

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
    private commentPanel commentPanel;

    public incidentPanel(TIncident incident) {
        this.incident = incident;
    }

    /**
     *
     */
    private void createGrid() {
        //GridPane gridPane = new GridPane();
        gridPane.setVgap(6);
        gridPane.setHgap(4);

        // добавляем дату инцидента
        DatePicker dateIncident = new DatePicker();
        Label labelDate = new Label("Дата инцидента");
        //labelDate.setStyle("incident");
        dateIncident.setValue(getLocalDate(incident.getFDate()));
        GridPane.setConstraints(labelDate, 0, 0);
        gridPane.getChildren().add(labelDate);
        GridPane.setConstraints(dateIncident, 1, 0);
        gridPane.getChildren().add(dateIncident);

        // Добавляем фирму 
        Label labelFirm = new Label("Фирма");
        TextField fFirm = new TextField(incident.getFFirmId().getFName());
        GridPane.setConstraints(labelFirm, 0, 1);
        gridPane.getChildren().add(labelFirm);
        GridPane.setConstraints(fFirm, 1, 1);
        gridPane.getChildren().add(fFirm);

        // Добавляем услугу 
        Label labelService = new Label("Сервис");
        TextField fService = new TextField(incident.getFServiceId().getFName());
        GridPane.setConstraints(labelService, 0, 2);
        gridPane.getChildren().add(labelService);
        GridPane.setConstraints(fService, 1, 2);
        gridPane.getChildren().add(fService);

        // Добавляем комментарий 
        Label labelComment = new Label("Комментарий");
        TextField fComment = new TextField(incident.getFComment());
        GridPane.setConstraints(labelComment, 2, 0);
        gridPane.getChildren().add(labelComment);
        GridPane.setConstraints(fComment, 3, 0);
        gridPane.getChildren().add(fComment);

        // добавляем дату создания
        Label labelDateCreated = new Label("Дата создания инцидента");
        DatePicker dateCreated = new DatePicker();
        dateCreated.setValue(getLocalDate(incident.getFDateCreated()));
        GridPane.setConstraints(labelDateCreated, 2, 1);
        gridPane.getChildren().add(labelDateCreated);
        GridPane.setConstraints(dateCreated, 3, 1);
        gridPane.getChildren().add(dateCreated);

        // Добавляем пользователя 
        Label labelUser = new Label("Пользователь");
        TextField fUser = new TextField(incident.getFUserId().getFName());
        GridPane.setConstraints(labelUser, 2, 2);
        gridPane.getChildren().add(labelUser);
        GridPane.setConstraints(fUser, 3, 2);
        gridPane.getChildren().add(fUser);
    }

    /**
     * 
     */
    private void addComments() {
        List<TIncidentComment> commentList = new LinkedList(incident.getTIncidentCommentCollection());
        this.commentPanel = new commentPanel(commentList);
    }

    /**
     * 
     */
    private void addButtonPanel() {

    }
}
