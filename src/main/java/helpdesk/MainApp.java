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
import org.apache.log4j.Logger;

public class MainApp extends Application {

    private Logger log = Logger.getLogger(MainApp.class);
    private Parent root;
    private Scene scene;

    @Override
    public void start(Stage stage) throws Exception {
        log.debug("start");
        FXMLLoader loader = new FXMLLoader();
        root = FXMLLoader.load(getClass().getResource("/fxml/mainForm.fxml"));
        scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        stage.setTitle("HelpDesk v. 1.0");
        stage.setScene(scene);
        //stage.setMaximized(true);
        stage.show();
        //showDialog();
    }

    
     public void showDialog() {
        try {
            Stage stage = new Stage();
            log.debug("showDialog");
            log.debug("URL = " + getClass().getResource("/fxml/login.fxml"));
            FXMLLoader loader = new FXMLLoader();
            Parent root = loader.load(getClass().getResource("/fxml/login.fxml"));
            //LoginFormFXMLController controller = loader.getController();
            //controller.setMain(this);
            stage.setTitle("Вход");
            stage.setMinHeight(150);
            stage.setMinWidth(300);
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(scene.getWindow());
            stage.show();

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
}
