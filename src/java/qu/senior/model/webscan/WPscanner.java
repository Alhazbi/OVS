/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qu.senior.model.webscan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
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

/**
 *
 * @author abdulla
 */
public class WPscanner extends Scanner {

    ArrayList<String> wp = new ArrayList<>();
    common c = new common();

    public void getWordpress(/*String ip, String path*/ArrayList<String> urls) {

        BufferedReader br = null;

        /* String sCurrentLine;

         File fileToRead = new File(path + ip + ".txt");
         PrintWriter out = new PrintWriter(path + "wordpress-sites.txt");
         br = new BufferedReader(new FileReader(fileToRead));
         int i = 0;
         while ((sCurrentLine = br.readLine()) != null) { */
        int i = 0;
        for (String singleUrl : urls) {
            try {
                for (String url : c.generateSingleUrlWithFolders(singleUrl)) {

                    String wpUrl = "http://" + url + "/wp-content";
                    //Document content = Jsoup.connect(""+sCurrentLine+"components").userAgent(userAgent).timeout(timeout).get();
                    Connection con = Jsoup.connect(wpUrl).userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/535.21 (KHTML, like Gecko) Chrome/19.0.1042.0 Safari/535.21").timeout(10000);
                    Connection.Response resp = con.execute();
                    Document doc = null;

                   //  System.out.println(resp.url().getHost());
                    if (resp.statusCode() == 200 && wpUrl.contains(resp.url().getHost()) && !resp.url().toString().contains("?") && !resp.url().toString().contains("www.qu.edu.qa") && !resp.url().toString().contains("86.36.67.254")) {

                        wp.add(url);

                    }
                }

            } catch (Exception e) {
                // continue;
            }
        }

        //  out.println(wp);
        for (String url : c.cleanDublication(wp)) {
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
    public void scan(String path, WebScanResult webScanResult) {
        try {
            /*  BufferedReader br = null;
             String sCurrentLine;

             File fileToRead = new File(path + "wordpress-sites.txt");

             try {
             br = new BufferedReader(new FileReader(fileToRead));
             } catch (FileNotFoundException ex) {
             Logger.getLogger(WPscanner.class.getName()).log(Level.SEVERE, null, ex);
             } */

            try {
                for (String wpurl : wp) {

                    if (!wpurl.equals("")) {
                        List<String> exploits = new ArrayList<String>();
                        String command = "cd ~/Desktop/Senior-website-scan/wpscan ; echo '123456789' | sudo -S ruby wpscan.rb --url "
                                + wpurl + "";
                        String url = wpurl.replaceAll("(http://|http://www\\.|www\\.|/)", "");
                        String output = c.executeCommand(command);
                        PrintWriter out = new PrintWriter(path + wpurl.replaceAll("(http://|http://www\\.|www\\.|/)", "") + "-wpcan.txt");
                        out.println(output);
                        String[] lines = output.split("\n");
                        for (int i = 0; i < lines.length; i++) {
                            String temp = lines[i];
                            if (temp.contains("[!]") && temp.contains("Title:")) {
                                String fullText = temp.substring(temp.indexOf("Title: ") + "Title:".length() + 1);

                                for (i++; lines[i].contains("Reference:"); i++) {
                                    System.out.println("In for ");
                                    String regex = "\\(?\\b(http://|www[.])[-A-Za-z0-9+&amp;@#/%?=~_()|!:,.;]*[-A-Za-z0-9+&amp;@#/%=~_()|]";
                                    Pattern p = Pattern.compile(regex);
                                    Matcher m = p.matcher(lines[i]);
                                    while (m.find()) {
                                        String urlStr = m.group();
                                        if (urlStr.startsWith("(") && urlStr.endsWith(")")) {
                                            urlStr = urlStr.substring(1, urlStr.length() - 1);
                                        }
                                        String html = String.format("<a target=\"_blank\" href=\"%s\"> Read more </a>", urlStr);
                                        fullText = fullText + html;
                                    }
                                }

                                exploits.add(fullText);
                                i--;
                            }
                        }

                        System.out.println("" + wpurl + " Scanned\nfile saved at:" + path + wpurl.replaceAll("(http://|http://www\\.|www\\.|/)", "") + "-wpscan.txt\n");
                        out.close();
                        webScanResult.getWebsites().add(new Website(url, exploits));

                    }
                }
            } catch (Exception ex) {
            }

        } catch (NullPointerException ex) {
            Logger.getLogger(WebScan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isRedirect(String url) {

        try {
            URLConnection con = new URL(url).openConnection();
            // System.out.println("orignal url: " + con.getURL());
            con.connect();
            String x = con.getURL().getHost();
            InputStream is = con.getInputStream();
            String y = con.getURL().getHost();
            is.close();
            if (x.equals(y)) {
                return true;
            }
        } catch (IOException ex) {
            Logger.getLogger(WPscanner.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public String getName() {
        return "Wordpress Websites Scanner";
    }

}
