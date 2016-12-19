/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qu.senior.model.webscan;

import java.util.ArrayList;
import qu.senior.entities.WebScanResult;
import qu.senior.model.common.common;

public class NewWebCrawler extends Scanner {
//wget --spider -r -l 1 --delete-after http://www.almotamar.net 2>&1 | grep "Saving to:"
    ArrayList<String> urls = new ArrayList<>();
common c = new common();
    public ArrayList<String> crawler(int level, ArrayList<String> list) throws InterruptedException {
        for (String url : list) {
       
            if (!url.equals("")) {
                String command = "wget --spider -r -l " + level + " --delete-after " + url + " 2>&1 | grep \"Saving to:\"";
                String output = c.executeCommand(command);
                for (String elem : output.split("[a-zA-Z ]+\\:[ ]\\‘")) {
                    if (!elem.equals("")) { 
                        System.out.println("Element:"+elem);
                        urls.add(elem.replace("’", "").replace("\n", ""));
                    }
                }
            }
        }
        return urls;

    }

    @Override
    public void scan(String ip,  WebScanResult webScanResult) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
