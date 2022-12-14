package edu.labs.java2;
public class Point2d {
        private double x;
        private double y;
        public Point2d ( double x, double y) {
            this.x = x;
            this.y = y;
        }
        public Point2d () {
//Вызовите конструктор с двумя параметрами и определите источник.
            this(0, 0);
        }
        public double getX () {
            return this.x;
        }
        public double getY () {
            return this.y;
        }
        public void setX ( double val) {
            this.x = val;
        }
        public void setY ( double val) {
            this.y = val;
        }
}
