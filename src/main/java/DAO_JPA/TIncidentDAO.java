/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_JPA;

import beans_JPA.TIncident;
import interfaces.daoInterface;
import javax.persistence.EntityManager;
import org.apache.log4j.Logger;

/**
 *
 * @author vasil
 */
public class TIncidentDAO implements daoInterface<TIncident, Long> {

    private final Logger log = Logger.getLogger(getClass().getName());
    private final EntityManager em;

    public TIncidentDAO(EntityManager em) {
        this.em = em;
    }

    @Override
    public EntityManager getEM() {
        return em;
    }
}
