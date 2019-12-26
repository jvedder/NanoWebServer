package nanowebserver.web;

import java.util.Date;

import nanowebserver.nanohttpd.NanoHTTPD.IHTTPSession;

public class Template
{
    public static void addHeader(StringBuilder sb, IHTTPSession session)
    {
        sb.append("<html>\n");
        sb.append("<head>\n");
        sb.append("<title>NanoWebServer</title>\n");

        sb.append("<style>\n");
        sb.append(".error {color:red; border:1px solid red; background-color:pink; padding:0 10px; margin:10px;}\n");
        sb.append(".clear {clear:both;}\n");
        sb.append(".menu  {padding:10px; margin:10px; background-color:LightGrey;}\n");
        sb.append(".panel {border:1px solid black; padding:10px; margin:10px;}\n");
        sb.append(".box   {border:1px solid black; padding:10px; margin:10px; float:left}\n");
        sb.append("</style>\n");

        sb.append("</head>\n");
        sb.append("<body>\n");
        return;
    }

    public static void addMenu(StringBuilder sb, IHTTPSession session)
    {
        //sb.append("<div class=\"menu\">\n");
        //sb.append("</div>\n");
        return;
    }
    
    public static void addTimeStamp(StringBuilder sb, IHTTPSession session)
    {
        Date now = new Date();
        
        sb.append("<p>");
        sb.append(String.format("%tD %tT", now, now));
        sb.append("</p>\n");
        return;
    }

    public static void addFooter(StringBuilder sb, IHTTPSession session)
    {
        sb.append("</body>\n");
        sb.append("</html>\n");
        return;
    }

}
