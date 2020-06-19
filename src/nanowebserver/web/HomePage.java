package nanowebserver.web;

import nanowebserver.Main;
import nanowebserver.nanohttpd.NanoHTTPD;
import nanowebserver.nanohttpd.NanoHTTPD.IHTTPSession;
import nanowebserver.nanohttpd.NanoHTTPD.Method;
import nanowebserver.nanohttpd.NanoHTTPD.Response;
import nanowebserver.nanohttpd.WebServer.WebPage;

public class HomePage implements WebPage
{
    public final String URI = "/";

    @Override
    public boolean handlesURI(String uri)
    {
        return uri.equals(URI);
    }

    @Override
    public boolean handlesMethod(Method method)
    {
        return method.equals(Method.GET);

    }

    @Override
    public Response serve(IHTTPSession session)
    {
    	System.out.println(Main.getTimeStamp() + " Serving Home page.");
    	
        StringBuilder sb = new StringBuilder();

        Template.addHeader(sb, session);
        Template.addMenu(sb, session);

        sb.append("<div class=\"clear\"/>\n");
        sb.append("<p>This is the home page</p>\n");
        sb.append("<p>Other pages are <a href='/abc'>/abc</a> and <a href='/xyz'>/xyz</a></p>\n");
        
        sb.append("<p></p>\n");
        
        Template.addTimeStamp(sb,  session);
        
        //DebugSection.add(sb, session);

        Template.addFooter(sb, session);

        return new Response(Response.Status.OK, NanoHTTPD.MIME_HTML, sb.toString());
    }

}