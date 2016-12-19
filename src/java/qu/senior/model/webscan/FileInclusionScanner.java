/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qu.senior.model.webscan;

import java.util.ArrayList;
import qu.senior.entities.WebScanResult;


public class FileInclusionScanner  extends Scanner{

    ArrayList<String> links = new ArrayList<>();

    public ArrayList<String> getLinks(ArrayList<String> urls) {

        for (String url : urls) {
            if (url.contains("?")) {
                links.add(url);
            }

        }
return links;
    }

    @Override
    public void scan(String ip , WebScanResult webScanResult) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getName() {
         return "LFI & RFI";
    }

}
