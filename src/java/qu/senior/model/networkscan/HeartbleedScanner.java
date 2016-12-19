package qu.senior.model.networkscan;

import qu.senior.model.common.common;

public class HeartbleedScanner extends Scanner{
    
    
    //perl ~/Desktop/Senior-Net-Scan/check-ssl-heartbleed.pl google.com:443
    
    common c = new common();

    @Override
    public boolean checkExploit(String ip) throws Exception {
       String out = c.executeCommand("echo \"QUIT\"|openssl s_client -connect "+ip+":443 -tlsextdebug 2>&1");
       
       
       if (out.contains("server extension \"heartbeat\" (id=15)")||out.contains("Connection refused"))
            return false;
       
       //if(out.contains("not vulnerable") || out.contains("timed out") || out.contains("failed to connect")){
    
       //} else return out;
        
//        String out = c.getHtml("https://hbelb.filippo.io/bleed/query?u="+ip+"&skip=1");
//        if(out.contains("error")){
//        return false;
//        }
//        return true;
       
       return true;
    }


    @Override
    public String getName() {
      return "HeartBleed SSL Exploit";
    }

    @Override
    public int getPortNeeded() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



}
