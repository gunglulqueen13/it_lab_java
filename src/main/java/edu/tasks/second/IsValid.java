package edu.tasks.second;

public class IsValid {
    public static void main(String[] args) {
        System.out.println(IsValid("19001"));
    }
    public static boolean IsValid(String x) {
        if (x.length() == 5 && x.charAt(0) != '0') {
            int z = 0;

            for(int i = 0; i < x.length(); ++i) {
                int y = Integer.parseInt(Character.toString(x.charAt(i)));
                if (y >= 0 && y <= 9) {
                    ++z;
                }
            }

            if (z == 5) {
                return true;
            }
        }

        return false;
    }
}
