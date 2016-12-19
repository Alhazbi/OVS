package qu.senior.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity

public class Scan implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
   
    private int userId; 
    
    @ManyToOne()
    @JoinColumn(name = "userId", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private User user;
    private String ip;
    
    @Temporal(TemporalType.DATE)
    
    private Date scanDate;
    
    private boolean isNetworkScam;
    private boolean isWebScan;

    private NetworkScanResult networkScanResult;
   
    private WebScanResult webScanResult;

    public Scan() {
    this.networkScanResult = new NetworkScanResult();
    this.webScanResult = new WebScanResult();    
    this.scanDate=new Date();
    }

    
    public Scan(int id, int userId, User user, String ip, Date scanDate, boolean isNetworkScam, boolean isWebScan, NetworkScanResult networkScanResult, WebScanResult webScanResult) {
        this.id = id;
        this.userId = userId;
        this.user = user;
        this.ip = ip;
        this.scanDate = scanDate;
        this.isNetworkScam = isNetworkScam;
        this.isWebScan = isWebScan;
        this.networkScanResult = networkScanResult;
        this.webScanResult = webScanResult;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public User getUser() {
        return user;
    }

    public String getIp() {
        return ip;
    }

    public Date getScanDate() {
        return scanDate;
    }

    public boolean isIsNetworkScam() {
        return isNetworkScam;
    }

    public boolean isIsWebScan() {
        return isWebScan;
    }

    public NetworkScanResult getNetworkScanResult() {
        return networkScanResult;
    }

    public WebScanResult getWebScanResult() {
        
        return this.webScanResult;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setScanDate(Date scanDate) {
        this.scanDate = scanDate;
    }

    public void setIsNetworkScam(boolean isNetworkScam) {
        this.isNetworkScam = isNetworkScam;
    }

    public void setIsWebScan(boolean isWebScan) {
        this.isWebScan = isWebScan;
    }

    public void setNetworkScanResult(NetworkScanResult networkScanResult) {
        this.networkScanResult = networkScanResult;
    }

    public void setWebScanResult(WebScanResult webScanResult) {
        this.webScanResult = webScanResult;
    }
    

    
    
}
