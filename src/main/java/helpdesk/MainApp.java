package helpdesk;

import controllers.mainController;
import DAO_JPA.TSprUsersDAO;
import beans_JPA.TSprUsers;
import controllers.LoginFormFXMLController;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.sql.DataSource;
import org.apache.log4j.Logger;

public class MainApp extends Application {

    private final Logger log = Logger.getLogger(MainApp.class);
    private Parent root;
    private Scene sceneMain;
    private String userName;
    private String userPass;
    private DataSource dataSource;
    private TSprUsers currentUser;
    private mainController mainFormController;
    private EntityManager em;

    @Override
    public void start(Stage stage) throws Exception {
        log.debug("start");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/mainForm.fxml"));
        root = loader.load();
        this.mainFormController = loader.getController();

        this.sceneMain = new Scene(root);
        this.sceneMain.getStylesheets().add("/styles/Styles.css");
        stage.setTitle("HelpDesk v. 1.0");
        stage.setScene(this.sceneMain);
        stage.setMaximized(true);
        stage.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                log.info("Resize -> " + newValue + " : " + oldValue);
                mainFormController.resizeForm();
            }
        });

        stage.show();
        showLoginDialog();

        this.dataSource = new org.springframework.jdbc.datasource.DriverManagerDataSource("jdbc:postgresql://192.168.1.240:5432/helpdesk", this.userName, this.userPass);
        log.debug(this.dataSource);
        this.mainFormController.setCurrentUser(currentUser);
        this.mainFormController.setDataSource(dataSource);
        this.mainFormController.setDialogStage(stage);
        this.mainFormController.setEm(em);
        this.mainFormController.refreshForm();
    }

    public void showLoginDialog() {
        try {
            Stage stage = new Stage();
            log.debug("showLoginDialog");
            log.debug("URL = " + getClass().getResource("/fxml/login.fxml"));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
            Parent rootLocal = loader.load();
            LoginFormFXMLController control = loader.getController();
            log.info(control);
            control.setMain(this);
            stage.setTitle("Вход");
            stage.setMinHeight(150);
            stage.setMinWidth(300);
            stage.setResizable(false);
            stage.setScene(new Scene(rootLocal));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(sceneMain.getWindow());
            stage.showAndWait();

            Map emProperties = new HashMap();
            emProperties.put("javax.persistence.jdbc.user", userName);
            emProperties.put("javax.persistence.jdbc.password", userPass);
            emProperties.put("hibernate.show_sql", true);
            // Устанавливаем EntityManager
            this.em = Persistence.createEntityManagerFactory("helpDesk_JPA", emProperties).createEntityManager();

            // Получаем текущего пользователя
            this.currentUser = (new TSprUsersDAO(em)).getItemByLogin(userName, userPass, "TSprUsers.findByFLogin", TSprUsers.class);
            log.debug("currentUser => " + this.currentUser);
            //= (new sprUsersDAO(dataSource)).getItemByName(userName);
            this.mainFormController.setStatusPanelUser(this.currentUser.getFName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
}
