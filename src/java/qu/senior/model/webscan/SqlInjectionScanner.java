/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qu.senior.model.webscan;

import java.util.ArrayList;
import qu.senior.entities.WebScanResult;
import qu.senior.model.common.common;


public class SqlInjectionScanner extends Scanner {

    common c = new common();

    @Override
    public void scan(String url, WebScanResult webScanResult) {

    }

    public ArrayList<String> multiSQLICheck(ArrayList<String> urls) {
        ArrayList<String> links = new ArrayList<>();
        for(String elem : urls){
        if(checkSqli(elem)){
            links.add(elem);
        }
        
        }
        return links;
    }

    public boolean checkSqli(String url) {
        url = url + "'";
        String html = c.getHtml(url);
        String[] errors = {"fatal error", "$GET", "syntax error", "supplied argument is not a valid mysql", "select * from", "mysql_fetch​_row()", "sql syntax", "incorrect syntax", "sql error", "mysql_fetch_array()", "execute query", "mysql_fetch_object()", "mysql_num_rows()", "mysql_fetch_assoc()"};

        for (String elem : errors) {
            if (html.toLowerCase().contains(elem)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getName() {
        return "SQl Injection";
    }

}
