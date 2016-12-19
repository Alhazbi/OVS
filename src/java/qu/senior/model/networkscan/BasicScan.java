/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qu.senior.model.networkscan;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.Stateless;
import qu.senior.entities.NmapHost;
@Stateless
public class BasicScan {

    public List<NmapHost> getNmapHost(String ip) throws InterruptedException {

       // String ip = "10.20.150.*";
        //String ip = "10.20.150.0/24"; // intire supnet
        //String ip = "10.20.150.*"; // range
        //String ip = "10.20.150.76-200"; // range
        //String ip = "10.20.150.1,2,3,4"; // multi ips
        List<NmapHost> hosts = new ArrayList<NmapHost>();
        
        Nmap n = new Nmap();
        String fullReport = n.nmapScan(ip);
      //  System.out.println(fullReport);
       String hostIp;
       String report;
        
   // String paraRegex="Nmap scan report for.*\nHost is up(.|\n)*?Network Distance: \\d hops?";
      String paraRegex="Nmap scan report for.*\nHost is up(.|\n)*?Running[ (A-Z|a-z)\\( \\)]*: [a-z|A-Z|0-9|\\. \\) \\( \\%]*\\n?";
    String ipRegex = "\\b\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\b";
    String ipRegexDown = "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3} \\[host down\\]";
    System.out.println("before");
    Pattern paraPatt = Pattern.compile(paraRegex);    
    System.out.println("after 1");
    Pattern ipPatt = Pattern.compile(ipRegex);
    System.out.println("after 2");
    Pattern ipPattDown = Pattern.compile(ipRegexDown);
    System.out.println("after 3");
    Matcher ipMatcher;
    Matcher ipDownMatcher; 
    Matcher paraMat = paraPatt.matcher(fullReport); 
    
    System.out.println("after 4");
    //find report for opened hosts
    while(paraMat.find()){
        report=paraMat.group();
    	System.out.println("Report:------------------------------\n"+report);

    	//System.out.println("after report");
        
         ipMatcher= ipPatt.matcher(report);
         if(ipMatcher.find()){
         hostIp = ipMatcher.group();
         report=report.replaceAll("\n", "</br>");
         hosts.add(new NmapHost(hostIp,true,report));
         }
    	}
    
    ipDownMatcher = ipPattDown.matcher(fullReport);
    
        while(ipDownMatcher.find()){
        ipMatcher= ipPatt.matcher(ipDownMatcher.group());
        if(ipMatcher.find())
         hosts.add(new NmapHost(ipMatcher.group(), false, ""));
        }
        return hosts;
    }
}
