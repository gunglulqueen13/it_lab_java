package edu.labs.java4;

public class ComplexNumber {
        private double realNumber;
        private double imaginaryUnit;

        public ComplexNumber(double realNumber, double imaginaryUnit){
            this.realNumber = realNumber;
            this.imaginaryUnit = imaginaryUnit;
        }

        public double getRealNumber(){
            return this.realNumber;
        }

        public double getImaginaryUnit(){
            return this.imaginaryUnit;
        }

        public double getModulusSquared(){
            return realNumber*realNumber + imaginaryUnit*imaginaryUnit;
        }

        public ComplexNumber getSquare(){
            double newRealNumber = realNumber*realNumber - imaginaryUnit*imaginaryUnit;
            double newImaginaryUnit = 2*realNumber*imaginaryUnit;
            return new ComplexNumber(newRealNumber, newImaginaryUnit);
        }

        public ComplexNumber add(ComplexNumber complexNumber){
            this.realNumber += complexNumber.getRealNumber();
            this.imaginaryUnit += complexNumber.getImaginaryUnit();
            return this;
        }


}
