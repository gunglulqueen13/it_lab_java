package edu.tasks.fourth;

import java.util.*;

public class Task4 {
        public static String bessie(int n, int k, String text){
            StringBuilder fin = new StringBuilder(text);
            int index = 0;
            for (int i = 0; i < fin.length(); i++) {
                if (fin.charAt(i) == ' ' && fin.substring(index,i+1).length() >= k){
                    fin.replace(i, i+1,"\n");
                    index = i;
                }
            }
            return fin.toString();
        }

        public static Object[] split(String string){
            int open = 0;
            int close = 0;
            ArrayList<String> fin = new ArrayList<>();
            int end_ind = 0;
            for (int i = 0; i < string.length(); i++) {
                if (string.charAt(i) == '(') open += 1;
                else if (string.charAt(i) == ')') close += 1;
                if (open == close) {
                    fin.add(string.substring(end_ind, i + 1));
                    end_ind = i+1;
                }
            }
            return fin.toArray();
        }

        public static String toCamelCase(String string){
            StringBuilder fin = new StringBuilder(string);
            for (int i = 0; i < fin.length(); i++) {
                if (fin.charAt(i) == '_') fin.replace(i, i+2, Character.toString(fin.charAt(i+1)).toUpperCase());
            }
            return fin.toString();
        }

        public static String toSnakeCase(String string){
            StringBuilder fin = new StringBuilder(string);
            for (int i = 0; i < fin.length(); i++) {
                if (Character.toString(fin.charAt(i)).matches("[A-Z]")) fin.replace(i, i+1, "_" + Character.toString(fin.charAt(i)).toLowerCase());
            }
            return fin.toString();
        }

        public static String overTime(double st_time, double end_time, double m_h, double coef){
            if (end_time <= 17){
                return "$" + String.format("%.2f", (end_time - st_time) * m_h);
            }
            else return "$" + String.format("%.2f", ((17 - st_time) * m_h + (end_time - 17) * m_h * coef));
        }

        public static String BMI(String weight, String height){
            double w = 0;
            double h = 0;
            for (int i = 0; i < weight.length(); i++) {
                if (weight.charAt(i) == ' '){
                    if (weight.endsWith("pounds")) w = Double.parseDouble(weight.substring(0, i)) / 2.205;
                    else w = Double.parseDouble(weight.substring(0, i));
                }
            }
            for (int i = 0; i < height.length(); i++) {
                if (height.charAt(i) == ' '){
                    if (height.endsWith("inches")) h = Double.parseDouble(height.substring(0, i)) / 39.37;
                    else h = Double.parseDouble(height.substring(0, i));

                }
            }
            double res = w / (h*h);
            if (res < 18.5) return String.format("%.1f", res) + " Underweight";
            else if (res >= 18.5 && res <= 24.9) return String.format("%.1f", res) + " Normal weight";
            else return String.format("%.1f", res) + " Overweight";
        }
        private static int bugger(int n){
            if (n < 10) return 0;
            int m = 1;
            while (n > 0){
                m *= n % 10;
                n = (n - n % 10) / 10;
            }
            return bugger(m) + 1;
        }

        public static String toStarShorthand(String a){     //звездная стенография
            if (a.isEmpty())
                return "''";
            String result = "";
            int k = 1;
            char rez1 = a.charAt(0);
            a += ' ';
            for (int i=1; i<a.length();i++){
                char rez2=a.charAt(i);
                if (rez2 == rez1)
                    k += 1;
                else {
                    result += rez1;
                    if (k > 1)
                        result += "*" + k;
                    k = 1;
                    rez1 = rez2;
                }
            }
            return result;
        }

        private static String getVowels(String sentence){
            String[] words = sentence.split(" ");
            String last_word = words[words.length - 1].toLowerCase();
            return last_word.replaceAll("[^eyuioa]", "");
        }

        private static boolean doesRhyme(String first, String second){
            return Objects.equals(getVowels(first), getVowels(second));
        }
        static public boolean trouble(long a, long b){      //цифра повторяестся три раза подряд и два раза подряд
            String s1 = Long.toString(a);
            String s2 = Long.toString(b);
            long [] arr1 = new long[s1.length()];
            long [] arr2 = new long [s2.length()];
            for (int i = s1.length() - 1; i >= 0; i--) {
                arr1[i] = a % 10;
                a /= 10;
            }
            for (int i = s2.length() - 1; i >= 0; i--) {
                arr2[i] = b % 10;
                b /= 10;
            }
            for (int i=2; i<s1.length();i++){
                for (int j=1; j<s2.length();j++){
                    if(arr1[i]==arr1[i-1]&&arr1[i-1]==arr1[i-2]&&arr1[i]==arr2[j]&&arr1[i-2]==arr2[j-1]){
                        return true;
                    }
                }
            }
            return false;

        }

        public static int countUniqueBooks(String stringSequence, Character bookEnd){
            StringBuilder str = new StringBuilder(stringSequence);
            HashSet<Character> set = new HashSet<>();
            String substr = new String();
            int index = -1;
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == bookEnd){
                    if (index == -1) index = i;
                    else {
                        substr = str.substring(index + 1, i);
                        System.out.println(str.substring(index + 1, i));
                        for (int j = 0; j < substr.length(); j++) {
                            set.add(substr.charAt(j));
                        }
                        index = -1;
                    }
                }
            }
            return set.size();
        }


        public static void main(String[] args) {
            System.out.println(bessie(10,7, "hello000 my name"));
            System.out.println(Arrays.toString(split("((())())(()(()()))")));
            System.out.println(toCamelCase("hello_edabit"));
            System.out.println(toSnakeCase("helloEdabit"));
            System.out.println(overTime(13.25, 15, 30, 1.5));
            System.out.println(BMI("106 ", "1 meters"));
            System.out.println(bugger(39));
            System.out.println(toStarShorthand(("abbccc")));
            System.out.println(doesRhyme("Sam I am!", "Green eggs and ham."));
            System.out.println(trouble(53242225, 452322));
            System.out.println(countUniqueBooks("AZYWABBCATTTA", 'A'));
        }


}
