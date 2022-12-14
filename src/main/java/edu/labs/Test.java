package edu.labs;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        System.out.println(minuteToSecond("8 2 3 4 5"));
    }
    public static String minuteToSecond(String str) {
        String[] str2;
        str2 = str.split(" ");
        int min = (int)Math.pow(10,8);
        int max = - (int)Math.pow(10,8);
        System.out.println(Arrays.toString(str2));
        for (int i = 0; i < str2.length; i++) {
            if (Integer.parseInt(str2[i]) < min) {
                min = Integer.parseInt(str2[i]);
            }
            if (Integer.parseInt(str2[i]) > max) {
                max = Integer.parseInt(str2[i]);
            }
        }
        System.out.println(min);
        System.out.println(max);
        StringBuilder builder = new StringBuilder();
        builder.append(Integer.toString(min)).append(" ").append(Integer.toString(max));
        return builder.toString();

    }

}