/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qu.senior.model.networkscan;


public abstract class Scanner {
    public abstract boolean checkExploit(String ip) throws Exception;
    public abstract String getName();
    public abstract int getPortNeeded();
}
