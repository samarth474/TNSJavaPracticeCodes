package com.samarth.assessment02;

import java.util.Scanner;

class Circle {

    double radius;
    String colour;
    Scanner sc = new Scanner(System.in);

    public void getInput() {

        radius = sc.nextDouble();
        sc.nextLine();
        colour = sc.nextLine();
    }

    public void calcArea() {
        double area = 3.14 * radius * radius;

        System.out.println("Radius : " + radius);
        System.out.println("Colour : " + colour);
        System.out.println("Area : " + area);
    }
}

public class Question03 {
    public static void main(String[] args) {

        Circle c = new Circle();
        c.getInput();
        c.calcArea();
    }
}