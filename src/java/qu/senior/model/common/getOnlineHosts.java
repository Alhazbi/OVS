package qu.senior.model.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class getOnlineHosts {
    
private class ProcessResultReader extends Thread
{
    final InputStream is;
    final String type;
    final StringBuilder sb;

    ProcessResultReader( final InputStream is, String type)
    {
        this.is = is;
        this.type = type;
        this.sb = new StringBuilder();
    }

    public void run()
    {
        try
        {
            final InputStreamReader isr = new InputStreamReader(is);
            final BufferedReader br = new BufferedReader(isr);
            String line = null;
            while ((line = br.readLine()) != null)
            {
                this.sb.append(line).append("\n");
            }
        }
        catch (final IOException ioe)
        {
            System.err.println(ioe.getMessage());
            throw new RuntimeException(ioe);
        }
    }

    @Override
    public String toString()
    {
        return this.sb.toString();
    }
}
    
    
    public String nmap(String ip) {
        // TODO code application logic here
        
try
    {
       // String host = "10.20.150.*";
        String host = ip;

        
        String query = "nmap -sP "+ host;
                //+"-oN ~/Desktop/nmap.txt";
        final Process p = Runtime.getRuntime().exec(String.format(query));
        final ProcessResultReader stderr = new ProcessResultReader(p.getErrorStream(), "STDERR");
        final ProcessResultReader stdout = new ProcessResultReader(p.getInputStream(), "STDOUT");
        stderr.start();
        stdout.start();
        final int exitValue = p.waitFor();
        if (exitValue == 0)
        {
           // System.out.print(stdout.toString());
            return stdout.toString();
        }
        else
        {
            //System.err.print(stderr.toString());
            return stderr.toString();
        }
    }
    catch (final IOException e)
    {
        throw new RuntimeException(e);
    }
    catch (final InterruptedException e)
    {
        throw new RuntimeException(e);
    }
        
        
    }
}
