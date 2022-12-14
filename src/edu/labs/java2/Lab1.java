package edu.labs.java2;

import java.util.Scanner;
public class Lab1 extends MainClass {
    public static void main(String[] args) {
        Point3d A = new Point3d();
        Point3d B = new Point3d();
        Point3d C = new Point3d();
        Scanner amount = new Scanner(System.in);
        System.out.println("Введите координаты x для точки A:");
        A.setX(amount.nextDouble());
        System.out.println("Введите координаты y для точки A:");
        A.setY(amount.nextDouble());
        System.out.println("Введите координаты z для точки A:");
        A.setZ(amount.nextDouble());
        System.out.println("Точка A имеет координаты: (" + Double.toString(A.getX()) + ", " + Double.toString(A.getY()) + ", " + Double.toString(A.getZ())+ ")");

        System.out.println("Введите координаты x для точки B:");
        B.setX(amount.nextDouble());
        System.out.println("Введите координаты y для точки B:");
        B.setY(amount.nextDouble());
        System.out.println("Введите координаты z для точки B:");
        B.setZ(amount.nextDouble());
        System.out.println("Точка B имеет координаты: (" + Double.toString(B.getX()) + ", " + Double.toString(B.getY()) + ", " + Double.toString(B.getZ())+ ")");

        System.out.println("Введите координаты x для точки C:");
        C.setX(amount.nextDouble());
        System.out.println("Введите координаты y для точки C:");
        C.setY(amount.nextDouble());
        System.out.println("Введите координаты z для точки C:");
        C.setZ(amount.nextDouble());
        System.out.println("Точка C имеет координаты: (" + Double.toString(C.getX()) + ", " + Double.toString(C.getY()) + ", " + Double.toString(C.getZ())+ ")");
        System.out.println();
        if (A.equals(B) || A.equals(C) || B.equals(C)) {
            System.out.println("Две из 3 точек равны, фигура не равняется треугольнику");
        }
        else {
            System.out.println("Площадь треугольника из заданных точек равна: " + computeArea(A, B, C));
        }
    }
    public static double computeArea(Point3d A, Point3d B, Point3d C){
        double Ab = distanceTo(A,B);
        double Bc = distanceTo(B,C);
        double Ca = distanceTo(C,A);
        double halfMeter = (Ab+Bc+Ca)/2;
        double squareArea = Math.sqrt(halfMeter * (halfMeter - Ab) * (halfMeter - Bc) * (halfMeter - Ca));
        return squareArea;
    }
}
