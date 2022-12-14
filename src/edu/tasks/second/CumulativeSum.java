package edu.tasks.second;

import java.util.Arrays;

public class CumulativeSum {
    public static void main(String[] args) {
        System.out.println(CumulativeSum(new int[]{1, 2, 3}));
    }
    public static String CumulativeSum(int[] array) {
        int[] myArray = new int[array.length];

        for(int i = 0; i < array.length; ++i) {
            for(int j = 0; j <= i; ++j) {
                myArray[i] += array[j];
            }
        }

        String result = Arrays.toString(myArray);
        return result;
    }
}
