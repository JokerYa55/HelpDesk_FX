/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import beans.sprUser;
import javafx.stage.Stage;
import javax.sql.DataSource;

/**
 *
 * @author vasil
 */
public interface controllerInterface {

    public void setDataSource(DataSource dataSource);

    public void setDialogStage(Stage dialogStage);

    public void setCurrentUser(sprUser currentUser);
    
    public void initForm();
}
