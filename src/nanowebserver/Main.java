package nanowebserver;

import java.util.Date;
import java.util.Random;

import nanowebserver.nanohttpd.WebServer;
import nanowebserver.web.GenericPage;
import nanowebserver.web.HomePage;

public class Main
{
    public static Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args)
    {
        int port = 6434;
        if (args.length > 0)
        {
            try 
            {
                port =Integer.parseInt(args[0]);
            }
            catch (Exception Ex)
            { /* ignore */}
            
        }
        WebServer webServer = new WebServer(port);
        
        webServer.registerPage(new HomePage());
        webServer.registerPage(new GenericPage("/abc", "This is ABC page."));
        webServer.registerPage(new GenericPage("/xyz", "This is XYZ page."));
       
        webServer.run();
    }
    
    public static String getTimeStamp()
    {
    	Date now = new Date();
    	return String.format("%tD %tT", now, now);
    }

}
