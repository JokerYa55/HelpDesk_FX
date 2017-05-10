package helpdesk;

import DAO.sprUsersDAO;
import beans.sprUser;
import controllers.LoginFormFXMLController;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.sql.DataSource;
import org.apache.log4j.Logger;

public class MainApp extends Application {

    private final Logger log = Logger.getLogger(MainApp.class);
    private Parent root;
    private Scene sceneMain;
    private String userName;
    private String userPass;
    private DataSource dataSource;
    private sprUser currentUser;
    private FXMLController mainFormController;

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
        this.mainFormController.setCurrentUser(currentUser);
        this.mainFormController.setDataSource(dataSource);
        this.mainFormController.refreshForm();
    }

    public void showLoginDialog() {
        try {
            Stage stage = new Stage();
            log.debug("showDialog");
            log.debug("URL = " + getClass().getResource("/fxml/login.fxml"));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
            Parent root = loader.load();
            LoginFormFXMLController control = loader.getController();
            log.info(control);
            control.setMain(this);
            stage.setTitle("Вход");
            stage.setMinHeight(150);
            stage.setMinWidth(300);
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(sceneMain.getWindow());
            stage.showAndWait();
            this.dataSource = new org.springframework.jdbc.datasource.DriverManagerDataSource("jdbc:postgresql://192.168.1.240:5432/helpdesk", this.userName, this.userPass);
            log.debug(this.dataSource);
            this.currentUser = (new sprUsersDAO(dataSource)).getItemByName(userName);

            // Получаем текущего пользователя
            this.currentUser = (new sprUsersDAO(dataSource)).getItemByName(userName);
            this.mainFormController.setStatusPanelUser(this.currentUser.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
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
}
