package com.example.demo.calc;

public class AddOperation {

    private double a, b;

//    public AddOperation(double a, double b) {
//        this.a = a;
//        this.b = b;
//    }

    public double add(double a, double b) {
        return a + b;
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
