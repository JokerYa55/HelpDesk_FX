/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import beans.sprUser;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.sql.DataSource;
import util.formLocator;

/**
 *
 * @author vasil
 */
public interface controllerInterface {

    public void setDataSource(DataSource dataSource);

    public void setDialogStage(Stage dialogStage);

    public void setCurrentUser(sprUser currentUser);
    
    public void initForm();
    
    //public formLocator showDialog(DataSource dataSource, Stage stage, Parent root, String windowCaption);
}
