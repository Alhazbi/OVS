/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qu.senior.model.networkscan;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import qu.senior.model.common.common;

public class Nmap {

    common c = new common();

    public String nmapScan(String ip) throws InterruptedException {

        String publicIPRegex = "^(([0,1]?[0-9]{1,2}|2[0-4][0-9]|25[0-5])\\.){3}([0,1]?[0-9]{1,2}|2[0-4][0-9]|25[0-5])$";
        Pattern publicPatt = Pattern.compile(publicIPRegex);
        Matcher publicMat = publicPatt.matcher(ip);

        if (publicMat.find()) {

            
            String out = c.executeCommand("echo '123456789' | sudo -S nmap -F -O --osscan-guess " + ip + " ");
            System.out.println(out);
            return out;

        } else {

            String out = c.executeCommand("echo '123456789' | sudo -S nmap -v -O --osscan-guess " + ip + " ");
            return out;
        }
    }
}
