package nanowebserver.web;

import nanowebserver.nanohttpd.NanoHTTPD;
import nanowebserver.nanohttpd.NanoHTTPD.IHTTPSession;
import nanowebserver.nanohttpd.NanoHTTPD.Method;
import nanowebserver.nanohttpd.NanoHTTPD.Response;
import nanowebserver.nanohttpd.WebServer.WebPage;

public class ErrorPage implements WebPage
{
    private String message = null;
    private Response.Status status = null;

    public ErrorPage(Response.Status status, String message)
    {
        this.status = status;
        this.message = message;
    }

    @Override
    public boolean handlesURI(String uri)
    {
        return true;
    }

    @Override
    public boolean handlesMethod(Method method)
    {
        return true;
    }

    @Override
    public Response serve(IHTTPSession session)
    {
        StringBuilder sb = new StringBuilder();

        Template.addHeader(sb, session);
        Template.addMenu(sb, session);

        sb.append("<div class=\"error\">\n");
        sb.append("<h3>");
        sb.append(status.getDescription());
        sb.append("</h3>\n");

        if (message != null)
        {
            sb.append("<p>");
            sb.append(message);
            sb.append("</p>\n");
        }

        sb.append("</div>\n");

        DebugSection.add(sb, session);

        Template.addFooter(sb, session);

        return new Response(status, NanoHTTPD.MIME_HTML, sb.toString());
    }

}
