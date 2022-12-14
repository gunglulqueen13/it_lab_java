package edu.tasks.first;
public class Animals {
    public static void main(String[] args) {
        System.out.println(legsCount(2,3, 5));
    }
    public static int legsCount(int chickens, int cows, int pigs) {
        return ((chickens * 2) + (cows * 4) + (pigs * 4));
    }
}
