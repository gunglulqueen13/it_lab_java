package edu.tasks.sixth;
import java.util.*;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import java.util.regex.Pattern;

public class Task6 {
    private static int c(int n, int k) {
        if (k == 0) return 1;
        if (k == 1) return n;
        if (k == n) return 1;
        return c(n - 1, k - 1) + c(n - 1, k);
    }

    private static int bell(int n) {
        if (n == 0) return 1;
        if (n == 1) return 1;
        int answer = 0;
        for (int i = 0; i < n; i++)
            answer += c(n - 1, i) * bell(i);
        return answer;
    }

    private static String translateWord(String word) {
        if (word.isEmpty()) return "";
        boolean isUpperCase = Character.isUpperCase(word.charAt(0));
        word = word.toLowerCase();

        String vowels = "eyuioa";
        boolean isStartsWithVowel = vowels.indexOf(word.charAt(0)) != -1;//если гласная

        if (!isStartsWithVowel && word.length() > 1) {
            while (vowels.indexOf(word.charAt(0)) == -1)
                word = word.substring(1) + word.charAt(0);
        }

        word = isStartsWithVowel ? word + "yay" : word + "ya";
        word = isUpperCase ? Character.toUpperCase(word.charAt(0)) + word.substring(1) : word;
        return word;
    }

    private static String translateSentence(String sentence) {
        StringBuilder answer = new StringBuilder();
        String word = "";
        for (char ch : sentence.toCharArray()) {
            if (Character.isLetter(ch)) word += ch;
            else {
                if (!word.isEmpty()) answer.append(translateWord(word));
                word = "";
                answer.append(ch);
            }
        }
        return answer.toString();
    }



    private static boolean validColor(String str) {
        if (!str.substring(0, 3).equals("rgb")) return false;
        boolean a = str.charAt(3) == 'a';
        boolean check = true;
        if (a) {
            String[] RGBA = str.substring(5, str.length() - 1).split(",");
            for(String item: RGBA) {
                if (item.length() == 0) {
                    return false;
                }
                Double item_double = Double.parseDouble(item);
                if (!(item_double >= 0 && item_double <= 255)) {
                    check = false;
                }
            }
        }
        else {
            String[] RGB = str.substring(4, str.length() - 1).split(",");
            for(String item: RGB) {
                if (item.length() == 0) {
                    return false;
                }
                Double item_double = Double.parseDouble(item);
                if (!(item_double >= 0 && item_double <= 255)) {
                    check = false;
                }
            }
        }
        return check;
    }

    private static String stripUrlParams(String url) {
        return stripUrlParams(url, new String[]{});
    }

    private static String stripUrlParams(String url, String[] removeParams) {
        String[] parts = url.split("\\?");
        String answer = parts[0];
        if (parts.length == 1) return answer;
        String[] paramsList = parts[1].split("&");
        HashMap<String, String> params = new HashMap<>();
        for (String param : paramsList)
            params.put(param.split("=")[0], param.split("=")[1]);
        answer += "?";
        for (String key : params.keySet()) {
            if (Arrays.asList(removeParams).contains(key)) continue;
            if (answer.charAt(answer.length() - 1) != '?') answer += "&";
            answer += key + "=" + params.get(key);
        }
        return answer;
    }

    private static ArrayList<String> getHashTags(String title) {
        title = title.replace(",", "");
        String[] words = title.split(" |,");
        Arrays.sort(words, Comparator.comparingInt(String::length).reversed());
        ArrayList<String> tags = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            if (i == 3) break;
            if (words[i].length() > 0)
                tags.add("#" + Character.toLowerCase(words[i].charAt(0)) + words[i].substring(1));
        }
        return tags;
    }

    public static int ulam(int n) {
        ArrayList<Integer> ulamNumbers = new ArrayList<>(Arrays.asList(1, 2));


        if (n == 1) {
            return ulamNumbers.get(0);
        }
        if (n == 2) {
            return ulamNumbers.get(1);
        }

        int nextUlam = 3;
        while (true){
            if (n == ulamNumbers.size()) {
                return ulamNumbers.get(n-1);
            }
            int count = 0;

            for (int j = 0; j < ulamNumbers.size() - 1; j++) {
                for (int k = j + 1; k < ulamNumbers.size(); k++) {
                    if (ulamNumbers.get(j) + ulamNumbers.get(k) == nextUlam) {
                        count++;
                    }
                    if (count > 1)
                        break;
                }
                if (count > 1)
                    break;
            }

            if (count == 1) {
                ulamNumbers.add(nextUlam);
            }

            nextUlam++;
        }
    }

    private static String longestNonrepeatingSubstring(String str) {
        String result = "";
        for (String item : str.split("(?!^)")) {
            if (result.indexOf(item) == -1) {
                result += item;
            }
        }
        return result;
    }

    public static String convertToRoman(int num) {
        String[] thousands = {"", "M", "MM", "MMM"};
        String[] hundreds = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] units = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        return thousands[num / 1000] + hundreds[(num % 1000) / 100] + tens[(num % 100) / 10] + units[num % 10];
    }

    public static boolean formula(String f) {
        String[] formulaParts = f.split("\s=\s");
        HashSet<Double> calculations = new HashSet<>();

        for (String part : formulaParts) {
            Expression e = new ExpressionBuilder(part).build();
            calculations.add(e.evaluate());
        }

        return calculations.size() == 1;
    }

    public static boolean palindromeDescendant(long num) {
        String s = String.valueOf(num);
        if (s.length() <= 1) {
            return false;
        }
        if(isPalindrome(s)) {
            return true;
        }
        return palindromeDescendant(
                Long.parseLong(
                        makeSumOfPairs(s)
                )
        );
    }
    public static boolean isPalindrome(String s) {
        return s.equals(new StringBuilder(s).reverse().toString());
    }

    public static String makeSumOfPairs(String num) {
        String[] digits = num.split("");
        ArrayList<String> newNum = new ArrayList<>();

        for(int i = 0; i < digits.length-1; i+=2){
            newNum.add(
                    String.valueOf(
                            Integer.parseInt(digits[i]) +
                                    Integer.parseInt(digits[i+1])
                    )
            );
        }

        return String.join("", newNum);
    }

    public static void main(String[] args) {
        System.out.println(bell(3));
        System.out.println(translateSentence("Do you think it is going to rain today?"));
        System.out.println(validColor("rgba(0,0,0,0.123456789)"));
        System.out.println(stripUrlParams("https://edabit.com", new String[]{"b"}));
        System.out.println(getHashTags("Visualizing Science"));
        System.out.println(ulam(206));
        System.out.println(longestNonrepeatingSubstring("abcda"));
        System.out.println(convertToRoman(16));
        System.out.println(formula("16 * 10 = 160 = 14 + 120"));
        System.out.println(palindromeDescendant(23336014));
    }
}
