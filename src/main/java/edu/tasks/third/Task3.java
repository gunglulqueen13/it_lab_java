package edu.tasks.third;

import java.util.Arrays;
import java.util.HashSet;
public class Task3 {

        public static int solutions(int a, int b, int c){
            int d = b*b - 4*a*c;
            if (d < 0) return 0;
            else if (d == 0) return 1;
            else return 2;
        }

        public static int findZip(String str){
            int first_index = str.indexOf("zip"); //получаем индекс первого вхождения zip
            if (first_index != -1) { //если найдено первое вхождение, идем дальше, иначе zip нет в строке
                //строка one zip test  test
                str = str.substring(first_index + 2); //срезаем часть строки до zip включительно
                int second_index = str.indexOf("zip"); //ищем второе вхождение
                if (second_index != -1) {
                    return  second_index + first_index + 2;
                    //если найдено, прибавляем количество символов,
                    // которые мы срезали (включая zip) и индекс второго вхождения zip
                }
                return -1;
            }
            return -1;
        }

        public static boolean checkPerfect(int n){ 
            int i = 0;
            for (int j = 1; j <= n/2; j++) {
                if (n % j == 0) i += j;
            }
            if (n == i) return true;
            else return false;
        }

        public static String flipEndChars(String string){
            if (string.length() < 2) return "Incompatible";
            else if (string.charAt(0) == string.charAt(string.length()-1)) return "Two's a pair";
            else {
                StringBuilder str = new StringBuilder(string);
                char last = str.charAt(str.length() - 1);
                str.setCharAt(str.length() - 1, str.charAt(0));
                str.setCharAt(0, last);
                return str.toString();
            }
        }

        public static boolean isValidHexCode(String code){
            return  (code.charAt(0) == '#' && code.length() == 7
                    && code.substring(1).matches("[a-fA-F[0-9]]+"));
        }

        public static boolean same(int[] arr1, int[] arr2){
            HashSet<Integer> set = new HashSet<>();
            for (int i = 0; i < arr1.length; i++) {
                set.add(arr1[i]);
            }
            int n = set.size();
            set.clear();
            for (int i = 0; i < arr2.length; i++) {
                set.add(arr2[i]);
            }
            if (n == set.size()) return true;
            else return false;
        }

        public static boolean isKaprekar(int n){
            if (n*n < 10 && n * n == n) return true;
            else{
                int beginIndex = Integer.toString(n * n).length() / 2;
                if (Integer.parseInt(Integer.toString(n*n).substring(0, beginIndex))
                        + Integer.parseInt(Integer.toString(n*n).substring(beginIndex))
                        == n) return true;
            }
            return false;
        }

        public static String longestZero(String str){
            int l = 1;
            int longest = 0;
            for (int i = 1; i < str.length(); i++) {
                if (str.charAt(i-1) == str.charAt(i) && str.charAt(i) == '0'){
                    l += 1;
                    if (l > longest) longest = l;
                }
                else l = 1;
            }
            return "0".repeat(longest);
        }

        public static int nextPrime(int n){
            boolean flag = true;
            for (int i = 2; i <= Math.sqrt(n); i++) {
                if (n % i == 0){
                    flag = false;
                    break;
                }
            }
            if (flag == true) return n;
            int num = n;
            while (true){
                num++;
                flag = true;
                for (int i = 2; i <= Math.sqrt(num); i++) {
                    if (num % i == 0){
                        flag = false;
                        break;
                    }
                }
                if (flag == true) return num;
            }
        }

    public static boolean rightTriangle(int a, int b, int c){ //task10
        if (a==0 || b==0 || c==0){
            return false;
        }
        if ((a*a==b*b+c*c) || (b*b==a*a+c*c) ||(c*c==a*a+b*b)) {
            return true;
        }
        return false;

    }


    public static void main(String[] args) {
            System.out.println("1  - " + solutions(1, 0, 1));
            System.out.println("2  - " + findZip("all zip files are Zipped"));
            System.out.println("3  - " + checkPerfect(28));
            System.out.println("4  - " + flipEndChars("Cat, dog, and mouse."));
            System.out.println("5  - " + isValidHexCode(("#CD5C&C")));
            System.out.println("6  - " + same(new int[]{1, 3, 4, 4, 4}, new int[]{2, 5, 7}));
            System.out.println("7  - " + isKaprekar(297));
            System.out.println("8  - " + longestZero("01100001011000"));
            System.out.println("9  - " + nextPrime(24));
            System.out.println("10 - " + rightTriangle(145,105,100));
        }

}
