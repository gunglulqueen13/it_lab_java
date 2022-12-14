package edu.tasks.first;

public class Operation {
    public static void main(String[] args) {
        System.out.println(operation(24,15, 9));
    }
    public static String operation(int result, int a, int b) {
        if (a + b == result) return "added";
        else if (a - b == result) return  "subtracted";
        else if (a * b == result) return  "multiplication";
        else if (a / b == result) return "division";
        return "none";
    }
}
