package edu.labs.java1;
import java.util.Scanner;

public class Primes {
    //Метод main() - создаёт массив с рандомными числами и проходится
    // по нему циклом, используя функцию isPrime.
    public static void main(String[] args) {
        int[] mas;
        int n;
        Scanner amount = new Scanner(System.in);
        System.out.print("Enter numbers of array:");
        n = amount.nextInt();
        mas = new int [n];
        for (int i = 0; i < n; i++){
            double x = (Math.random() * ((100 - 2) + 1)) + 2;
            mas[i] = (int) x;
        }
        for (int j = 0; j < n; j++){
           if (isPrime(mas[j])) {
               System.out.println(mas[j] + " Является простым");
           }
           else {
               System.out.println(mas[j] + " Не является простым");
           }

        }
    }
    //Функция isPrime(int m) проверяет аргумент на простое число, аргумент
    //сгенерирован в методе main
    public static boolean isPrime(int m)
    {
        for (int i = 2; i < m; i++){
            if ((m % i) == 0)
                return false;
        }
        return true;
    }
}
