/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import org.apache.log4j.Logger;

/**
 *
 * @author vasil
 */
public class sprIncidentStatus {

    private Logger log = Logger.getLogger(sprIncidentStatus.class);

    private Long Id;
    private String Name;

    public sprIncidentStatus(Long Id, String Name) {
        this.Id = Id;
        this.Name = Name;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    @Override
    public String toString() {
        return this.Name;
    }
    
    
}
