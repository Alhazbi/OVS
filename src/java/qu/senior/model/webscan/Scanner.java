/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qu.senior.model.webscan;

import qu.senior.entities.WebScanResult;

public abstract class Scanner {

    public abstract void scan(String ip, WebScanResult webScanResult);

    public abstract String getName();
    
    

}
