package com.samarth.assessment04;

import java.util.Scanner;

public class Question04 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String str = sc.nextLine();

        String remaining = "";
        String uppercase = "";

        for (int i = 0; i < str.length(); i++) {

            char ch = str.charAt(i);

            if (Character.isUpperCase(ch)) {
                uppercase = uppercase + ch;
            } else {
                remaining = remaining + ch;
            }
        }

        System.out.println(remaining + uppercase);

        sc.close();
    }
}