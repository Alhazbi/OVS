/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qu.senior.entities;

import java.util.List;
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
public class WebScanResultTest {
    
    public WebScanResultTest() {
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
     * Test of setWebsites method, of class WebScanResult.
     */
    @Test
    public void testSetWebsites() {
        System.out.println("setWebsites");
        List<Website> websites = null;
        WebScanResult instance = new WebScanResult();
        instance.setWebsites(websites);
        // TODO review the generated test code and remove the default call to fail.
        
    }
    
}
