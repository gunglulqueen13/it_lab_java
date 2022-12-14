package edu.labs.java5;

import java.awt.geom.Rectangle2D;

public class BurningShip extends FractalGenerator{
    public static final int MAX_ITERATIONS = 2000;

    @Override
    public void getInitialRange(Rectangle2D.Double range) {
        range.x = -2;
        range.y = -2.5;
        range.width = 4;
        range.height = 4;
    }

    @Override
    public int numIterations(double x, double y) {
        Complex z = new Complex();
        Complex c = new Complex(x, y);
        for (int i = 0; i < MAX_ITERATIONS; i++){
            z = z.abs().square().plus(c);
            if (z.isGreater(new Complex(2, 0))) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString(){
        return "Burning Ship";
    }
}
