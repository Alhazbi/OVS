/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qu.senior.services;

public class ScanParam {
    private String ip; 
    private boolean isNetworkScan;
    private boolean isWebScan;
    private int scanlevel;

    public ScanParam() {
    }

    public ScanParam(String ip, boolean isNetworkScan, boolean isWebScan, int scanlevel) {
        this.ip = ip;
        this.isNetworkScan = isNetworkScan;
        this.isWebScan = isWebScan;
        this.scanlevel=scanlevel;
    }

    public int getScanlevel() {
        return scanlevel;
    }

    public void setScanlevel(int scanlevel) {
        this.scanlevel = scanlevel;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public boolean isIsNetworkScan() {
        return isNetworkScan;
    }

    public void setIsNetworkScan(boolean isNetworkScan) {
        this.isNetworkScan = isNetworkScan;
    }

    public boolean isIsWebScan() {
        return isWebScan;
    }

    public void setIsWebScan(boolean isWebScan) {
        this.isWebScan = isWebScan;
    }
    
    
    
}
