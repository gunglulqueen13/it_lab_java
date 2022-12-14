package edu.tasks.second;

public class IsPrefixSuffix {
    public static void main(String[] args) {
        System.out.println(IsPrefix("automation", "auto-"));
        System.out.println(IsSuffix("arachnophobia", "-phobia"));
    }
    public static boolean IsPrefix(String s1, String s2) {
        String str = "";

        for(int i = 0; i < s2.length() - 1; ++i) {
            str = str + s2.charAt(i);
        }

        return s1.startsWith(str);
    }
    public static boolean IsSuffix(String s1, String s2) {
        String str = "";

        for(int i = 1; i < s2.length(); ++i) {
            str = str + s2.charAt(i);
        }

        return s1.endsWith(str);
    }
}
