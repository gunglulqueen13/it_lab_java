package edu.tasks.first;

public class Gamble {
    public static void main(String[] args) {
        System.out.println(gambleProfit(0.2,50, 9));
    }
    public static boolean gambleProfit(double prob, double prize, double pay) {
        if ((prob * prize) > pay) return true;
        return false;
    }
}
