package com.example.demo.calc;

public class SubtractOperation {

    private double a, b;

//    public SubtractOperation(double a, double b) {
//        this.a = a;
//        this.b = b;
//    }

    public double subtract(double a, double b) {
        return a - b;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }
}
