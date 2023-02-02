package com.example.algorithms;

import java.util.Arrays;
import java.util.List;

public class IntermediateTests {

    /**
     * Given a string s of length n. The task is to find the length of the shortest substring, which upon deletion,
     * makes the resultant string to be consisting of distinct characters only.
     */
    public static void shortestSubstringToDelete(String text) {

        System.out.println(Math.min(findMinLength(text),
                findMinLength(new StringBuilder(text).reverse().toString())));
    }

    public static int findMinLength(String s) {
        int n = s.length();
        boolean[] visited = new boolean[256];

        int len = 0;
        for (int i = 0; i < n; i++) {
            if (visited[s.charAt(i)]) {
                for (int j = n - 1; j >= i; j--) {

                    if (visited[s.charAt(j)]) {
                        len = j - i + 1;
                        break;
                    } else {
                        visited[s.charAt(j)] = true;
                    }
                }
                break;
            } else {
                visited[s.charAt(i)] = true;
            }
        }
        return len;
    }

    /**
     * For each word in a list of words, if any two adjacent characters are equal, change one of them. Determine
     * the minimum number of substitutions so the final string contains no adjacent equal characters.
     * <p>
     * Example
     * words = ['add', 'boook', 'break']
     * 1. 'add': change one d (1 change)
     * 2. 'boook': change the middle o (1 change)
     * 3. 'break': no changes are necessary (0 changes)
     * The return array is [1,1,0].
     */
    public static void noPairsAllowed(List<String> list) {

        int[] ans = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            int count = 0;
            int j = 1;
            String w = list.get(i);

            while (j < w.length()) {

                //test for match
                if (w.charAt(j) == w.charAt(j - 1)) {
                    //increment counter and skip the next character
                    count += 1;
                    j += 2;
                } else {
                    //move to the next character
                    j += 1;
                }
            }

            ans[i] = count;
        }

        System.out.println(Arrays.toString(ans));
    }

    /**
     * Consider every subsequence of an array of integers.
     * Sort the subsequence in increasing order.
     * Determine the sum of differences of elements in the subsequence.
     * Return the length of the longest subsequence where this sum is even
     */
    public static void findLongestSubsequence(List<Integer> list) {

        list = list.stream().sorted().toList();

        int firstOdd = -1;
        int firstEven = -1;
        int lastOdd = -1;
        int lastEven = -1;

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) % 2 == 0) {
                lastEven = i;
                if (firstEven == -1) {
                    firstEven = i;
                }
            } else {
                lastOdd = i;
                if (firstOdd == -1) {
                    firstOdd = i;
                }
            }
        }

        int ans = 1;
        if (firstOdd != -1) {
            ans = Math.max(ans, lastOdd - firstOdd + 1);
        }
        if (firstEven != -1) {
            ans = Math.max(ans, lastEven - firstEven + 1);
        }

        System.out.println(ans);
    }

    // Function to calculate Euclidean distance to the centroid
    static double find(double x, double y,
                       int[][] p) {
        double mind = 0;

        for (int i = 0; i < p.length; i++) {
            double a = p[i][0], b = p[i][1];
            mind += Math.sqrt((x - a) * (x - a) +
                    (y - b) * (y - b));
        }
        return mind;
    }

    /**
     * Function to calculate the minimum sum of the euclidean distances to all points
     */
    static void getMinDistSum(int[][] p) {

        // Calculate the centroid
        double x = 0, y = 0;
        for (int i = 0; i < p.length; i++) {
            x += p[i][0];
            y += p[i][1];
        }

        x = x / p.length;
        y = y / p.length;

        // Calculate distance of all points
        System.out.println(find(x, y, p));
    }


    public static void main(String[] args) {

//        shortestSubstringToDelete("xabbcacpqr");
//        noPairsAllowed(List.of("ab","aab","abb","abab","abaaaba","abaaaaba"));
//        findLongestSubsequence(List.of(5, 6, 2, 3, 2, 4));

        // Initializing the points
        int[][] vec = {{0, 1}, {1, 0},
                {1, 2}, {2, 1}};
        getMinDistSum(vec);
    }
}
