package edu.labs.java8;

// данный класс хранит всех URL-адресов для поиска, а также относительный "уровень" каждого из
//этих URL-адресов
import java.util.ArrayList;
import java.util.LinkedList;

public class URLPool {
    //список ожидающих адресов
    private final LinkedList<URLDepthPair> pendingURLs;
    // список обработанных адресов
    public LinkedList<URLDepthPair> processedURLs;
    // массив просмотренных адресов
    private final ArrayList<String> seenURLs = new ArrayList<>();
    // количество ожидающих потоков
    public int waitingThreads;
    int maxDepth;

    // инициализация ожидающих потоков и списков с ожидающими и обработанными адресами
    public URLPool(int maxDepthPair) {

        maxDepth = maxDepthPair;
        waitingThreads = 0;
        pendingURLs = new LinkedList<>();
        processedURLs = new LinkedList<>();
    }
    // получение ожидающих потоков
    public synchronized int getWaitThreads() {

        return waitingThreads;
    }
    // получение размера списка с ожидающими адресами
    public synchronized int size() {

        return pendingURLs.size();
    }
    // добавляет пару пару глубина - адрес
    public synchronized void put(URLDepthPair depthPair) {
        if (waitingThreads != 0) {

            --waitingThreads;
            this.notify(); // возобновляет работу потока, если ссылки не равны 0
        }

        if (!seenURLs.contains(depthPair.getURL()) &
                !pendingURLs.contains(depthPair)) {

            if (depthPair.getDepth() < maxDepth) {

                pendingURLs.add(depthPair);
            }

            else {

                processedURLs.add(depthPair);
                seenURLs.add(depthPair.getURL());
            }
        }
    }
    // получение следующей пары
    public synchronized URLDepthPair get() {

        URLDepthPair myDepthPair;

        // ожидание при пустом списке ожидающих адресов, увеличение ожидающих потоков
        while (pendingURLs.isEmpty()) {

            ++waitingThreads;

            try {

                this.wait();
            }

            catch (InterruptedException e) {

                System.err.println("MalformedURLException: " + e.getMessage());
                return null;
            }
        }

        // добавление пары в списки просмотренных и обработанных адресов
        myDepthPair = pendingURLs.pop();

        while (seenURLs.contains(myDepthPair.getURL())) {

            myDepthPair = pendingURLs.pop();
        }

        processedURLs.add(myDepthPair);
        seenURLs.add(myDepthPair.getURL());

        return myDepthPair;
    }
}
