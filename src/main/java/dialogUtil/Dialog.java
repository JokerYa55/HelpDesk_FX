/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dialogUtil;

import interfaces.dailogInterface;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

/**
 *
 * @author vasil
 * @param <T>
 */
public class Dialog<T> implements dailogInterface<T> {

    private final Logger log = Logger.getLogger(getClass().getName());
    private String resourceName = null;
    private final pWnd<T> pDialog;
    private final dialogType dType;

    public Dialog(String resourceName, dialogType type) {
        this.resourceName = resourceName;
        this.pDialog = createDialog();
        this.dType = type;
    }

    private pWnd<T> createDialog() {
        pWnd<T> res = null;
        try {
            res = new pWnd<>();
            Stage stage = new Stage();
            res.setStage(stage);
            FXMLLoader loader = new FXMLLoader(getClass().getResource(this.resourceName));

            res.setController(loader.getController());
        } catch (Exception e) {
            log.log(Priority.ERROR, res);
        }
        return res;
    }

    @Override
    public pWnd<T> getPWnd() {
        return this.pDialog;
    }

    @Override
    public void show() {
        if (dType == dialogType.FORM) {
            this.pDialog.getStage().show();
        } else {
            this.pDialog.getStage().showAndWait();
        }
    }

    @Override
    public void close() {
        this.pDialog.getStage().close();
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

}
