
/**
 * File: DivisibleSumPairs.java
 * 
 * HackerRank 3 Months Preparation Kit - Week 1: Divisible Sum Pairs
 * @see Problem URL: https://www.hackerrank.com/challenges/three-month-preparation-kit-divisible-sum-pairs/problem
 *
 * This program determines the number of (i, j) index pairs in a list where:
 *   - i < j
 *   - (ar[i] + ar[j]) % k == 0
 *
 * Two implementations are provided:
 *   1. Basic Brute-Force Approach (O(n²))
 *   2. Optimized Frequency-Array Approach (O(n + k))
 *
 * @author Drew Mayberry
 * @since 2025-08-05
 */

import java.util.List;

public class DivisibleSumPairs {

    public static void main(String[] args) {
        // Sample input from HackerRank
        List<Integer> array = List.of(1, 3, 2, 6, 1, 2);
        int k = 3;

        System.out.println("Brute-Force Result:   " +
                divisibleSumPairsBasic(array.size(), k, array));
        System.out.println("Optimized Result:     " +
                divisibleSumPairsOptimized(array.size(), k, array));
    }

    /**
     * Brute Force:
     * Checks every possible pair (i, j) where i < j.
     * Increments count if (ar[i] + ar[j]) is divisible by k.
     *
     * @param n  Number of elements in the list
     * @param k  Divisor for divisibility check
     * @param ar List of integers
     * @return Number of valid divisible-sum pairs
     *
     * Time Complexity:  O(n²)
     * Space Complexity: O(1)
     */
    public static int divisibleSumPairsBasic(int n, int k, List<Integer> ar) {
        int count = 0;

        // Test all pairs (i, j) with i < j
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // Check if sum is divisible by k
                if ((ar.get(i) + ar.get(j)) % k == 0) {
                    count++;
                }
            }
        }

        return count;
    }

    /**
     * Optimized:
     * Uses a frequency array of size k to track remainders seen so far.
     * For each value, finds the complementary remainder needed for a divisible sum.
     * Adds the number of such complements already seen to the count.
     *
     * @param n  Number of elements in the list
     * @param k  Divisor for divisibility check
     * @param ar List of integers
     * @return Number of valid divisible-sum pairs
     *
     * Time Complexity:  O(n + k) → O(n) for typical cases
     * Space Complexity: O(k)
     */
    public static int divisibleSumPairsOptimized(int n, int k, List<Integer> ar) {
        int count = 0;
        int[] frequency = new int[k]; // frequency[r] = count of numbers with remainder r

        for (int value : ar) {
            // Remainder of current number when divided by k
            int remainder = value % k;

            // Complement remainder needed to form a divisible sum
            int complement = (k - remainder) % k;

            // Add all previously seen numbers with the needed complement remainder
            count += frequency[complement];

            // Record this number's remainder for future pairings
            frequency[remainder]++;
        }

        return count;
    }
}
