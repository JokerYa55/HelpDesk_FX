/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visualComponents;

import beans_JPA.TIncidentComment;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author vasil
 */
public class commentPanel extends AnchorPane {

    private TextArea textComment;
    private TIncidentComment comment;

    public commentPanel(TIncidentComment comment) {
        this.comment = comment;
    }

}
