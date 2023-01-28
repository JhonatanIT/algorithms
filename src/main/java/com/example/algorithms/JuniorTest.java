package com.example.algorithms;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class JuniorTest {

    public static void reverseString(String text) {
        String reverseString = new StringBuilder(text).reverse().toString();
        System.out.println(reverseString);
    }

    public static void isPalindromicNumber(int number) {

        String numberString = String.valueOf(number);
        String reverseNumberString = new StringBuilder(numberString).reverse().toString();

        System.out.println(numberString.equals(reverseNumberString));
    }

    public static void countCharacters(String string) {

        int[] counter = new int[256];

        IntStream.range(0, string.length())
                .forEach(i -> counter[string.charAt(i)]++);

        IntStream.range(0, counter.length)
                .filter(i -> counter[i] > 0)
                .forEach(i -> System.out.println((char) i + " : " + counter[i]));
    }

    public static void countRepeatedCharacter(String string) {

        int[] counter = new int[256];

        IntStream.range(0, string.length())
                .forEach(i -> counter[string.charAt(i)]++);

        IntStream.range(0, counter.length)
                .filter(i -> counter[i] > 1)
                .forEach(i -> System.out.println((char) i + " : " + counter[i]));
    }

    public static void isNumberMultipleOf(int number, int multiplier) {
        System.out.println(number % multiplier == 0);
    }

    public static void isLeapYear(int number) {
        System.out.println(LocalDate.of(number, 1, 1).isLeapYear());
    }

    public static void randomOrderString(String string) {
        List<String> list = Arrays.asList(string.split(""));
        Collections.shuffle(list);
        System.out.println(list);
    }

    public static void getNonDuplicatedList(List<Integer> list, int[] nums) {
        Set<Integer> integerSet = new HashSet<>(list);
        System.out.println(integerSet);
    }

    public static void stringHasVowel(String string) {

        //Using match
//        System.out.println(string.toLowerCase().matches(".*[aeiou].*"));

        //using functional programming
        System.out.println(Stream.of(string.toLowerCase().split("")).anyMatch(x -> x.matches("[aeiou]")));
    }

    /**
     * use regionMatches
     * Params:
     * - offset first string
     * - string to compare
     * - offset of string to compare
     * - length to compare
     *
     * @param morsecode
     */
    public static void morseCode(String morsecode) {

        // Input: ....  (replace ".." by "--")
        // Output: [--.. , .--. , ..--]
        List<String> list = new ArrayList<>();

        if (morsecode.contains("..")) {
            int length = morsecode.length();
            String stringReplaced = "..";

            for (int i = 0; i < length; i++) {
                if (morsecode.regionMatches(i, stringReplaced, 0, 2)) {
                    list.add(morsecode.substring(0, i) + morsecode.substring(i, length).replaceFirst("..", "--"));
                }
            }
        } else if (!morsecode.isEmpty()) {
            list.add(morsecode);
        }

        System.out.println(list);
    }

    public static void minSizeSubstringDeleted(String string) {

        Map<String, Integer> map = new HashMap<>();

        //Mapping characters and quantity
        Arrays.asList(string.split("")).forEach(x -> {
            if (map.containsKey(x)) {
                map.put(x, map.get(x) + 1);
            } else {
                map.put(x, 1);
            }
        });

        //Get duplicated and non-duplicated characters
        List<String> duplicated = map.entrySet().stream().filter(x -> x.getValue() > 1).map(Map.Entry::getKey).toList();
        List<String> nonDuplicated = map.entrySet().stream().filter(x -> x.getValue() == 1).map(Map.Entry::getKey).toList();

        int counter = string.length();
        String uniqueString = string;

        //Get all substring
        for (int i = 0; i < string.length(); i++) {
            for (int j = i + 1; j <= string.length(); j++) {

                String temp = string.substring(0, i) + string.substring(j);  //delete every substring and evaluate

                if (duplicated.stream().allMatch(x -> temp.chars().filter(c -> (char) c == x.charAt(0)).count() <= 1)   //only 1 occurrence of duplicated
                        && nonDuplicated.stream().anyMatch(temp::contains)                                              //there may be no-duplicate items
                        && j - i < counter) {
                    counter = j - i;
                    uniqueString = temp;
                }
            }
        }

        System.out.println("The minimum length of the deleted subString is " + counter + " and the final subString with unique elements is: " + uniqueString);

    }

    public static void main(String[] args) {

//        reverseString("moto");
//        isPalindromicNumber(2342432);
//        countCharacters("Jhonatan");
//        countRepeatedCharacter("Jhonatan");
//        isNumberMultipleOf(25,5);
//        isLeapYear(2024);
//        randomOrderString("Jhonatan");
//        getNonDuplicatedList(Arrays.asList(1,2,4,5,6,7,2,346,6,5,2));
//        stringHasVowel("Jhonatan");
//        morseCode("....");
        minSizeSubstringDeleted("abcfgacbcrbbb");  //
    }

}
