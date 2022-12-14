package edu.tasks.first;

public class labCounter {
    public static void main(String[] args) {
        System.out.println(matlab(5, 2, 1));
    }
    public static boolean matlab(int a, int b, int c) {
        for (int i = 0; i < b; i++) {
            a+=a;
        }
        return (a % c == 0);
    }
}
