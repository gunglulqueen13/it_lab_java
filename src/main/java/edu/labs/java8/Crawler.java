package edu.labs.java8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Crawler {

    public static void main(String[] args) {

        int maxDepthPair = 0;

        int numThreads = 5;
        //проверяет на корректность длины входных данных (адрес глубина и потоки)
        if (args.length != 3) {

            System.out.println("usage: java Crawler <URL> <depth> "
                    + "<number of crawler threads>");
            System.exit(1);
        }

        try {//значение глубиныи и потока из строки в целое значение
            maxDepthPair = Integer.parseInt(args[1]);
            numThreads = Integer.parseInt(args[2]);
        }

        catch (NumberFormatException nfe) {

            System.out.println("usage: java Crawler <URL> <depth> "
                    + "<number of crawler threads>");
            System.exit(1);
        }

         // Пара URL-адресов с глубиной для представления веб-сайта, который пользователь ввел с глубиной 0
        URLDepthPair currentDepthPair = new URLDepthPair(args[0], 0);

        // пул и добавление в него введенного пользователем адреса
        URLPool pool = new URLPool(maxDepthPair);
        pool.put(currentDepthPair);

        // общее число потоков
        int initialActiveThreads = Thread.activeCount();

        // ожидающие потоки не равны запрошенному количеству потоков, если общее количество потоков меньше запрошенного количества
        // потоков, создайте больше потоков
        while (pool.getWaitThreads() != numThreads) {

            if (Thread.activeCount() - initialActiveThreads < numThreads) {
                CrawlerTask crawler = new CrawlerTask(pool);
                new Thread(crawler).start();
            } else {
                try {
                    Thread.sleep(500);
                }
                catch (InterruptedException ie) {
                    System.out.println("Caught unexpected InterruptedException,"
                            + " ignoring...");
                }
            }
        }

        for (int counter = 0; counter < pool.processedURLs.size(); ++counter){

            System.out.println(pool.processedURLs.get(counter));
        }

        System.exit(0);
    }

    static LinkedList<String> getSites(URLDepthPair myDepthPair)
            throws IOException {
        LinkedList<String> URLs = new LinkedList<>();

        Socket sock;

        try {

            sock = new Socket(myDepthPair.getWebHost(), 80);
        }

        catch (UnknownHostException e) {

            System.err.println("UnknownHostException: " + e.getMessage());
            return URLs;
        }

        catch (IOException ex) {

            System.err.println("IOException: " + ex.getMessage());
            return URLs;
        }

        try {

            sock.setSoTimeout(3000);
        }

        catch (SocketException exc) {

            System.err.println("SocketException: " + exc.getMessage());
            return URLs;
        }

        String docPath = myDepthPair.getDocPath();
        String webHost = myDepthPair.getWebHost();

        OutputStream outStream;
        try {

            outStream = sock.getOutputStream();
        }

        catch (IOException exce) {

            System.err.println("IOException: " + exce.getMessage());
            return URLs;
        }

        PrintWriter myWriter = new PrintWriter(outStream, true);
        if (docPath.length() == 0) {

            myWriter.println("GET / HTTP/1.1");
            myWriter.println("Host: " + webHost);
            myWriter.println("Connection: close");
            myWriter.println();
        }
        else {

            myWriter.println("GET " + docPath + " HTTP/1.1");
            myWriter.println("Host: " + webHost);
            myWriter.println("Connection: close");
            myWriter.println();
        }

        InputStream inStream;
        try {

            inStream = sock.getInputStream();
        }

        catch (IOException excep){

            System.err.println("IOException: " + excep.getMessage());
            return URLs;
        }

        InputStreamReader inStreamReader = new InputStreamReader(inStream);
        BufferedReader BuffReader = new BufferedReader(inStreamReader);
        int serverCode = 0;
        String lineCode;

        try {

            lineCode = BuffReader.readLine();
        }

        catch (IOException except) {

            System.err.println("IOException: " + except.getMessage());
            return URLs;
        }

        if (lineCode == null) {

            System.out.println("Ошибка: сайт \"" + myDepthPair.getURL() +
                    "\" вернул пустой ответ");
            return URLs;
        }

        Pattern patternCode = Pattern.compile("(2|3|4)[0-9]{2}");
        Matcher matcherCode = patternCode.matcher(lineCode);

        while (matcherCode.find()) {

            serverCode = Integer.valueOf(lineCode.substring(matcherCode.start(), matcherCode.end() - 2));
        }

        if (serverCode == 2) {

            while (true) {

                String line;

                try {

                    line = BuffReader.readLine();
                }

                catch (IOException except) {

                    System.err.println("IOException: " + except.getMessage());
                    return URLs;
                }
                if (line == null) {

                    break;
                }
                Pattern patternURL = Pattern.compile(
                        "[\"]" + "[https?://]{7,8}" + "([w]{3})?" + "[\\w\\.\\-]+" + "\\." + "[A-Za-z]{2,6}" + "[\\w\\.-/]*" + "[\"]");

                Matcher matcherURL = patternURL.matcher(line);

                while (matcherURL.find()) {

                    String newLink = line.substring(matcherURL.start() + 1,
                            matcherURL.end() - 1);

                    URLs.add(newLink);
                }
            }

            sock.close();

            return URLs;
        }

        if (serverCode == 3) {

            String newURL = "";
            String tempLine;

            while (true) {

                try {

                    tempLine = BuffReader.readLine();
                }

                catch (IOException except) {

                    System.err.println("IOException: " + except.getMessage());
                    return URLs;
                }
                if (tempLine == null) {

                    break;
                }
                Pattern patternNewURL = Pattern.compile(
                        "(Location: ){1}[\\S]+");
                Matcher matcherNewURL = patternNewURL.matcher(tempLine);

                while (matcherNewURL.find()) {

                    newURL = tempLine.substring(matcherNewURL.start() + 10,
                            matcherNewURL.end());
                }
            }

            if (newURL.equals(myDepthPair.getURL())) {

                System.out.println("Ошибка: сайт \"" + myDepthPair.getURL() +
                        "\" перенаправляет на самого себя"
                        + " (код ответа HTML 3xx)");

                sock.close();
                return URLs;
            }

            URLDepthPair newDepthPair;
            newDepthPair = new URLDepthPair(newURL, myDepthPair.getDepth());

            return getSites(newDepthPair);
        }

        else {

            System.out.println("Ошибка: сайт \"" + myDepthPair.getURL() +
                    "\" недоступен (код ответа HTML 4xx)");

            sock.close();
            return URLs;
        }
    }
}