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
public class NetworkScanResultTest {
    private  NetworkScanResult networkScanResult;
    public NetworkScanResultTest() {
        networkScanResult = new NetworkScanResult(false, false, false);
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
     * Test of isFreakSSL method, of class NetworkScanResult.
     */
    @Test
    public void testIsFreakSSL() {
        System.out.println("isFreakSSL");

        boolean expResult = false;
        boolean result = networkScanResult.isFreakSSL();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of isHeartBleedSSL method, of class NetworkScanResult.
     */
    @Test
    public void testIsHeartBleedSSL() {
        System.out.println("isHeartBleedSSL");

        boolean expResult = false;
        boolean result = networkScanResult.isHeartBleedSSL();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of isPoodleSSL method, of class NetworkScanResult.
     */
    @Test
    public void testIsPoodleSSL() {
        System.out.println("isPoodleSSL");

        boolean expResult = false;
        boolean result = networkScanResult.isPoodleSSL();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of setFreakSSL method, of class NetworkScanResult.
     */
    @Test
    public void testSetFreakSSL() {
        System.out.println("setFreakSSL");
        boolean freakSSL = false;
        NetworkScanResult instance = new NetworkScanResult();
        instance.setFreakSSL(freakSSL);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of setHeartBleedSSL method, of class NetworkScanResult.
     */
    @Test
    public void testSetHeartBleedSSL() {
        System.out.println("setHeartBleedSSL");
        boolean heartBleedSSL = false;
        NetworkScanResult instance = new NetworkScanResult();
        instance.setHeartBleedSSL(heartBleedSSL);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of setPoodleSSL method, of class NetworkScanResult.
     */
    @Test
    public void testSetPoodleSSL() {
        System.out.println("setPoodleSSL");
        boolean poodleSSL = false;
        NetworkScanResult instance = new NetworkScanResult();
        instance.setPoodleSSL(poodleSSL);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    
}
