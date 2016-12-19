/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qu.test;

import com.google.gson.Gson;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import qu.senior.entities.Scan;
import qu.senior.entities.Website;
import qu.senior.model.networkscan.NetworkScan;


public class TestJson {
    
    
    public static void main(String x[]) throws Exception{
        
    //137.254.56.26
    //184.173.140.162
        
        
    String ip="184.173.140.162";
    Scan scan = new Scan();
    scan.setId(4000);
    scan.setUserId(1);
    scan.setIp(ip);
    scan.setIsNetworkScam(true);
    scan.setIsWebScan(true);
    
    NetworkScan networkScan=new NetworkScan();
    scan.setNetworkScanResult(networkScan.networkscan(ip));
    
    File file = new File("C:\\Users\\Mohd\\java-workspace\\SeniorTest\\jomtest.txt");
    String output="";
    try {

        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()) {
         output=output+sc.nextLine()+"\n";
        }
        sc.close();
    } 
      
    catch (FileNotFoundException e) {
        e.printStackTrace();
    }
    List<String> exploits = new ArrayList<String>();
    String[] a=output.split("(?m)(#\\s(\\d)+)$");
    ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(a));
    arrayList.remove(0);
    for (String temp:arrayList){
                        if (temp.contains("Vulnerable? N/A") || temp.contains("Vulnerable? Yes"))
                        {
                         exploits.add(temp.split("\n")[1]);
                        }
                    }
    
    
    scan.getWebScanResult().getWebsites().add(new Website("test jomla", exploits));
  
 
    
    
    
    
    
    
    
    
    file = new File("C:\\Users\\Mohd\\java-workspace\\SeniorTest\\wp.txt");
        output="";
    try {

         Scanner sc = new Scanner(file);

        while (sc.hasNextLine()) {
         output=output+sc.nextLine()+"\n";
        }
        sc.close();
    } 
        catch (FileNotFoundException e) {
        e.printStackTrace();
    }
    
    exploits = new ArrayList<String>();
    
    String[] lines=output.split("\n");
                        for(String temp:lines){
                          if(temp.contains("[!]") && temp.contains("Title:"))
                             exploits.add(temp.substring(temp.indexOf("Title: ")+"Title:".length()+1));
   }
                       
                        scan.getWebScanResult().getWebsites().add(new Website("test wp", exploits));
    
    
                Gson gson = new Gson();
            String json = gson.toJson(scan);
    
    
            System.out.println(json);
       }
    
    
    
    
    
}
