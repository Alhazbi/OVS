/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qu.senior.model.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class common {

    public String getHtml(String URL) {

        InputStream is = null;
        try {
            String result = "";
            String line;

            URL url = new URL(URL);
            is = url.openStream();  // throws an IOException
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            while ((line = br.readLine()) != null) {
                result += line;
            }
            return result;
        } catch (IOException ioe) {
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException ioe) {
                // nothing to see here
            }
        }
        return "";
    }

    public String executeCommand(String command) throws InterruptedException{

        String output = "";

        String[] cmd = {"/bin/sh", "-c", command};

        Process p;
        try {
            p = Runtime.getRuntime().exec(cmd);
            p.waitFor();

            BufferedReader reader
                    = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line ;
            while ((line = reader.readLine()) != null) {
                output=output+line + "\n";
            }

        } catch (IOException | InterruptedException e) {
            System.err.println("Cralwling: "+e.getMessage());
            e.printStackTrace();
        }

        return output;

    }

    public ArrayList<String> getFolders(String url) {
        ArrayList<String> folders = new ArrayList<String>();
        Pattern pattern = Pattern.compile("\\/[-a-zA-Z0-9+&@/#%?=~_|!:,.;]+\\/");
        Matcher matcher1 = pattern.matcher(url);
        if(matcher1.find())
        for (String elem : matcher1.group().split("\\/")) {
            if (!elem.isEmpty()) {
                folders.add("/" + elem + "/");
            }
        }
        return folders;
    }

    public List<String> cleanDublication(List<String> linkList) {

        List<String> al = linkList;
// add elements to al, including duplicates
        Set<String> hs = new HashSet<>();
        hs.addAll(al);
        al.clear();
        al.addAll(hs);

        return al;

    }
    public ArrayList<String> generateSingleUrlWithFolders(String url) {
        ArrayList<String> links = new ArrayList<>();

        links.add(getHost(url));
        for (String elem : getFolders(url)) {

            links.add(url + elem);
        }

        return links;
    }
    
    public String getHost(String url) {
        String name = null ;
        Pattern pattern = Pattern.compile("[-a-zA-Z0-9+&@#%?=~_|!:,.;]+\\.[-a-zA-Z0-9+&@#%?=~_|!:,.;]+\\/");
        Matcher matcher1 = pattern.matcher(url);
        if(matcher1.find())
        name= matcher1.group();
        return name;
    }
}
