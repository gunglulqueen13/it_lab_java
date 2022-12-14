package edu.tasks.fitfh;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;



public class Task5 {
    public static String encrypt(String text){
        String [] words = new String[text.length()];
        String [] words2 = new String[text.length()];
        words[0] = String.valueOf((int)(text.charAt(0)));
        for (int i = 0; i < text.length(); i++){
            words[i] = String.valueOf((int)(text.charAt(i)));
        }
        words2[0] = words[0];
        for (int j = 1; j < text.length(); j++){
            words2[j] = String.valueOf(Integer.parseInt(String.valueOf(words[j])) - Integer.parseInt(String.valueOf(words[j - 1])));
        }
        return String.join(", ", words2);
    }

    public static String decrypt(int[] a){      //кодироване слов из чисел в слова
        String result ="";
        char x = 0;

        for (int i=0; i<a.length; i++){
            if (i==0){
                x = (char) a[i];
                result+=x;
            }else{
                x=(char)(x+a[i]);

                result+=x;
            }
        }
        return result;
    }
    private static boolean canMove(String type, String start, String end) {
        char wStart = start.charAt(0);
        int hStart = start.charAt(1);
        char wEnd = end.charAt(0);
        int hEnd = end.charAt(1);

        int deltaW = Math.abs(wStart - wEnd);
        int deltaH = Math.abs(hStart - hEnd);

        switch (type) {
            case ("King"):
                if ((deltaW == 0 || deltaW == 1) || (deltaH == 0 || deltaH == 1)) return true;
                break;
            case ("Rook"):
                if (deltaW == 0 || deltaH == 0) return true;
                break;
            case ("Bishop"):
                if (deltaW == deltaH) return true;
                break;
            case ("Queen"):
                if (deltaW == 0 || deltaH == 0 || deltaW == deltaH) return true;
                break;
            case ("Knight"):
                if ((deltaW == 2 && deltaH == 1) || (deltaW == 1 && deltaH == 2)) return true;
                break;
            case ("Pawn"):
                if (hEnd - hStart == 1 || hEnd - hStart == 2) return true;
                break;
        }
        return false;
    }
    public static boolean canComplete(String a, String b){      //набор букв идет по порядку в целом слове
        int x=0;
        for (int i=0; i<b.length();i++){
            if(b.charAt(i)==a.charAt(x)){
                x++;
            }
        }
        if (x==a.length())
            return true;
        return false;
    }
    //спрэд
    private static int sumDigProd(int... numbers) {
        int n = Arrays.stream(numbers).sum();
        while (n > 10) {
            int newN = 1;
            while (n > 0) {
                newN *= n % 10;
                n = (n - n % 10) / 10;
            }
            n = newN;
        }
        return n;
    }

    private static ArrayList<String> sameVowelGroup(String[] words) {
        ArrayList<String> answer = new ArrayList<>();
        String vowels = "eyuioa";
        String missingVowels = "";
        for (char ch : vowels.toCharArray()) {
            if (words[0].indexOf(ch) == -1) {
                missingVowels += ch;
            }
        }

        for (String word : words) {
            boolean flag = true;
            for (char ch : missingVowels.toCharArray()) {
                if (!(word.indexOf(ch) == -1)) {
                    flag = false;
                }
            }
            if (flag) answer.add(word);
        }
        return answer;
    }
    private static boolean validateCard(Long num) {
        int checkDigit = (int) (num % 10);
        num = (num - num % 10) / 10;
        String numReversed = new StringBuffer(String.valueOf(num)).reverse().toString();
        int[] digitArray = new int[numReversed.length()];
        for (int i = 0; i < numReversed.length(); i++) {
            int digit = numReversed.charAt(i);
            if (i % 2 == 0) {
                digit *= 2;
                if (digit > 10) {
                    digit = (digit % 10) + ((digit - digit % 10) / 10);
                }
            }
            digitArray[i] = digit;
        }
        int sum = Arrays.stream(digitArray).sum();
        return 10 - sum % 10 == checkDigit;
    }
    private static String numToEng(int num) {
        if (num == 0) return "zero";

        String answer = "";
        int hundreds = num / 100;
        int tens = num % 100 / 10;
        int units = num % 10;
        String[][] numbers = new String[][]{new String[]{"zero", "one", "two", "three", "fore", "five", "six", "seven", "eight", "nine"},
                new String[]{"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"},
                new String[]{"", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"}
        };


        if (hundreds > 0) answer += numbers[0][hundreds] + " hundred ";

        if (tens == 1) {
            answer += numbers[1][units];
            return answer;
        }

        if (tens > 1) answer += numbers[2][units] + " ";
        if (units > 0) answer += numbers[0][units];
        return answer;
    }

    private static String getSha256Hash(String str) {
        return Hashing.sha256().hashString(str, StandardCharsets.UTF_8).toString();
    }


    private static String correctTitle(String title) {
        String[] words = title.split(" ");
        String newTitle = "";
        for (String word : words) {
            word = word.toLowerCase();
            if (!(word.equals("and") || word.equals("the") || word.equals("of") || word.equals("in"))) {
                char firstChar = Character.toUpperCase(word.charAt(0));
                word = firstChar + word.substring(1);
            }
            newTitle += word + " ";
        }
        return newTitle;
    }
    private static String hexLattice(int size) {
        int n = 1;
        int i = 1;
        while (n < size) {
            n += 6 * i;
            i++;
        }
        if (n != size) return "Invalid";
        String answer = "";
        int stringLen = i * 4 - 3;
        System.out.println(stringLen);
        for (int j = 0; j < i; j++) {
            answer = drowLine(i, answer, stringLen, j);
        }
        for (int j = i - 2; j >= 0; j--) {
            answer = drowLine(i, answer, stringLen, j);
        }

        return answer;
    }

    private static String drowLine(int i, String answer, int stringLen, int j) {
        int spaces = (stringLen - (i + j) * 2 + 1) / 2;
        for (int k = 0; k < spaces; k++) answer += " ";
        for (int k = 0; k < i + j; k++) answer += "o ";
        answer += "\n";
        return answer;
    }


    public static void main(String[] args){
        System.out.println(encrypt("Hello"));
        System.out.println(decrypt(new int[]{ 72, 33, -73, 84, -12, -3, 13, -13, -68 }));
        System.out.println(canMove("Rook", "A8", "H8"));
        System.out.println(canComplete("butl", "beautiful"));
        System.out.println(sumDigProd(1, 2, 3, 4, 5, 6));
        System.out.println(sameVowelGroup(new String[]{"toe", "ocelot", "maniac"}));
        System.out.println(validateCard(1234567890123456L));
        System.out.println(numToEng(909));
        System.out.println(correctTitle("TYRION LANNISTER, HAND OF THE QUEEN."));
        System.out.println(hexLattice(7));

    }
}
