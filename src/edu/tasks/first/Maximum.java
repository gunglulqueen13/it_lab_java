package edu.tasks.first;

public class Maximum {
    public static void main(String[] args) {
        System.out.println(nextEdge(8, 10));
    }
    public static int nextEdge(int a, int b) {
        return (a + b - 1);
    }
}
