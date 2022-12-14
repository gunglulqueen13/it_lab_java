package edu.tasks.second;

public class BoxSeq {
    public static void main(String[] args) {
        System.out.println(BoxSeq(5));
    }
    public static int BoxSeq(int x) {
        int rez = 0;
        if (x == 0) {
            rez = 0;
        }

        for(int i = 1; i <= x; ++i) {
            if (i % 2 == 1) {
                rez += 3;
            } else {
                --rez;
            }
        }

        return rez;
    }
}
