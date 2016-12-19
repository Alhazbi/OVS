/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qu.senior.entities;

import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author abdulla
 */
public class HistoryTest {

    private History history;

    public HistoryTest() {
        
history = new History(Long.getLong("500000000"), "192.168.1.1", new Date(1220227200L * 1000));

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
     * Test of getIp method, of class History.
     */
    @Test
    public void testGetIp() {
        System.out.println("getIp");

        String expResult = "192.168.1.1";
        String result = history.getIp();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getDate method, of class History.
     */
    @Test
    public void testGetDate() {
        System.out.println("getDate");

        Date expResult = new Date(1220227200L * 1000);
        Date result = history.getDate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getScanId method, of class History.
     */
    @Test
    public void testGetScanId() {
        System.out.println("getScanId");

        Long expResult = Long.getLong("500000000");
        Long result = history.getScanId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setIp method, of class History.
     */
    @Test
    public void testSetIp() {
        System.out.println("setIp");
        String ip = "";
        History instance = new History();
        instance.setIp(ip);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of setScanId method, of class History.
     */
    @Test
    public void testSetScanId() {
        System.out.println("setScanId");
        Long scanId = null;
        History instance = new History();
        instance.setScanId(scanId);
        // TODO review the generated test code and remove the default call to fail.
       
    }

    /**
     * Test of setDate method, of class History.
     */
    @Test
    public void testSetDate() {
        System.out.println("setDate");
        Date date = null;
        History instance = new History();
        instance.setDate(date);
        // TODO review the generated test code and remove the default call to fail.
       
    }

}
