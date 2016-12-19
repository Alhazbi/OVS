package qu.senior.model.webscan;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import qu.senior.model.common.common;

public class WebCrawler {
common c = new common();
    List<String> links = new ArrayList<>();
    boolean exist = false;
    String regex = "\\<(a href)\\=\\\"(http)\\:\\/\\/[-a-zA-Z0-9+&@#/%?=~_|!:,.;]+";
    String regex2 = "(http|https)\\:\\/\\/[-a-zA-Z0-9+&@#/%?=~_|!:,.;]+";

    public WebCrawler() {
    }

    


    public void crawl(String URL) {

        //System.out.println( links.size() );  
        if (!links.isEmpty()) {
            for (String link : links) {
                if (link.equals(URL)) {
                    exist = true;
                }
            }
        }
        links.add(URL);
        if (!exist) {
            Document doc = null;
            try {

                doc = Jsoup.connect(URL).timeout(10 * 1000).userAgent("Mozilla/5.0 (Windows NT 6.0) AppleWebKit/536.5 (KHTML, like Gecko) Chrome/19.0.1084.46 Safari/536.5").method(Connection.Method.POST).get();

            } catch (Exception ex) {
                // Logger.getLogger(WebCrawler.class.getName()).log(Level.SEVERE, null, ex);
            }

            Pattern pattern = Pattern.compile(regex);
            Pattern url = Pattern.compile(regex2);
            Matcher matcher1 = pattern.matcher(c.getHtml(URL));
            while (matcher1.find()) {

                Matcher matcher2 = url.matcher(matcher1.group());
                if (matcher2.find()) {
                    if (!matcher2.group().contains("w3.org")) {

                        crawl(matcher2.group());
                    }
                }
            }
        }

    }

    public void display() {

        for (String link : links) {
            System.out.println(link);

        }
    }

    public List<String> multiCrawl(List<String> list, String path, String ip) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (String elem : list) {
            // crawl(elem);
            arrayList.addAll(getLinks(elem));
        }
        PrintWriter out2;
        try {
            out2 = new PrintWriter(path + ip + ".txt");

            int i = 0;
            for (String elem : c.cleanDublication(arrayList)) {
                out2.println(elem);

                i++;
                //System.out.println("http://" + elem + "/");
            }
            out2.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(WebCrawler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c.cleanDublication(arrayList);
    }

    public List<String> getLinks(String url) {

        //ArrayList<String> toArray = new ArrayList<>();
        try {
            crawl(url);
        } catch (Exception e) {

        }

        return links;
    }
}
