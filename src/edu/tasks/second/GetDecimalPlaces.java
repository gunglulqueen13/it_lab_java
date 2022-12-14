package edu.tasks.second;

public class GetDecimalPlaces {
    public static void main(String[] args) {
        System.out.println(GetDecimalPlaces("43.20"));
    }
    public static int GetDecimalPlaces(String x) {
        int result;
        if (x.indexOf(46) == -1) {
            result = 0;
        } else {
            result = x.length() - x.indexOf(46) - 1;
        }

        return result;
    }
}
