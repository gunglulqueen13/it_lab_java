package edu.tasks.sixth;
import java.util.*;
import java.util.regex.Pattern;

public class Task6 {
    public static void main(String[] args) {
        //ex1
        System.out.println("----------ex1----------");
        System.out.println(bell(1));
        System.out.println(bell(2));
        System.out.println(bell(3));

        //ex2
        System.out.println("----------ex2----------");
        System.out.println(translateWord("flag"));
        System.out.println(translateWord("Apple"));
        System.out.println(translateWord("button"));
        System.out.println(translateWord(""));
        System.out.println(translateSentence("I like to eat honey waffles."));
        System.out.println(translateSentence("Do you think it is going to rain today?"));

        //ex3
        System.out.println("----------ex3----------");
        System.out.println(validColor("rgb(0,0,0)"));
        System.out.println(validColor("rgb(0,,0)"));
        System.out.println(validColor("rgb(255,256,255)"));
        System.out.println(validColor("rgba(0,0,0,0.123456789)"));

        //ex4
        System.out.println("----------ex4----------");
        System.out.println(stripUrlParams("https://edabit.com?a=1&b=2&a=2"));
        System.out.println(stripUrlParams("https://edabit.com?a=1&b=2&a=2", new String[]{"b"}));
        System.out.println(stripUrlParams("https://edabit.com", new String[]{"b"}));

        //ex5
        System.out.println("----------ex5----------");
        System.out.println(getHashTags("How the Avocado Became the Fruit of the Global Trade"));
        System.out.println(getHashTags("Why You Will Probably Pay More for Your Christmas Tree This Year"));
        System.out.println(getHashTags("Hey Parents, Surprise, Fruit Juice Is Not Fruit"));
        System.out.println(getHashTags("Visualizing Science"));

        //ex6 TODO
        System.out.println("----------ex6----------");
        System.out.println(ulam(4));
        System.out.println(ulam(9));
        System.out.println(ulam(206));

        //ex7
        System.out.println("----------ex7----------");
        System.out.println(longestNonrepeatingSubstring("abcabcbb"));
        System.out.println(longestNonrepeatingSubstring("aaaaaa"));
        System.out.println(longestNonrepeatingSubstring("abcde"));
        System.out.println(longestNonrepeatingSubstring("abcda"));

        //ex8 TODO
        System.out.println("----------ex8----------");
        System.out.println(convertToRoman(2));
        System.out.println(convertToRoman(12));
        System.out.println(convertToRoman(16));

        //ex9
        System.out.println("----------ex9----------");
        System.out.println(formula("6 * 4 = 24"));
        System.out.println(formula("18 / 17 = 2"));
        System.out.println(formula("16 * 10 = 160 = 14 + 120"));

        //ex10
        System.out.println("----------ex10---------");
        System.out.println(palindromedescendant(11211230));
        System.out.println(palindromedescendant(13001120));
        System.out.println(palindromedescendant(23336014));
        System.out.println(palindromedescendant(11));
    }

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
        boolean isStartsWithVowel = vowels.indexOf(word.charAt(0)) != -1;

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

    private static int ulam(int n) {
        n--;
        ArrayList<Integer> ulam = new ArrayList<>();
        ulam.add(1);
        ulam.add(2);
        for (int i = 0; i < n; i++) {
            ArrayList<Integer> sum = (ArrayList<Integer>) ulam.clone();
            ArrayList<Integer> uniq = new ArrayList<>();
            for (int j = 0; j < ulam.size() - 1; j++)
                for (int k = j + 1; k < ulam.size(); k++) {
                    int newSum = ulam.get(j) + ulam.get(k);
                    if (sum.contains(newSum)) {
                        int index = uniq.indexOf(newSum);
                        if (index != -1) uniq.remove(index);
                    } else {
                        sum.add(newSum);
                        uniq.add(newSum);
                    }
                }
            ulam.add(Collections.min(uniq));
        }
        return ulam.get(n);
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

    private static String convertToRoman(int n) {
        int[] romanKeys = new int[]{1000, 500, 100, 50, 10, 5, 1};
        Character[] romanValues = new Character[]{'M', 'D', 'C', 'L', 'X', 'V', 'I'};

        String answer = "";
        for (int i = 0; i < romanKeys.length; i++) {
            int k = n / romanKeys[i];
            if (k < 4) {
                for (int j = 0; j < k; j++)
                    answer += romanValues[i];
            } else if (k == 4) {
                answer += romanValues[i];
                answer += romanValues[i - 1];
            }
            n -= k * romanKeys[i];
        }

        return answer;
    }

    private static boolean formula(String formula) {
        formula = formula.replace(" ", "");
        String[] expressions = formula.split("=");
        ArrayList<Integer> results = new ArrayList<>();
        for (String expression : expressions) {
            if (expression.indexOf("+") > 0)
                results.add(Integer.parseInt(expression.split("\\+")[0]) + Integer.parseInt(expression.split("\\+")[1]));
            else if (expression.indexOf("-") > 0)
                results.add(Integer.parseInt(expression.split("-")[0]) - Integer.parseInt(expression.split("-")[1]));
            else if (expression.indexOf("*") > 0)
                results.add(Integer.parseInt(expression.split("\\*")[0]) * Integer.parseInt(expression.split("\\*")[1]));
            else if (expression.indexOf("/") > 0)
                results.add(Integer.parseInt(expression.split("/")[0]) / Integer.parseInt(expression.split("/")[1]));
            else
                results.add(Integer.parseInt(expression));
        }
        for (int i = 0; i < results.size() - 1; i++) {
            if (results.get(i) != results.get(i + 1)) return false;
        }
        return true;
    }

    private static boolean palindromedescendant(int n) {
        ArrayList<Integer> digits = new ArrayList<>();
        while (n > 0) {
            digits.add(n % 10);
            n = n / 10;
        }
        return palindromedescendant(digits);
    }

    private static boolean palindromedescendant(ArrayList<Integer> digits) {
        if (digits.size() == 1) return false;
        boolean isPalindrome = true;
        for (int i = 0; i < digits.size() / 2; i++)
            if (digits.get(i) != digits.get(digits.size() - i - 1)) {
                isPalindrome = false;
                break;
            }
        if (isPalindrome) return true;
        ArrayList<Integer> newDigits = new ArrayList<>();
        for (int i = 0; i < digits.size() - 1; i += 2) {
            int n = digits.get(i) + digits.get(i + 1);
            if (n > 10) {
                newDigits.add(n / 10);
                newDigits.add(n % 10);
            } else
                newDigits.add(n);
        }
        return palindromedescendant(newDigits);
    }
}
