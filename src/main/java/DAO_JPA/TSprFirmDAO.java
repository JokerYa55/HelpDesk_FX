/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_JPA;

import beans_JPA.TSprFirm;
import interfaces.daoInterface;
import javax.persistence.EntityManager;
import org.apache.log4j.Logger;

/**
 *
 * @author vasil
 */
public class TSprFirmDAO implements daoInterface<TSprFirm, Long> {

    private final Logger log = Logger.getLogger(getClass().getName());
    private final EntityManager em;

    public TSprFirmDAO(EntityManager em) {
        this.em = em;
    }

    @Override
    public EntityManager getEM() {
        return em;
    }

}
