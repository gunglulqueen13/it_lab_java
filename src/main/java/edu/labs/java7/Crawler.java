package edu.labs.java7;

import java.net.*;
import java.io.*;
import java.util.*;

/*Сканер (Crawler) - это класс, который перемещается по веб-страницам и ищет URL-адреса, 
поэтому класс сканера должен включать в себя код, который фактически открывает и закрывает сокеты. */

public class Crawler {
    /*создайте строковые константы в классах */
    public static final String URL_INDICATOR = "a href=\"";
    public static final String URL_ENDING = "\"";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));//Для потоков ввода вы можете использовать классы InputStreamReader
        int maxDepth = 0;
        String[] strings;
        System.out.println("usage: <URL> <depth> (0 - for exit)");

        while (true) {
            strings = br.readLine().split(" ");
            if (strings[0].equals("0"))return;
            if (strings.length != 2) {
                System.out.println("usage: <URL> <depth> (0 - for exit)");
            } else {
                try {
                    maxDepth = Integer.parseInt(strings[1]);
                } catch (NumberFormatException e) {
                    System.out.println("usage: <URL> <depth> (0 - for exit)");
                }
            }
            break;
        }
        //Для хранения пар (URL, depth) используйте LinkedList, который является реализацией List.
        LinkedList <URLDepthPair> inQueueURLs = new LinkedList <URLDepthPair>();
        LinkedList <URLDepthPair> processedURLs = new LinkedList <URLDepthPair>();
        ArrayList<String> seenURLs = new ArrayList<String>();

        inQueueURLs.add(new URLDepthPair(strings[0],0));
        seenURLs.add(strings[0]);

         // проверка каждого адреса пока список ожидающих непустой
        while (inQueueURLs.size() != 0){
             // получение следующего адреса из ожидающих и добавление к обработанным, сохранение его глубины
            URLDepthPair pair = inQueueURLs.pop();
            processedURLs.add(pair);
            int depth = pair.getDepth();
             // получение всех ссылок с сайта и сохранение в новом списке ссылок
            ArrayList<String> links = new ArrayList<String>();
            links = getURLs(pair);
            // проверка глубины данной и заданной
            if(maxDepth > depth){
                // перебор ссылок с сайта
                for (String link:links){
                    // если ссылка не встречалась, то мы добавляем в ожидающие и прибавляем глубину
                    if(!seenURLs.contains(link)){
                        inQueueURLs.add(new URLDepthPair(link,depth + 1));
                        seenURLs.add(link);
                    }
                }

            }
        }
        //выводит пару
        for (URLDepthPair pair:processedURLs) {
            System.out.println(pair);
        }
    }

    private static ArrayList<String> getURLs(URLDepthPair pair){
        ArrayList<String> URLs = new ArrayList<String>();
        /*нужно создать новый экземпляр Socket для каждого URL-адреса, с которого вы загружаете текст. */
        Socket socket;
        try {
            socket = new Socket(pair.getHost(),80);
        } catch (UnknownHostException e) {
            System.out.println("UnknownHostException: " + e.getMessage());
            return URLs;
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
            return URLs;
        }

        try {
            socket.setSoTimeout(3000);//устанавливает время ожидания сокета (Socket) в миллисекундах.
        } catch (SocketException e) {
            System.out.println("SocketException: " + e.getMessage());
            return URLs;
        }

        InputStream inputStream;
        OutputStream outputStream;

        try {
            outputStream = socket.getOutputStream();//возвращает OutputStream, связанный с Socket. 
            //Этот метод позволяет сокету отправлять данные на другую сторону соединения.
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
            return URLs;
        }

        // Отправка запроса на сервер
        PrintStream printStream = new PrintStream(outputStream, true);
        printStream.println("GET " + pair.getPath() + " HTTP/1.1");
        printStream.println("Host: " + pair.getHost() + ":80");
        printStream.println("Connection: close");
        printStream.println();

        try {
            inputStream = socket.getInputStream();//возвращает InputStream, связанный с Socket. 
            //Этот метод позволяет сокету получать данные с другой стороны соединения.
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
            return URLs;
        }
        InputStreamReader in = new InputStreamReader (inputStream);//in имеет тип InputStreamReader, который может читать символы из сокета (Socket)
        BufferedReader reader = new BufferedReader(in);
        while (true){
            String string;
            int beginIndex = 0;
            int endIndex = 0;

            try {
                string = reader.readLine();
            } catch (IOException e) {
                System.out.println("IOException: " + e.getMessage());
                return URLs;
            }
            if(string == null)return URLs;
            while (true){
                beginIndex = string.indexOf(URL_INDICATOR,beginIndex);
                if(beginIndex == -1)break;
                //if(string.contains("https://"))break;
                beginIndex += URL_INDICATOR.length();
                endIndex = string.indexOf(URL_ENDING, beginIndex);
                String temp = string.substring(beginIndex,endIndex);
                if(!temp.contains("http"))temp = "http://" + pair.getHost() + temp;
                URLs.add(temp);
                beginIndex = endIndex;
            }
        }
    }
}

