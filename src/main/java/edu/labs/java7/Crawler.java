package edu.labs.java7;
import java.net.MalformedURLException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Crawler {

    public static void showResult(ConcurrentHashMap<URLDepthPair, Boolean> source) {
        for (Map.Entry<URLDepthPair, Boolean> item : source.entrySet()){
            System.out.println(item.getKey());
        }
    }

    private static Thread newTask(URLPool pool){
        return new Thread(new CrawlerTask(pool));
    }

    public static void createWorkers(URLPool pool, int numThreads){
        for (int i = 0; i < numThreads; i++) {
            Thread taskWorker = newTask(pool);
            taskWorker.start();
        }
    }

    public static void main(String[] args) {
        args = new String[]{"https://youtube.com", "2", "2"};
        if (args.length == 3) {
            String startUrl = args[0];
            int maxDepth = Integer.parseInt(args[1]);
            int numThreads = Integer.parseInt(args[2]);

            URLPool pool = new URLPool(maxDepth);

            try {
                pool.addPair(new URLDepthPair(startUrl, 0));
            } catch (MalformedURLException e) {
                System.out.println("Invalid start URL");
                System.exit(0);
            }

            createWorkers(pool, numThreads);

            while (pool.getWaitingWorkers() != numThreads) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ignored) {
                }
            }

            try {
                showResult(pool.getResult());;
            } catch (NullPointerException ignored) {
            }
            System.exit(0);

        } else {
            System.out.println("usage: java Crawler <URL> <maximum_depth> <num_threads> or second/third not digit");
        }
    }
}

