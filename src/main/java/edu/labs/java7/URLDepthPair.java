package edu.labs.java7;

import java.net.MalformedURLException;
import java.net.URL;

/*Класс пар URL-Depth является только местом для хранения URL-адресов и значений глубины, 
а также включает в себя несколько дополнительных утилит. */

public class URLDepthPair {
    private String url;
    private String path;
    private String host;
    private int depth;


    URLDepthPair(String url, int depth){ //конструктор задает текущий url-адрес и глубину
        this.url = url;
        this.depth = depth;
        try {
            URL myurl = new URL(url);
            path = myurl.getPath();
            host = myurl.getHost();
            
        /*В случае, если вы найдете URL-адрес, который не начинается с «http: //», 
        вы должны выдать исключение MalformedURLException, которое является частью Java API.*/
        } catch (MalformedURLException e) {
            System.out.println("MalformedURLException: " + e.getMessage());
        }
    }


    @Override
    public String toString() {//метод toString, который выводит содержимое пары. Этот метод упрощает вывод результатов веб-сканирования.
        return depth + "    " + url;
    }

    public int getDepth() {
        return depth;
    }

    public String getUrl() {
        return url;
    }

    public String getHost() {
        return host;
    }

    public String getPath() {
        return path;
    }
}
