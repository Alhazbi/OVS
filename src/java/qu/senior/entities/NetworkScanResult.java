/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qu.senior.entities;

import java.io.Serializable;


public class NetworkScanResult implements Serializable{
   private boolean freakSSL; 
   private boolean heartBleedSSL;
   private boolean poodleSSL;

    public NetworkScanResult() {
    }

    public NetworkScanResult(boolean freakSSL, boolean heartBleedSSL, boolean poodleSSL) {
        this.freakSSL = freakSSL;
        this.heartBleedSSL = heartBleedSSL;
        this.poodleSSL = poodleSSL;
    }

    public boolean isFreakSSL() {
        return freakSSL;
    }

    public boolean isHeartBleedSSL() {
        return heartBleedSSL;
    }

    public boolean isPoodleSSL() {
        return poodleSSL;
    }

    public void setFreakSSL(boolean freakSSL) {
        this.freakSSL = freakSSL;
    }

    public void setHeartBleedSSL(boolean heartBleedSSL) {
        this.heartBleedSSL = heartBleedSSL;
    }

    public void setPoodleSSL(boolean poodleSSL) {
        this.poodleSSL = poodleSSL;
    }
   
}
