/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qu.senior.entities;

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
public class NmapHostTest {
    
    public NmapHostTest() {
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
     * Test of getIp method, of class NmapHost.
     */
    @Test
    public void testGetIp() {
        System.out.println("getIp");
        NmapHost instance = new NmapHost();
        String expResult = null;
        String result = instance.getIp();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of setIp method, of class NmapHost.
     */
    @Test
    public void testSetIp() {
        System.out.println("setIp");
        String ip = null;
        NmapHost instance = new NmapHost();
        instance.setIp(ip);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of isOpen method, of class NmapHost.
     */
    @Test
    public void testIsOpen() {
        System.out.println("isOpen");
        NmapHost instance = new NmapHost();
        boolean expResult = false;
        boolean result = instance.isOpen();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of setOpen method, of class NmapHost.
     */
    @Test
    public void testSetOpen() {
        System.out.println("setOpen");
        boolean open = false;
        NmapHost instance = new NmapHost();
        instance.setOpen(open);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of getReport method, of class NmapHost.
     */
    @Test
    public void testGetReport() {
        System.out.println("getReport");
        NmapHost instance = new NmapHost();
        String expResult = null;
        String result = instance.getReport();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of setReport method, of class NmapHost.
     */
    @Test
    public void testSetReport() {
        System.out.println("setReport");
        String report = "";
        NmapHost instance = new NmapHost();
        instance.setReport(report);
        // TODO review the generated test code and remove the default call to fail.
        
    }


    
}
