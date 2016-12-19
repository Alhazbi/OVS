/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qu.senior.model.webscan;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.Stateless;
import org.jsoup.examples.ListLinks;

@Stateless
public class WFI {

    String regex = "(http|https)+:\\/\\/[-a-zA-Z0-9+&@#%?=~_|!:,.;]+";

    public List<String> cleanDublication(List<String> linkList) {

        List<String> al = linkList;
// add elements to al, including duplicates
        Set<String> hs = new HashSet<>();
        hs.addAll(al);
        al.clear();
        al.addAll(hs);

        return al;

    }

    public List<String> getLinks(String ipAddress) {
        ArrayList<String> list = new ArrayList<>();

        try {

            for (int i = 1; i < 202; i += 5) {
                String url = "http://www.bing.com/search?q=ip%3A" + ipAddress + "&go=Submit&qs=ds&first=" + i + "&FORM=PERE";
                Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0 (iPad; U; CPU OS 3_2 like Mac OS X; en-us) AppleWebKit/531.21.10 (KHTML, like Gecko) Version/4.0.4 Mobile/7B334b Safari/531.21.10").timeout(10000).get();
                Elements links = doc.select("a[href]");
                Pattern URL = Pattern.compile(regex);

                for (Element link : links) {
                    if (!link.attr("abs:href").contains("microsoft") && !link.attr("abs:href").contains("bing")) {

                        Matcher matcher = URL.matcher(link.attr("abs:href"));
                        if (matcher.find()) {
                            list.add(matcher.group());
                        }
                    }
                }

            }

        } catch (IOException ex) {
            Logger.getLogger(ListLinks.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cleanDublication(list);
    }
//    public static void main(String[] args){
//    WFI w = new WFI();
//        System.out.println(w.getLinks("86.36.67.254"));
//    
//    }

}