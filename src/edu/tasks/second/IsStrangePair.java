package edu.tasks.second;

public class IsStrangePair {
    public static void main(String[] args) {
        System.out.println(IsStrangePair("radio", "orator"));
    }
    public static boolean IsStrangePair(String s1, String s2) {
        if (s1.length() == 0 && s2.length() == 0) {
            return true;
        } else {
            return s1.charAt(0) == s2.charAt(s2.length() - 1) && s1.charAt(s1.length() - 1) == s2.charAt(0);
        }
    }
}
