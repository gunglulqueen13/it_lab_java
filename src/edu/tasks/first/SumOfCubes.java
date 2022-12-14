package edu.tasks.first;

public class SumOfCubes {
    public static void main(String[] args) {
        int[] array = {1, 5, 9};
        System.out.println(SumOfCubes(array));
    }
    public static int SumOfCubes(int[] array) {
        int amount = 0;
        if (array.length > 0) {
            for (int i = 0; i < array.length; i++) {
                amount += Math.pow(array[i], 3);
            }
        }
        return amount;
    }
}
