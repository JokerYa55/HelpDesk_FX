package helpdesk;

import controllers.LoginFormFXMLController;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
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
    private Scene scene;
    private String userName;
    private String userPass;
    private DataSource dataSource;

    @Override
    public void start(Stage stage) throws Exception {
        log.debug("start");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/mainForm.fxml"));
        root = loader.load();
        FXMLController mainFormController = loader.getController();
        scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        stage.setTitle("HelpDesk v. 1.0");
        stage.setScene(scene);
        //stage.setMaximized(true);
        stage.show();
        showLoginDialog();
        mainFormController.setDataSource(dataSource);
        mainFormController.refreshForm();
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
            stage.initOwner(scene.getWindow());
            stage.showAndWait();
            this.dataSource = new org.springframework.jdbc.datasource.DriverManagerDataSource("jdbc:postgresql://192.168.1.250:5432/service_desk", this.userName, this.userPass);
            log.debug(this.dataSource);
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
