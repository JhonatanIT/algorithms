package com.example.algorithms;

//JAVA 17

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExerciseLordOfTheRings {

    public static void main(String[] args) {

        lordOfTheRings("""
                10
                -5 -3 23 -3232 -45 0 5 5 10 -5""");
    }

    private static void lordOfTheRings(String input) {

        String[] inputLines = input.split("\n");

        if (inputLines.length != 2) {
            System.out.println("Input needs 2 lines");
            return;
        }

        String[] firstLine = inputLines[0].trim().split(" ");
        String[] potential = inputLines[1].trim().split(" ");

        if (firstLine.length != 1) {
            System.out.println("First line require 1 param: number of wrestlers");
            return;
        }

        int n = Integer.parseInt(firstLine[0]);

        if (n <= 0) {
            System.out.println("number of wrestlers must be positive");
            return;
        }

        if (potential.length != n) {
            System.out.printf("Second line require %s wrestlers", n);
            return;
        }

        List<Integer> potentialList = Arrays.stream(potential).map(Integer::parseInt).toList();
        getSolution(potentialList, n);
    }

    private static void getSolution(List<Integer> potentialList, int n) {
        List<Integer> tempList = new ArrayList<>();
        boolean skipNegatives = false;

        for (int i = 0; i < n; i++) {
            if (potentialList.get(i) >= 0) {
                skipNegatives = true;
                tempList.add(potentialList.get(i));
            } else if (!skipNegatives) {
                tempList.add(potentialList.get(i));
            }
        }

        System.out.println("Lords of The Ring: ");
        System.out.println(tempList);
    }

}
