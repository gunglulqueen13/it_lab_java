package edu.tasks.first;

public class AddUpTo {
    public static void main(String[] args) {
        int[] array = {1, 2, 3};
        int a = array[array.length - 1];
        System.out.println(AddUpTo(a));
    }
    public static int AddUpTo(int a) {
        int amount = 0;
        for (int i = 1; i <= a; i++) {
            amount += i;
        }
        return amount;
    }
}
