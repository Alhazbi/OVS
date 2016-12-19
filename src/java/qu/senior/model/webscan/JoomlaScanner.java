/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qu.senior.model.webscan;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import qu.senior.entities.WebScanResult;
import qu.senior.entities.Website;
import qu.senior.model.common.common;

public class JoomlaScanner extends Scanner {

    ArrayList<String> jo = new ArrayList<>();
    common c = new common();

 
   public void getJoomla(/*String ip, String path*/ArrayList<String> urls) {

        BufferedReader br = null;

        /* String sCurrentLine;

         File fileToRead = new File(path + ip + ".txt");
         PrintWriter out = new PrintWriter(path + "wordpress-sites.txt");
         br = new BufferedReader(new FileReader(fileToRead));
         int i = 0;
         while ((sCurrentLine = br.readLine()) != null) { */
        int i = 0;
        for (String singleUrl :urls) {
            try{
            for(String url :  c.generateSingleUrlWithFolders(singleUrl)){
                
                    String wpUrl = "http://" + url + "/components";
                    //Document content = Jsoup.connect(""+sCurrentLine+"components").userAgent(userAgent).timeout(timeout).get();
                    Connection con = Jsoup.connect(wpUrl).userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/535.21 (KHTML, like Gecko) Chrome/19.0.1042.0 Safari/535.21").timeout(10000);
                    Connection.Response resp = con.execute();
                    Document doc = null;

               
                   //  System.out.println(resp.url().getHost());
                  
                    if (resp.statusCode() == 200 && wpUrl.contains(resp.url().getHost()) && !resp.url().toString().contains("?") && !resp.url().toString().contains("www.qu.edu.qa") && !resp.url().toString().contains("86.36.67.254")) {

                        jo.add(url);

                    }
                }

            } catch (Exception e) {
                // continue;
            }
        }

        //  out.println(wp);
        for (String url : c.cleanDublication(jo)) {
            System.out.println(url);
        }
        //    out.close();

        try {
            if (br != null) {
                br.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public String getName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void scan(String path ,WebScanResult webScanResult) {
        try {
            /*
             BufferedReader br = null;
             String sCurrentLine;

             File fileToRead = new File(path + "joomla-sites.txt");

             br = new BufferedReader(new FileReader(fileToRead));

             while ((sCurrentLine = br.readLine()) != null) {
             */
            for (String jourl : jo) {

                if (!jourl.equals("")) {
                    String command = "cd ~/Desktop/Senior-website-scan/joomscan ; perl joomscan.pl -u "
                            + jourl + "";
                    List<String> exploits = new ArrayList<String>();
                    String output = c.executeCommand(command);
                    String url = jourl.replaceAll("(http://|http://www\\.|www\\.|/)", "");
                    PrintWriter out = new PrintWriter(path + jourl.replaceAll("(http://|http://www\\.|www\\.|/)", "") + "-joomscan.txt");
                    out.println(output);
                    
                    String[] a=output.split("(?m)(#\\s(\\d)+)$");
                    ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(a));
                    arrayList.remove(0);
                    
                    for (String temp:arrayList){
                        if (temp.contains("Vulnerable? N/A") || temp.contains("Vulnerable? Yes"))
                        {
                           String ex=temp.split("\n")[1];
                           String regex = "\\(?\\b(http://|www[.])[-A-Za-z0-9+&amp;@#/%?=~_()|!:,.;]*[-A-Za-z0-9+&amp;@#/%=~_()|]";
Pattern p = Pattern.compile(regex);
Matcher m = p.matcher(temp);
while(m.find()) {
String urlStr = m.group();
if (urlStr.startsWith("(") && urlStr.endsWith(")"))
{
urlStr = urlStr.substring(1, urlStr.length() - 1);
}
String html = String.format("<a target=\"_blank\" href=\"%s\"> Read more </a>", urlStr);
ex = ex + html;
}
                         exploits.add(ex);
                         
                        }
                    }
                    System.out.println("" + jourl + " Scanned\nfile saved at:" + path + jourl.replaceAll("(http://|http://www\\.|www\\.|/)", "") + "-joomscan.txt\n");
                    out.close();
                    webScanResult.getWebsites().add(new Website(url,exploits));
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(WebScan.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

}
