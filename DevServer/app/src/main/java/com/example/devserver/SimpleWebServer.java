
package com.example.devserver;

import fi.iki.elonen.NanoHTTPD;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

public class SimpleWebServer extends NanoHTTPD {

    private static final String ASSETS_PATH = "/data/user/0/com.example.devserver/files/assets"; // Pfad zu den HTML-Dateien

    public SimpleWebServer(int port) {
        super(port);
    }

    @Override
    public Response serve(IHTTPSession session) {
        Map<String, String> headers = session.getHeaders();
        String uri = session.getUri();
        File file = new File(ASSETS_PATH + uri);
        if (file.exists() && !file.isDirectory()) {
            try {
                FileInputStream fis = new FileInputStream(file);
                return newFixedLengthResponse(Response.Status.OK, getMimeType(uri), fis, (int) file.length());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return newFixedLengthResponse(Response.Status.NOT_FOUND, "text/plain", "File not found");
    }

    private String getMimeType(String uri) {
        if (uri.endsWith(".html")) {
            return "text/html";
        } else if (uri.endsWith(".js")) {
            return "application/javascript";
        } else if (uri.endsWith(".css")) {
            return "text/css";
        }
        return "text/plain";
    }
}
