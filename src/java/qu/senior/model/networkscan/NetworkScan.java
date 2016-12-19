/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qu.senior.model.networkscan;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import qu.senior.entities.NetworkScanResult;



@Stateless
public class NetworkScan {


    public NetworkScanResult networkscan(String ip) throws Exception {
        
        System.out.println(ip);
        // FreakExploit ----- start---------
        FreakExploitScanner f = new FreakExploitScanner();
        //output 
        
        NetworkScanResult networkScanResult = new NetworkScanResult();
        try {
            if (f.checkExploit(ip)) {

                System.out.println(f.getName() + ": Vulnerable");
                networkScanResult.setFreakSSL(true);

            } else {
                networkScanResult.setFreakSSL(false);
                System.out.println(f.getName() + ": Not vulnerable");
            }
        } catch (Exception ex) {
            Logger.getLogger(NetworkScan.class.getName()).log(Level.SEVERE, null, ex);
        }

         // FreakExploit ----- end ---------
        // Heartbleed ----- start---------
        HeartbleedScanner h = new HeartbleedScanner();
        try {
            if (h.checkExploit(ip)) {
      networkScanResult.setHeartBleedSSL(true);
                System.out.println(h.getName() + ": Vulnerable");

            } else {
                      networkScanResult.setHeartBleedSSL(false);
                System.out.println(h.getName() + ": Not vulnerable");
            }
        } catch (Exception ex) {
            Logger.getLogger(NetworkScan.class.getName()).log(Level.SEVERE, null, ex);
        }

         // Heartbleed ----- end ---------
        // Poodle ----- start---------
        PoodleExploitScanner p = new PoodleExploitScanner();
        try {
            if (p.checkExploit(ip)) {
                networkScanResult.setPoodleSSL(true);
                System.out.println(p.getName() + ": Vulnerable");

            } else {
                networkScanResult.setPoodleSSL(false);
                System.out.println(p.getName() + ": Not vulnerable");
            }
        } catch (Exception ex) {
            Logger.getLogger(NetworkScan.class.getName()).log(Level.SEVERE, null, ex);
        }

       return networkScanResult;
    }

}
