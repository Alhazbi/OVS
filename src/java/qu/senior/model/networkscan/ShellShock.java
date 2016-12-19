/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qu.senior.model.networkscan;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;


public class ShellShock extends Scanner {
    /*
     http://shellshock.brandonpotter.com/
     __VIEWSTATE=P9yWxnPe40dkGbxNUDUr0jjRsdCzxjZzPNI1JEwOOouRzZOo6Pza1xqJCb5r9777nzCzJfIr7vNxHiGMNrpuIFMnsCvx5ozNOCeffhkTsjK1mn0ADrYRjqJ1w8mHseRH%2Fabg1RcxZ1%2B%2FTFNGl67hls7Ej0mP2jCh1MlJDFhd3RP39YASQRA0tXXhaT93buybSSK%2FMb%2FVQOzJ50eLJnnfdllfZnwaA9C6NwX%2Bm3udOG3HpwFoM7URXLj2G8q9HlC3&__EVENTVALIDATION=gYKnSY7OxGsAIq1JxBMkDZUemu%2F05Z%2FuapaNbUogJfdxUgEHgsA1A4WkP1AIGXCoVUI%2Fm95MBgAAEWJabOL6bXFMyKQMTEOO%2BKUjITBP0JJQ2RhKGJQ31biaoVUH%2BV6goPcHoJSeISbWOP6pi2TmBjGHJqi%2FSInaBIr2rnJBu5IYB7RvZDGphvcju0mP%2BmbT&ctl00%24MainContent%24txtHost=184.174.140.162&ctl00%24MainContent%24btnBeginTest=Begin+Test&ctl00%24MainContent%24txtScriptUrl=
    
     */

    @Override
    public boolean checkExploit(String ip) throws Exception {
        
        URL url = new URL("http://shellshock.brandonpotter.com/");
        Map<String, Object> params = new LinkedHashMap<>();
        params.put("__VIEWSTATE", "P9yWxnPe40dkGbxNUDUr0jjRsdCzxjZzPNI1JEwOOouRzZOo6Pza1xqJCb5r9777nzCzJfIr7vNxHiGMNrpuIFMnsCvx5ozNOCeffhkTsjK1mn0ADrYRjqJ1w8mHseRH/abg1RcxZ1+/TFNGl67hls7Ej0mP2jCh1MlJDFhd3RP39YASQRA0tXXhaT93buybSSK/Mb/VQOzJ50eLJnnfdllfZnwaA9C6NwX+m3udOG3HpwFoM7URXLj2G8q9HlC3");
        params.put("__EVENTVALIDATION", "gYKnSY7OxGsAIq1JxBMkDZUemu/05Z/uapaNbUogJfdxUgEHgsA1A4WkP1AIGXCoVUI/m95MBgAAEWJabOL6bXFMyKQMTEOO+KUjITBP0JJQ2RhKGJQ31biaoVUH+V6goPcHoJSeISbWOP6pi2TmBjGHJqi/SInaBIr2rnJBu5IYB7RvZDGphvcju0mP+mbT");
        params.put("ctl00$MainContent$txtHost", ip);
        params.put("ctl00$MainContent$btnBeginTest", "Begin+Test");
        params.put("ctl00$MainContent$txtScriptUrl", "");

        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String, Object> param : params.entrySet()) {
            if (postData.length() != 0) {
                postData.append('&');
            }
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
        }
        byte[] postDataBytes = postData.toString().getBytes("UTF-8");

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        conn.setDoOutput(true);
        conn.getOutputStream().write(postDataBytes);
        Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        String out = "";
        for (int c; (c = in.read()) >= 0;) {

            out += (char) c;

        };
        while (!out.contains("Test Finished")); // wait until its finish

        if (out.contains("No Vulnerabilities Found")) {
            return false;
        }

        return true;
    }

    @Override
    public String getName() {
      return "ShellShock Exploit";
    }

    @Override
    public int getPortNeeded() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
