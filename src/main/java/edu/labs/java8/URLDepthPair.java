package edu.labs.java8;

import java.net.MalformedURLException;
import java.net.URL;

public class URLDepthPair {

    private final String currentURL;
    private final int currentDepth;

    public URLDepthPair(String URL, int depth) {

        currentURL = URL;
        currentDepth = depth;
    }
    public String getURL() {

        return currentURL;
    }
    public int getDepth() {

        return currentDepth;
    }

    @Override
    public String toString() {

        String stringDepth = Integer.toString(currentDepth);
        return stringDepth + '\t' + currentURL;
    }

    public String getDocPath() {

        try {

            URL url = new URL(currentURL);
            return url.getPath();
        }

        catch (MalformedURLException e) {

            System.err.println("MalformedURLException: " + e.getMessage());
            return null;
        }
    }

    public String getWebHost() {

        try {

            URL url = new URL(currentURL);
            return url.getHost();
        }

        catch (MalformedURLException e) {

            System.err.println("MalformedURLException: " + e.getMessage());
            return null;
        }
    }
}