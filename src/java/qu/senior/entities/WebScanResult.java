/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qu.senior.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebScanResult  implements Serializable{
    // key url list exploit
    private List<Website> websites;

    public WebScanResult() {
        this.websites = new ArrayList<Website>();
    }
    public WebScanResult(List<Website> websites) {
        this.websites = websites;
    }

    public List<Website> getWebsites() {
        return websites;
    }

    public void setWebsites(List<Website> websites) {
        this.websites = websites;
    }

    
    
}
