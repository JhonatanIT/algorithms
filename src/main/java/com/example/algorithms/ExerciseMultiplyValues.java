package com.example.algorithms;

//JAVA 17

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public class ExerciseMultiplyValues {

    public static void main(String[] args) {

        multiplyValues("""
                12 3
                2 3 4""");
    }

    private static void multiplyValues(String input) {

        String[] inputLines = input.split("\n");

        if (inputLines.length != 2) {
            System.out.println("Input needs 2 lines");
            return;
        }

        String[] firstLine = inputLines[0].trim().split(" ");
        String[] secondLine = inputLines[1].trim().split(" ");

        if (firstLine.length != 2) {
            System.out.println("First line require 2 params");
            return;
        }

        int n = Integer.parseInt(firstLine[0]);
        int k = Integer.parseInt(firstLine[1]);

        if (n <= 0 || k <= 0) {
            System.out.println("n y k must be positive");
            return;
        }

        if (secondLine.length != k) {
            System.out.printf("Second line require %s elements", k);
            return;
        }

        List<Integer> integerList = Arrays.stream(secondLine).sorted().map(Integer::parseInt).toList();  //order lexicographically
        getMinimalSolution(integerList, n);
    }


    private static void getMinimalSolution(List<Integer> integerList, int n) {

        IntStream.range(0, integerList.size() - 1)
                .mapToObj(i -> getIndividualSolution(integerList, n, i, i, new ArrayList<>()))      //Get all individual solutions
                .filter(x -> !x.contains(-1))                                               //No "-1"
                .min(Comparator.comparingInt(List::size))                                   //Get solution with minimal size
                .ifPresentOrElse(System.out::println, () -> {
                    System.out.println(List.of(-1));                                    //I can't find a solution
                });
    }

    private static List<Integer> getIndividualSolution(List<Integer> integerList, int n, int variablePosition, int initialPosition, List<Integer> solutionDetailList) {

        int divisor = integerList.get(variablePosition);

        if (n == 1) {
            solutionDetailList.add(0, 1);           //Add the first element -> 1
//            System.out.println(solutionDetailList.toString());
            return solutionDetailList;
        } else {
            if (n % divisor == 0) {
                //Add divisor in the possible solution
                solutionDetailList.add(divisor);
                solutionDetailList = getIndividualSolution(integerList, n / divisor, variablePosition, initialPosition, solutionDetailList);

            } else if (variablePosition + 1 != initialPosition && variablePosition + 1 < integerList.size()) {
                //search more divisors in other positions
                solutionDetailList = getIndividualSolution(integerList, n, variablePosition + 1, initialPosition, solutionDetailList);

            } else if (variablePosition + 1 != initialPosition && initialPosition != 0) {
                //search more divisors in other positions
                solutionDetailList = getIndividualSolution(integerList, n, 0, initialPosition, solutionDetailList);

            } else {
                //Don't find possible solution
                solutionDetailList = new ArrayList<>();
                solutionDetailList.add(-1);
            }
        }

        return solutionDetailList;
    }

}
