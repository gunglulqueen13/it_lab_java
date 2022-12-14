package edu.tasks.second;

public class Repeat {
    public static void main(String[] args) {
        System.out.println(repeat("mice", 5));
    }
    public static String repeat(String x, int c) {
        String result = "";

        for(int i = 0; i < x.length(); ++i) {
            char sim = x.charAt(i);

            for(int j = 0; j < c; ++j) {
                result += sim;
            }
        }

        return result;
    }
}
