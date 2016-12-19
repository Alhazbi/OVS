/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qu.senior.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Website  implements Serializable{
    private String URL; 
    private List<String> exploits; 

    
    public Website() {
        this.exploits = new ArrayList<String>();
    }
    public Website(String URL, List<String> exploits) {
        this.URL = URL;
        this.exploits = exploits;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public List<String> getExploits() {
        return exploits;
    }

    public void setExploits(List<String> exploits) {
        this.exploits = exploits;
    }
    
}
