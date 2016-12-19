/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qu.senior.model.webscan;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class test {

    public ArrayList<String> getFolders() {
        ArrayList<String> folders = new ArrayList<String>();
        try {
            URL aURL = new URL("");

            Pattern pattern = Pattern.compile("\\/[-a-zA-Z0-9+&@#/%?=~_|!:,.;]+\\/");
            Matcher matcher1 = pattern.matcher(aURL.getPath());

            for (String elem : matcher1.group().split("\\/")) {
                if (!elem.isEmpty()) {
                    folders.add("/" + elem + "/");
                }
            }

        } catch (MalformedURLException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
        return folders;
    }
}
