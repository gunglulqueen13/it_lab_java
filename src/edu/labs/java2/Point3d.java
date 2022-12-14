package edu.labs.java2;

public class Point3d extends Point2d {
    private double z;

    public Point3d(double x, double y, double z) {
        super(x,y);
        this.z = z;
    }
    public Point3d(){
        this(0.0, 0.0, 0.0);
    }
    public double getZ () {
        return this.z;
    }
    public void setZ ( double val) {
        this.z = val;
    }
    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (!(obj instanceof Point3d)) return false;

        Point3d point3d = (Point3d) obj;

        if (Double.compare(point3d.getX(), getX()) != 0) return false;
        if (Double.compare(point3d.getY(), getY()) != 0) return false;
        return Double.compare(point3d.getZ(), getZ()) == 0;

    }
}
