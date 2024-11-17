package com.example.algorithms;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
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

        IntStream.range(0, string.length()).forEach(i -> counter[string.charAt(i)]++);

        IntStream.range(0, counter.length).filter(i -> counter[i] > 0).forEach(i -> System.out.println((char) i + " : " + counter[i]));
    }

    public static void countRepeatedCharacter(String string) {

        int[] counter = new int[256];

        IntStream.range(0, string.length()).forEach(i -> counter[string.charAt(i)]++);

        IntStream.range(0, counter.length).filter(i -> counter[i] > 1).forEach(i -> System.out.println((char) i + " : " + counter[i]));
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

    public static void miniMaxSum(List<Integer> arr) {
        // Write your code here

        int n = arr.size();

        if (n == 0) {
            System.out.println("0 0");
            return;
        } else if (n == 1) {
            System.out.println(arr.getFirst() + " " + arr.getFirst());
            return;
        }

        List<Integer> arrOrdered = arr.stream().sorted().toList();
        long sum = 0;
        for (int i = 1; i < n - 1; i++) {
            sum += arrOrdered.get(i);
        }

        long min = sum + arrOrdered.getFirst();
        long max = sum + arrOrdered.getLast();

        System.out.println(min + " " + max);
    }

    public static String timeConversion(String s) {
        // Write your code here
        String[] time = s.split(":");
        int hour = Integer.parseInt(time[0]);

        if (s.contains("PM") && 12 != hour) {
            hour += 12;
        } else if (s.contains("AM") && 12 == hour) {
            hour = 0;
        }

        //String.format("%02d", hour) -> add up to 2 digits
        String timeConversion = String.format("%02d", hour) + ":" + time[1] + ":" + time[2].substring(0, 2);
        System.out.println(timeConversion);

        return timeConversion;
    }

    public static List<Integer> breakingRecords(List<Integer> scores) {
        // Write your code here
        int countMin = 0;
        int countMax = 0;
        int min = scores.getFirst();
        int max = scores.getFirst();

        for (int score : scores) {
            if (score < min) {
                countMin++;
                min = score;
            } else if (score > max) {
                countMax++;
                max = score;
            }
        }

        List<Integer> records = new ArrayList<>();
        records.add(countMax);
        records.add(countMin);
        System.out.println(records);

        return records;
    }

    public static void camelCase(List<String> list) {
//        for (String line : list) {
//            String[] splitLine = line.split(";");
//            StringBuilder result = new StringBuilder();
//
//            if ("S".equals(splitLine[0])) {
//
//                //Split
//                for (int i = 0; i < splitLine[2].length(); i++) {
//                    char c = splitLine[2].charAt(i);
//                    if (Character.isUpperCase(c) && i > 0) {
//                        result.append(" ");
//                    }
//                    result.append(c);
//                }
//                System.out.println(result.toString().replace("()", "").toLowerCase());
//            } else {
//
//                //Combine
//                String[] words = splitLine[2].split(" ");
//
//                //Class
//                if ("C".equals(splitLine[1])) {
//                    result.append(Character.toUpperCase(words[0].charAt(0))).append(words[0].substring(1));
//                } else {
//                    result.append(words[0]);
//                }
//                for (int i = 1; i < words.length; i++) {
//                    result.append(Character.toUpperCase(words[i].charAt(0))).append(words[i].substring(1));
//                }
//
//                //Method
//                if ("M".equals(splitLine[1])) {
//                    result.append("()");
//                }
//                System.out.println(result);
//            }
//        }

        //Functional programming
        list.forEach(line -> {
            String[] splitLine = line.split(";");
            String result;

            if ("S".equals(splitLine[0])) {
                // Split
                result = IntStream.range(0, splitLine[2].length()).mapToObj(i -> {
                    char c = splitLine[2].charAt(i);
                    return (Character.isUpperCase(c) && i > 0 ? " " : "") + c;
                }).collect(Collectors.joining()).replace("()", "").toLowerCase();
            } else {
                // Combine
                String[] words = splitLine[2].split(" ");
                result = IntStream.range(0, words.length).mapToObj(i -> {
                    String word = words[i];

                    //Class
                    if ((i == 0 && "C".equals(splitLine[1])) || (i > 0)) {
                        return Character.toUpperCase(word.charAt(0)) + word.substring(1);
                    } else {
                        return word;
                    }
                }).collect(Collectors.joining());

                // Method
                if ("M".equals(splitLine[1])) {
                    result += "()";
                }
            }
            System.out.println(result);
        });
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
//        minSizeSubstringDeleted("abcfgacbcrbbb");  //
//        miniMaxSum(Arrays.asList(1, 2, 4, 5, 6, 7, 2, 346, 6, 5, 2));
//        timeConversion("04:40:22AM"); //"12:34:43PM"
//        breakingRecords(List.of(10, 5, 20, 20, 4, 5, 2, 25, 1));
        camelCase(List.of("S;V;iPad", "C;M;mouse pad", "C;C;code swarm", "C;V;code variable", "S;C;OrangeHighlighter"));
    }

}
