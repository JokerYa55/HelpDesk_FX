/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import beans.sprFirm;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author vasil
 */
public class sprFirmDAOTest {

    private final Logger log = Logger.getLogger(sprFirmDAOTest.class);
    private DataSource dataSource = new org.springframework.jdbc.datasource.DriverManagerDataSource("jdbc:postgresql://192.168.1.250:5432/service_desk", "vasil", "123");

    public sprFirmDAOTest() {
    }

    @BeforeClass
    public static void setUpClass() {        
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getItemById method, of class sprFirmDAO.
     */
    @Test
    public void testGetItemById() {
        log.info("getItemById");
        Long id = null;
//        sprFirmDAO instance = new sprFirmDAO();
//        sprFirm expResult = null;
//        sprFirm result = instance.getItemById(id);
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getItemList method, of class sprFirmDAO.
     */
    @Test
    public void testGetItemList_0args() {
        log.info("getItemList");
        sprFirmDAO instance = new sprFirmDAO(dataSource);
//        List<sprFirm> expResult = null;
//        List<sprFirm> result = instance.getItemList();
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getItemList method, of class sprFirmDAO.
     */
    @Test
    public void testGetItemList_Long_Long() {
        log.info("getItemList");
        Long startIndex = null;
        Long itemCount = null;
        sprFirmDAO instance = new sprFirmDAO(dataSource);
//        List<sprFirm> expResult = null;
//        List<sprFirm> result = instance.getItemList(startIndex, itemCount);
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of addItem method, of class sprFirmDAO.
     */
    @Test
    public void testAddItem() {
        log.info("addItem");
        sprFirm Item = null;
        sprFirmDAO instance = new sprFirmDAO(dataSource);
//        long expResult = 0L;
//        long result = instance.addItem(Item);
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of deleteItem method, of class sprFirmDAO.
     */
    @Test
    public void testDeleteItem() {
        log.info("deleteItem");
        sprFirm Item = null;
        sprFirmDAO instance = new sprFirmDAO(dataSource);
        boolean expResult = false;
//        boolean result = instance.deleteItem(Item);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of updateItem method, of class sprFirmDAO.
     */
    @Test
    public void testUpdateItem() {
        log.info("updateItem");
        sprFirm Item = null;
        sprFirmDAO instance = new sprFirmDAO(dataSource);
        boolean expResult = false;
//        boolean result = instance.updateItem(Item);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

}
