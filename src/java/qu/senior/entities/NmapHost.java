/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qu.senior.entities;

/**
 *
 * @author abdulla
 */
public class NmapHost {
    
    private String ip;
    private boolean open;
    private String report;

    public NmapHost() {
    }

    public NmapHost(String ip, boolean open, String report) {
        this.ip = ip;
        this.open = open;
        this.report = report;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }
    
    
    
}
