package nanowebserver.web;

import nanowebserver.Main;
import nanowebserver.nanohttpd.NanoHTTPD;
import nanowebserver.nanohttpd.NanoHTTPD.IHTTPSession;
import nanowebserver.nanohttpd.NanoHTTPD.Method;
import nanowebserver.nanohttpd.NanoHTTPD.Response;
import nanowebserver.nanohttpd.WebServer.WebPage;

public class GenericPage implements WebPage
{
    protected String uri = "";
    protected String message = "";

    public GenericPage(String uri, String message)
    {
        this.uri = uri;
        this.message = message;
    }
    
    @Override
    public boolean handlesURI(String requestedUri)
    {
        return requestedUri.equals(this.uri) || requestedUri.equals(this.uri + "/");
    }

    @Override
    public boolean handlesMethod(Method method)
    {
        return method.equals(Method.GET);
    }

    @Override
    public Response serve(IHTTPSession session)
    {
    	System.out.println(Main.getTimeStamp() + " Serving " + uri);
    	
        StringBuilder sb = new StringBuilder();

        Template.addHeader(sb, session);
        Template.addMenu(sb, session);

        sb.append("<div class=\"clear\"/>\n");
        sb.append("<p>");
        sb.append(message);
        sb.append("</p>\n");

        sb.append("<p></p>\n");
        
        Template.addTimeStamp(sb, session);
        
        //DebugSection.add(sb, session);

        Template.addFooter(sb, session);

        return new Response(Response.Status.OK, NanoHTTPD.MIME_HTML, sb.toString());
    }

}