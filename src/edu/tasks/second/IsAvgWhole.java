package edu.tasks.second;

public class IsAvgWhole {
    public static void main(String[] args) {
        System.out.println(IsAvgWhole(new int[]{1, 3}));
    }
    public static boolean IsAvgWhole(int[] array) {
        int sum = 0;

        for(int i = 0; i < array.length; ++i) {
            sum += array[i];
        }

        return sum % array.length == 0;
    }
}
