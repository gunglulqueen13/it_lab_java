package edu.tasks.second;

public class DifferenceMaxMin {
    public static void main(String[] args) {
        System.out.println(DifferenceMaxMin(new int[]{10, 4, 1, 4, -10, -50, 32, 21}));
    }
    public static int DifferenceMaxMin(int[] array) {
        int maximum = array[0];
        int minimum = array[0];

        for(int i = 1; i < array.length; ++i) {
            if (array[i] > maximum) {
                maximum = array[i];
            }

            if (array[i] < minimum) {
                minimum = array[i];
            }
        }

        return maximum - minimum;
    }
}
