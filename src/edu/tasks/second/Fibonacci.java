package edu.tasks.second;

public class Fibonacci {
    public static void main(String[] args) {
        System.out.println(Fibonacci(3));
    }
    public static int Fibonacci(int x) {
        int[] array = new int[x + 1];
        int result = 0;
        array[0] = 1;
        array[1] = 1;

        for(int i = 2; i < x + 1; ++i) {
            array[i] = array[i - 1] + array[i - 2];
            result = array[i];
        }

        return result;
    }
}
