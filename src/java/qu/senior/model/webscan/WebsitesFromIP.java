/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qu.senior.model.webscan;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class WebsitesFromIP {

    List<String> containedUrls = new ArrayList<String>();

    private final String USER_AGENT = "Mozilla/5.0 (Windows; U; Windows NT 5.1; fr; rv:1.9.1) Gecko/20090624 Firefox/3.5";
    private static final Pattern urlPattern = Pattern.compile("[-A-Za-z0-9+&amp;@#/%?=~_()|!:,.;]*[.][-A-Za-z0-9+&amp;@#/%?=~_()|!:,.;]*[-A-Za-z0-9+&amp;@#/%=~_()|]",
            Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);

    // HTTP POST request
    public void getWebsiteList(String ip, String path) throws Exception {

        URL url = new URL("http://domains.yougetsignal.com/domains.php");
        //URL url = new URL("http://www.ipfingerprints.com/scripts/getReverseIP.php");
        Map<String, Object> params = new LinkedHashMap<>();
        params.put("remoteAddress", ip);
        //params.put("remoteHost", ip);
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

        Matcher matcher = urlPattern.matcher(out);

        while (matcher.find()) {

            containedUrls.add(out.substring(matcher.start(0), matcher.end(0)));
        }
// for the second url 
     /*   for (int i = 0; i < containedUrls.size(); i++) {

            if (!containedUrls.get(i).contains(">")) {
                containedUrls.remove(i);
            }

            containedUrls.set(i, containedUrls.get(i).replace(">", "http://"));
            containedUrls.set(i, containedUrls.get(i).replace("www.", ""));
        }

 // end */
        
        
        PrintWriter out2 = new PrintWriter(path + ip + ".txt");
        int i = 0;
        for (String elem : containedUrls) {
            out2.println("http://" + elem + "/");

            i++;
            //System.out.println("http://" + elem + "/");
        }
        out2.close();

    }

    public List<String> getLinksFromIP() {

        ArrayList<String> linksFromIP = new ArrayList<>();

        for (String elem : containedUrls) {
            linksFromIP.add("http://" + elem + "/");
        }

        return linksFromIP;

    }

}
