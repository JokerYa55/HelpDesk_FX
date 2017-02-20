package helpdesk;

import DAO.sprFirmDAO;
import DAO.sprIncidentStatusDAO;
import DAO.tIncidentDAO;
import beans.sprIncidentStatus;
import beans.tIncident;
import controllers.UpdIncidentController;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import static util.utils.getLocalDate;

public class FXMLController implements Initializable {

    private final Logger log = Logger.getLogger(FXMLController.class);
    private DataSource dataSource;

    @FXML
    private Label label;

    @FXML
    private Label idLUserName;

    @FXML
    private MenuItem idMainMenuExit;

    @FXML
    private MenuItem idMIAbout;

    @FXML
    Accordion idAccordion;

    @FXML
    TreeView<sprIncidentStatus> idTreeView;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
        (new sprFirmDAO()).getItemById(Long.MIN_VALUE);
    }

    @FXML
    private void newIncidentButtonAction(ActionEvent event) {
        log.info("newIncidentButtonAction -> " + event.toString());
        showAddIncidentForm(event);
    }

    @FXML
    private void refreshButtonAction(ActionEvent event) {
        refreshForm();
    }

    @FXML
    private void handleMainMenuExit(ActionEvent event) {
        log.info(event);
        System.exit(0);
    }

    @FXML
    private void handleMainMenuAbout(ActionEvent event) {
        log.info("About");
        showAbout(event);
    }

    @FXML
    public void showDialog(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            log.debug("showDialog");
            log.debug("URL = " + getClass().getResource("/fxml/login.fxml"));

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
            Parent root = loader.load();

            UpdIncidentController contr1 = loader.getController();

            stage.setTitle("Новый инцидент");
            stage.setMinHeight(150);
            stage.setMinWidth(300);
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setStatusPanelUser(String userName) {
        idLUserName.setText(userName);
    }

    public void refreshForm() {
        refreshTree();
        refreshIncidentList(null);
    }

    private void refreshTree() {
        try {
            log.info("refreshTree()");
            //idTreeView.getRoot().getChildren().clear();
            List<sprIncidentStatus> itemList = (new sprIncidentStatusDAO(dataSource)).getItemList();
            TreeItem<sprIncidentStatus> rootItem = new TreeItem(null);
            rootItem.setExpanded(true);
            for (sprIncidentStatus item : itemList) {
                TreeItem<sprIncidentStatus> node = new TreeItem(item);
                rootItem.getChildren().add(node);
            }

            idTreeView.setRoot(rootItem);
            idTreeView.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    log.debug(event);
                    //log.debug(idTreeView.getSelectionModel().getSelectedItem().getValue());                    
                    refreshIncidentList(idTreeView.getSelectionModel().getSelectedItem().getValue());
                }
            });
        } catch (Exception e) {
            log.error(e);
        }
    }

    private void refreshIncidentList(sprIncidentStatus id) {
        try {
            idAccordion.getPanes().clear();
            System.out.println("refreshIncidentList()");
            // Заполняем список инцидентов
            idAccordion.getPanes().clear();
            List<tIncident> incedentList = null;
            if (id == null) {
                incedentList = (new tIncidentDAO(dataSource)).getItemList();
            } else {
                incedentList = (new tIncidentDAO(dataSource)).getItemListByStatus(id.getId());
            }
            for (tIncident incident : incedentList) {
                // создаем панель с информацией об инциденте
                AnchorPane panel = new AnchorPane();
                GridPane gridPane = new GridPane();
                gridPane.setVgap(3);
                gridPane.setHgap(2);
                DatePicker dateIncident = new DatePicker();

                // добавляем дату инцидента
                Label labelDate = new Label("Дата инцидента");
                //labelDate.setStyle("incident");
                dateIncident.setValue(getLocalDate(incident.getFDate()));
                GridPane.setConstraints(labelDate, 0, 0);
                gridPane.getChildren().add(labelDate);
                GridPane.setConstraints(dateIncident, 1, 0);
                gridPane.getChildren().add(dateIncident);

                // Добавляем фирму 
                Label labelFirm = new Label("Фирма");
                TextField fFirm = new TextField(incident.getFFirmName());
                GridPane.setConstraints(labelFirm, 0, 1);
                gridPane.getChildren().add(labelFirm);
                GridPane.setConstraints(fFirm, 1, 1);
                gridPane.getChildren().add(fFirm);

                // Добавляем услугу 
                Label labelService = new Label("Сервис");
                TextField fService = new TextField(incident.getFServiceName());
                GridPane.setConstraints(labelService, 0, 2);
                gridPane.getChildren().add(labelService);
                GridPane.setConstraints(fService, 1, 2);
                gridPane.getChildren().add(fService);

                // Добавляем комментарий 
                Label labelComment = new Label("Комментарий");
                TextField fComment = new TextField(incident.getFComment());
                GridPane.setConstraints(labelComment, 0, 3);
                gridPane.getChildren().add(labelComment);
                GridPane.setConstraints(fComment, 1, 3);
                gridPane.getChildren().add(fComment);

                // добавляем дату создания
                Label labelDateCreated = new Label("Дата создания инцидента");
                DatePicker dateCreated = new DatePicker();
                dateCreated.setValue(getLocalDate(incident.getFDateCreated()));
                GridPane.setConstraints(labelDateCreated, 0, 4);
                gridPane.getChildren().add(labelDateCreated);
                GridPane.setConstraints(dateCreated, 1, 4);
                gridPane.getChildren().add(dateCreated);

                // Добавляем пользователя 
                Label labelUser = new Label("Пользователь");
                TextField fUser = new TextField(incident.getFUserName());
                GridPane.setConstraints(labelUser, 0, 5);
                gridPane.getChildren().add(labelUser);
                GridPane.setConstraints(fUser, 1, 5);
                gridPane.getChildren().add(fUser);

                HBox hBox = new HBox();

                // Добавляем кнопку удалить
                Button btnDel = new Button("Удалить");
                btnDel.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        Alert alertForm = new Alert(Alert.AlertType.CONFIRMATION);

                        alertForm.setTitle("Удаление инцидента");
                        alertForm.setHeaderText("Вы действительно хотите удалить инцидент ? ");
                        alertForm.setContentText("");

                        Optional<ButtonType> result = alertForm.showAndWait();
                        if (result.get() == ButtonType.OK) {
                            log.info(result.get());
                            (new tIncidentDAO()).deleteItem(incident);
                        } else {
                            log.info(result.get());
                        }
                    }
                });
                hBox.getChildren().add(btnDel);

                // Добавляем кнопку редактировать
                Button btnEdit = new Button("Редактировать");
                btnEdit.setId("idBtnEdit");
                btnEdit.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        log.info(incident);
                        //((Node)event.getSource()).
                        showUpdIncidentForm(((Node) event.getSource()).getScene().getWindow(), incident);
                    }
                });
                hBox.getChildren().add(btnEdit);
                GridPane.setConstraints(hBox, 0, 6);
                gridPane.getChildren().add(hBox);

                panel.getChildren().add(gridPane);

                TitledPane tp = new TitledPane(String.format("Инцидент № %1$d от %2$tF \nСтатус: %3$s\nФирма: %4$s", incident.getId(), incident.getFDate(), incident.getFIncidentStatusName(), incident.getFFirmName()), panel);
                tp.getStyleClass().add("incidentErr");
                tp.setOnMousePressed((MouseEvent event) -> {
                    log.debug(incident);
                });
                idAccordion.getPanes().add(tp);

            }
        } catch (Exception e) {
            log.error(e);
        }
    }

    // Вызов формы "О программе"
    private void showAbout(ActionEvent actionEvent) {
        try {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("О программе");
            alert.setHeaderText(null);
            alert.setContentText("Программа учета инцидентов в. 1.0");

            alert.showAndWait();
        } catch (Exception e) {
            log.error(e);
        }
    }

    // Вызов формы добавления инцидента
    private void showAddIncidentForm(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            log.debug(" showAddIncidentForm()");
            log.debug("URL = " + getClass().getResource("/fxml/addIncident.fxml"));
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/addIncident.fxml"));
            stage.setTitle("Новый инцидент");
            stage.setMinHeight(150);
            stage.setMinWidth(300);
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Вызов формы добавления инцидента
    private void showUpdIncidentForm(javafx.stage.Window parentWnd, tIncident parentInc) {
        try {
            Stage stage = new Stage();
            log.debug(" showAddIncidentForm()");
            log.debug("URL = " + getClass().getResource("/fxml/updIncident.fxml"));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/updIncident.fxml"));
            Parent root = loader.load();
            UpdIncidentController contr1 = loader.getController();
            log.debug(contr1);
            //contr1.setIncident(parentInc);

            contr1.initFormField(parentInc);

            stage.setTitle("Редактировать инцидент");
            stage.setMinHeight(150);
            stage.setMinWidth(300);
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(parentWnd);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //refreshForm();
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
