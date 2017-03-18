package helpdesk;

import DAO.sprIncidentStatusDAO;
import DAO.tIncidentDAO;
import beans.sprIncidentStatus;
import beans.sprUser;
import beans.tIncident;
import beans.tIncidentComment;
import controllers.AddIncidentController;
import controllers.SprFirmController;
import controllers.SprServiceController;
import controllers.UpdIncidentController;
import interfaces.controllerInterface;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import util.utils.btnStatus;
import static util.utils.getLocalDate;

public class FXMLController implements Initializable, controllerInterface {

    private final Logger log = Logger.getLogger(FXMLController.class);
    private DataSource dataSource;
    private sprUser currentUser;
    private Stage dialogStage;
    private sprIncidentStatus currentIncidentStatus;

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
    private MenuItem idMIRepInc;

    @FXML
    private MenuBar idMBMainMenu;

    @FXML
    private PieChart idPCAll;

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
    private void handleMainMenuRepInc(ActionEvent event) {
        log.info(event);
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

        } catch (Exception e) {
            log.error(e);
        }
    }

    public void refreshForm() {
        refreshTree();
        refreshIncidentList(null);
        refreshChart();
    }

    private void refreshTree() {
        try {
            log.info("refreshTree()");
            //idTreeView.getRoot().getChildren().clear();
            List<sprIncidentStatus> itemList = (new sprIncidentStatusDAO(dataSource)).getItemList();

            ImageView imV = new ImageView(new Image(getClass().getResourceAsStream("/icons/open_mono.png")));
            imV.setFitHeight(16);
            imV.setFitWidth(16);
            Node rootIcon = imV;
            TreeItem<sprIncidentStatus> rootItem = new TreeItem("Статус", rootIcon);

            rootItem.setExpanded(true);
            for (sprIncidentStatus item : itemList) {

                ImageView imV1 = new ImageView(new Image(getClass().getResourceAsStream("/icons/folder_mono.png")));
                imV1.setFitHeight(16);
                imV1.setFitWidth(16);
                Node nodeIcon = imV1;

                TreeItem<sprIncidentStatus> node = new TreeItem(item, imV1);
                rootItem.getChildren().add(node);
            }

            idTreeView.setRoot(rootItem);

            idTreeView.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    log.debug(event);
                    if (idTreeView.getSelectionModel().getSelectedItem().getValue() instanceof sprIncidentStatus) {
                        currentIncidentStatus = idTreeView.getSelectionModel().getSelectedItem().getValue();
                        refreshIncidentList(currentIncidentStatus);
                    } else {
                        refreshIncidentList(null);
                    }
                }
            });
        } catch (Exception e) {
            log.error(e);
        }
    }

    private void refreshIncidentList(sprIncidentStatus id) {
        try {
            log.debug("refreshIncidentList");
            idAccordion.getPanes().clear();
            System.out.println("refreshIncidentList()");
            // Заполняем список инцидентов
            idAccordion.getPanes().clear();
            List<tIncident> incedentList = null;
            log.debug(id);
            if (id == null) {
                incedentList = (new tIncidentDAO(dataSource)).getItemList();
            } else {
                incedentList = (new tIncidentDAO(dataSource)).getItemListByStatus(id.getId());
            }
            for (tIncident incident : incedentList) {
                // создаем панель с информацией об инциденте
                AnchorPane panel = new AnchorPane();
                GridPane gridPane = new GridPane();
                gridPane.setVgap(6);
                gridPane.setHgap(4);
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
                TextField fUser = new TextField(incident.getFUserName());
                GridPane.setConstraints(labelUser, 2, 2);
                gridPane.getChildren().add(labelUser);
                GridPane.setConstraints(fUser, 3, 2);
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
                            (new tIncidentDAO(dataSource)).deleteItem(incident);
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

                Button btnComment = new Button("Добавить комментарий");
                btnEdit.setId("idBtnComment");
                btnEdit.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        log.info(incident);
                        //((Node)event.getSource()).
                        //showUpdIncidentForm(((Node) event.getSource()).getScene().getWindow(), incident);
                    }
                });
                hBox.getChildren().add(btnComment);
                hBox.setId("idBoxButton");

                // Добавляем панель с кнопками на грид
                //GridPane.setConstraints(hBox, 0, 6);
                //gridPane.getChildren().add(hBox);
                // Добавляем контейнер VBox
                VBox vBox = new VBox();

                vBox.getChildren().add(gridPane);

                vBox.getChildren().add(hBox);
                VBox.setMargin(hBox, new Insets(10, 10, 10, 10));

                //panel.getChildren().add(gridPane);
                // Добавляем к панели сообщения
                // Добавляем сообщения
                List<tIncidentComment> commentList = (new tIncidentDAO(dataSource)).getIncidentComment(incident);
                for (tIncidentComment itemComment : commentList) {
                    String message = itemComment.getUserName() + " : " + itemComment.getDateCreated() + "\n" + itemComment.getComment(); 
                    int rowNum = message.length()/80;
                    if (rowNum==0) rowNum ++;
                    log.debug("rowNum = " + rowNum);
                    TextArea t1 = new TextArea(message);
                    t1.setId("idTAMessage_" + itemComment.getId());
                    log.info(t1.getId());
                    t1.setEditable(false);
                    t1.setScrollLeft(5);
                    t1.setPrefHeight(45*rowNum);
                    t1.getStyleClass().add("messageQ");
                    t1.setWrapText(true);
                    vBox.getChildren().add(t1);
                    
                    t1.setStyle("text-area-background: green;");
                    /*Region region = ( Region ) t1.lookup( ".content" );
                    region.setStyle( "-fx-background-color: yellow" );*/
                    
                    VBox.setMargin(t1, new Insets(10, 10, 10, 30 * itemComment.getLevel()));
                }

                /*TextArea t2 = new TextArea("1werqwerqwer qwerqwerq sh sd hsf ghdf ghdfghd  ghdfgh dfghdfg hdf ghdf ghwer qwerqwer");
                t2.setId("idTAMessage2");
                vBox.getChildren().add(t2);*/
                //VBox.setMargin(t2, new Insets(10, 10, 10, 70));
                // Добавляем vBox на панель
                panel.getChildren().add(vBox);

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

    // Обновление диаграммы
    private void refreshChart() {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList((new tIncidentDAO(dataSource)).getStatDataByUser());
        //final PieChart chart = new PieChart(pieChartData);
        idPCAll.setData(pieChartData);
        idPCAll.setTitle("Статус заявок");
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addIncident.fxml"));
            Parent root = loader.load();
            AddIncidentController control = loader.getController();
            stage.setTitle("Новый инцидент");
            stage.setMinHeight(150);
            stage.setMinWidth(300);
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
            control.setDialogStage(stage);
            control.setCurrentUser(currentUser);
            control.setDataSource(dataSource);
            control.setDialogStage(stage);
            control.initForm();
            stage.showAndWait();
            if (control.getFormResult() == btnStatus.btnOK) {
                refreshIncidentList(currentIncidentStatus);
                refreshChart();
            }
        } catch (Exception e) {
            log.error(e);
        }
    }

    // Вызов формы добавления инцидента
    private void showUpdIncidentForm(javafx.stage.Window parentWnd, tIncident parentInc) {
        try {
            log.debug(" showAddIncidentForm()");
            log.debug("URL = " + getClass().getResource("/fxml/updIncident.fxml"));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/updIncident.fxml"));
            Parent root = loader.load();
            UpdIncidentController contr1 = loader.getController();
            log.debug(contr1);

            contr1.initFormField(parentInc);
            Stage stage = new Stage();
            stage.setTitle("Редактировать инцидент");
            stage.setMinHeight(150);
            stage.setMinWidth(300);
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(parentWnd);

            contr1.setDialogStage(stage);
            contr1.setCurrentUser(currentUser);
            contr1.setDataSource(dataSource);
            contr1.initForm();
            stage.showAndWait();
            if (contr1.getFormResult() == btnStatus.btnOK) {
                refreshIncidentList(currentIncidentStatus);
                refreshChart();
            };
        } catch (Exception e) {
            log.error(e);
        }
    }

    // Вызов формы справочника фирм
    @FXML
    private void showSprFirmForm(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            log.debug("showSprFirmForm");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/sprFirm.fxml"));
            Parent root = loader.load();
            SprFirmController control = loader.getController();
            stage.setTitle("Справочник фирм");
            stage.setMinHeight(150);
            stage.setMinWidth(300);
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(idMBMainMenu.getScene().getWindow());
            control.setDialogStage(stage);
            control.setCurrentUser(currentUser);
            control.setDataSource(dataSource);
            control.setDialogStage(stage);
            control.initForm();
            stage.show();

        } catch (Exception e) {
            log.error(e);
        }
    }

    // Вызов формы справочника фирм
    @FXML
    private void showSprServiceForm(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            log.debug("showSprServiceForm");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/sprService.fxml"));
            Parent root = loader.load();
            SprServiceController control = loader.getController();
            stage.setTitle("Справочник услуг");
            stage.setMinHeight(150);
            stage.setMinWidth(300);
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(idMBMainMenu.getScene().getWindow());
            control.setDialogStage(stage);
            control.setCurrentUser(currentUser);
            control.setDataSource(dataSource);
            control.setDialogStage(stage);
            control.initForm();
            stage.show();

        } catch (Exception e) {
            log.error(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        log.debug("initialize");
    }

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setStatusPanelUser(String userName) {
        idLUserName.setText(userName);
    }

    @Override
    public void setCurrentUser(sprUser currentUser) {
        this.currentUser = currentUser;
    }

    @Override
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @Override
    public void initForm() {
        log.debug("initForm");
        idTreeView.getStyleClass().add("my-tree-view");
    }
    //
}
