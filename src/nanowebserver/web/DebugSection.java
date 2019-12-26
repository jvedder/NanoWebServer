package nanowebserver.web;

import java.util.HashMap;
import java.util.Map;

import nanowebserver.nanohttpd.NanoHTTPD.IHTTPSession;

public class DebugSection
{
    public static void add1(StringBuilder sb, IHTTPSession session)
    {
        return;
    }

    public static void add(StringBuilder sb, IHTTPSession session)
    {
        sb.append("<div class=\"panel\">\n");

        sb.append("<h3>Response Debug</h3>\n");

        sb.append("<p>");
        sb.append("<code>");
        sb.append(String.valueOf(session.getMethod()));
        sb.append(" ");
        sb.append(String.valueOf(session.getUri()));
        sb.append("</code>");
        sb.append("</p>\n");

        // sb.append("<h3>Headers</h3>\n");
        // // sb.append("<p>");
        // sb.append(toString(session.getHeaders()));
        // // sb.append("</p>\n");

        sb.append("<h3>Parameters</h3>\n");
        // sb.append("<p>");
        sb.append(toString(session.getParms()));
        // sb.append("</p>\n");

        // // Map<String, String> parms = session.getParms();
        // Map<String, List<String>> decodedQueryParameters =
        // decodeParameters(session.getQueryParameterString());
        //
        // sb.append("<h3>Parameters [as lists]</h3>\n");
        // // sb.append("<p>");
        // sb.append(toString(decodedQueryParameters));
        // // sb.append("</p>\n");

        try
        {
            Map<String, String> files = new HashMap<String, String>();
            session.parseBody(files);
            if (files.size() < 0)
            {
                sb.append("<h3>Files</h3>\n");
                sb.append("<p>");
                sb.append(toString(files));
                sb.append("</p>\n");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        sb.append("</div>\n");
    }

    private static String toString(Map<String, ? extends Object> map)
    {
        if (map.size() == 0)
        {
            return "";
        }
        return unsortedList(map);
    }

    @SuppressWarnings("rawtypes")
    private static String unsortedList(Map<String, ? extends Object> map)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("<ul>\n");
        for (Map.Entry entry : map.entrySet())
        {
            listItem(sb, entry);
        }
        sb.append("</ul>\n");
        return sb.toString();
    }

    @SuppressWarnings("rawtypes")
    private static void listItem(StringBuilder sb, Map.Entry entry)
    {
        sb.append("<li><code><b>");
        sb.append(entry.getKey());
        sb.append("</b> = ");
        sb.append(entry.getValue());
        sb.append("</code></li>\n");
    }
}
