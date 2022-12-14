package edu.labs.java2;

public class MainClass{
    public static void main(String[] args) {
        Point2d myOtherPoint = new Point2d ();
        System.out.println(Double.toString(myOtherPoint.getX()) + ", " +Double.toString(myOtherPoint.getY()));
        Point3d firstPoint = new Point3d();
        firstPoint.setX(5.6);
        firstPoint.setY(4);
        firstPoint.setZ(5.77);
        Point3d secondPoint = new Point3d(3.8, 4, 5.77);
        System.out.println(Double.toString(firstPoint.getX()) + ", " + Double.toString(firstPoint.getY()) + ", " + Double.toString(firstPoint.getZ()));
        System.out.println(Double.toString(secondPoint.getX()) + ", " + Double.toString(secondPoint.getY()) + ", " + Double.toString(secondPoint.getZ()));
        System.out.println(firstPoint.equals(secondPoint));
        double length3D = distanceTo(firstPoint,secondPoint);
        System.out.println("Расстояние между двумя точками:" + length3D);
    }
    public static double distanceTo(Point3d firstPoint, Point3d secondPoint) {
        double distance;
        double x;
        double y;
        double z;
        x = Math.pow((firstPoint.getX() - secondPoint.getX()), 2);
        y = Math.pow((firstPoint.getY() - secondPoint.getY()), 2);
        z = Math.pow((firstPoint.getZ() - secondPoint.getZ()), 2);
        distance = Math.sqrt(y + x + z);
        return distance;
    }
}
