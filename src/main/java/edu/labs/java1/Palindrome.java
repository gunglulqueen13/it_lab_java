package edu.labs.java1;
import java.util.Scanner;
public class Palindrome {
    //Метод main(String[] args) предназначен для ввода строки и проверки на палиндром
    public static void main(String[] args) {
        String str;
        Scanner amount = new Scanner(System.in);
        System.out.print("Enter any strings:");
        str = amount.nextLine();
        String[] array = str.split(" ");
        // Перебираем каждый элемент массива
        for (String word : array) {
            if (isPalindrome(word)) {
                System.out.println("String " + word + " is palindrome!");
            } else {
                System.out.println("String " + word + " isn't palindrome!");
            }
        }
    }
    //Метод  reverseString(String s) переворачивает строку
    public static String reverseString(String s) {
        char[] array = s.toCharArray();
        String result = "";
        for (int i = array.length - 1; i >= 0; i--) {
            result = result + array[i];
        }
        return result;
    }
    //Метод isPalindrome(String s) проверяет является ли строка палиндромом
    public static boolean isPalindrome(String s) {
        return s.equals(reverseString(s));
    }
}
