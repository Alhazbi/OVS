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


public class ScanTest {
    
    public ScanTest() {
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
     * Test of getId method, of class Scan.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Scan instance = new Scan();
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of getUserId method, of class Scan.
     */
    @Test
    public void testGetUserId() {
        System.out.println("getUserId");
        Scan instance = new Scan();
        int expResult = 0;
        int result = instance.getUserId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of getUser method, of class Scan.
     */
    @Test
    public void testGetUser() {
        System.out.println("getUser");
        Scan instance = new Scan();
        User expResult = null;
        User result = instance.getUser();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }




    /**
     * Test of isIsNetworkScam method, of class Scan.
     */
    @Test
    public void testIsIsNetworkScam() {
        System.out.println("isIsNetworkScam");
        Scan instance = new Scan();
        boolean expResult = false;
        boolean result = instance.isIsNetworkScam();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of isIsWebScan method, of class Scan.
     */
    @Test
    public void testIsIsWebScan() {
        System.out.println("isIsWebScan");
        Scan instance = new Scan();
        boolean expResult = false;
        boolean result = instance.isIsWebScan();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }



    /**
     * Test of setId method, of class Scan.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        int id = 0;
        Scan instance = new Scan();
        instance.setId(id);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of setUserId method, of class Scan.
     */
    @Test
    public void testSetUserId() {
        System.out.println("setUserId");
        int userId = 0;
        Scan instance = new Scan();
        instance.setUserId(userId);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of setUser method, of class Scan.
     */
    @Test
    public void testSetUser() {
        System.out.println("setUser");
        User user = null;
        Scan instance = new Scan();
        instance.setUser(user);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of setIp method, of class Scan.
     */
    @Test
    public void testSetIp() {
        System.out.println("setIp");
        String ip = "";
        Scan instance = new Scan();
        instance.setIp(ip);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of setScanDate method, of class Scan.
     */
    @Test
    public void testSetScanDate() {
        System.out.println("setScanDate");
        Date scanDate = null;
        Scan instance = new Scan();
        instance.setScanDate(scanDate);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of setIsNetworkScam method, of class Scan.
     */
    @Test
    public void testSetIsNetworkScam() {
        System.out.println("setIsNetworkScam");
        boolean isNetworkScam = false;
        Scan instance = new Scan();
        instance.setIsNetworkScam(isNetworkScam);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of setIsWebScan method, of class Scan.
     */
    @Test
    public void testSetIsWebScan() {
        System.out.println("setIsWebScan");
        boolean isWebScan = false;
        Scan instance = new Scan();
        instance.setIsWebScan(isWebScan);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of setNetworkScanResult method, of class Scan.
     */
    @Test
    public void testSetNetworkScanResult() {
        System.out.println("setNetworkScanResult");
        NetworkScanResult networkScanResult = null;
        Scan instance = new Scan();
        instance.setNetworkScanResult(networkScanResult);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of setWebScanResult method, of class Scan.
     */
    @Test
    public void testSetWebScanResult() {
        System.out.println("setWebScanResult");
        WebScanResult webScanResult = null;
        Scan instance = new Scan();
        instance.setWebScanResult(webScanResult);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of getIp method, of class Scan.
     */
    @Test
    public void testGetIp() {
        System.out.println("getIp");
        Scan instance = new Scan();
        String expResult = null;
        String result = instance.getIp();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    
}
