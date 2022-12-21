package edu.labs.java6;

public class Complex {
    private final double re;
    private final double im;

    public Complex(double real, double imag) {
        this.re = real;
        this.im = imag;
    }

    public Complex() {
        this.re = 0;
        this.im = 0;
    }

    public Complex plus(Complex z) {
        return new Complex(this.re + z.re, this.im + z.im);
    }

    public Complex square() {
        return new Complex(
                this.re * this.re - this.im * this.im,
                2 * this.re * this.im
        );
    }

    public boolean isGreater(Complex z){
        return this.re * this.re + this.im * this.im > z.re * z.re + z.im * z.im;
    }

    public Complex conjugate(){
        return new Complex(this.re, -this.im);
    }

    public Complex abs(){
        return new Complex(Math.abs(this.re), Math.abs(this.im));
    }
}
