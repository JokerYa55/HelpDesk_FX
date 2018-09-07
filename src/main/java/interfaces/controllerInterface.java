/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import beans_JPA.TSprUsers;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.sql.DataSource;

/**
 *
 * @author vasil
 */
public interface controllerInterface {

    public void setEM(EntityManager em);

    public void setDataSource(DataSource dataSource);

    public void setDialogStage(Stage dialogStage);

    public void setCurrentUser(TSprUsers currentUser);

    public void initForm();

    //public formLocator showDialog(DataSource dataSource, Stage stage, Parent root, String windowCaption);
}
