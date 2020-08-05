package nanowebserver.nanohttpd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import nanowebserver.nanohttpd.NanoHTTPD.Response.Status;

public class WebServer extends NanoHTTPD
{
    public interface WebPage
    {
        public boolean handlesURI(String uri);

        public boolean handlesMethod(Method method);

        public Response serve(IHTTPSession session);
    }

    protected static final boolean DEBUG = true;
    protected static final int DEFAULT_PORT = 8080;

    protected List<WebPage> pages;

    public WebServer()
    {
        this(DEFAULT_PORT);
    }

    public WebServer(int port)
    {
        super(port);
        pages = new ArrayList<WebPage>();
    }

    public void registerPage(WebPage page)
    {
        this.pages.add(page);
    }

    public void run()
    {
        try
        {
            this.start();
        }
        catch (IOException ioe)
        {
            System.err.println("Couldn't start server:" + ioe);
        }
        
        int port = this.getListeningPort();
        System.out.println("Server started on port " + port + ". Hit Enter to stop.");

        try
        {
            System.in.read();
        }
        catch (IOException ignored)
        {
        }

        this.stop();
        System.out.println("Server stopped.");
    }

    @Override
    public Response serve(IHTTPSession session)
    {
        Method method = session.getMethod();
        String uri = session.getUri();

        for (WebPage page : pages)
        {
            if (page.handlesURI(uri))
            {
                if (page.handlesMethod(method))
                {
                    return page.serve(session);
                }
                else
                {
                    return new Response(Status.METHOD_NOT_ALLOWED, MIME_PLAINTEXT, Status.METHOD_NOT_ALLOWED.getDescription());
                }
            }
        }

        return new Response(Status.NOT_FOUND, MIME_PLAINTEXT, Status.NOT_FOUND.getDescription());
    }
}
