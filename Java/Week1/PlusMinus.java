import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * This class solves the Plus Minus problem by calculating ratios
 * of positive, negative, and zero elements in an array.
 *
 * HackerRank 3 Months Preparation Kit - Week 1: Plus Minus
 * @see Problem URL: https://www.hackerrank.com/challenges/three-month-preparation-kit-plus-minus/problem
 *
 * @author Drew Mayberry
 * @since 2025-07-31
 */
public class PlusMinus {

    /**
     * Main method to test both brute force and optimized solutions.
     * Demonstrates different approaches with sample test cases.
     */
    public static void main(String[] args) {
        // Test data: mix of positive, negative, and zero values
        List<Integer> arr1 = new ArrayList<>(List.of(1, 1, 0, -1, -1));
        List<Integer> arr2 = new ArrayList<>(List.of(-4, 3, -9, 0, 4, 1)); // HackerRank sample

        System.out.println("Plus Minus | Hacker Rank Week 1\n");

        // Compare performance and readability of different approaches
        System.out.println("\nBrute Force Plus Minus:");
        System.out.println("Array 1");
        plusMinusSolution(arr1);
        System.out.println("Array 2");
        plusMinusSolution(arr2);

        System.out.println("\nModern Optimized Plus Minus:");
        System.out.println("Array 1");
        plusMinusModern(arr1);
        System.out.println("Array 2");
        plusMinusModern(arr2);
    }

    /**
     * Efficient single-pass solution using traditional loop and counters.
     * Optimal approach for competitive programming due to O(n) time complexity.
     * 
     * Time Complexity: O(n) - single pass through array
     * Space Complexity: O(1) - fixed number of counter variables
     *
     * @param arr the input list of integers to analyze
     */
    public static void plusMinusSolution(List<Integer> arr) {
        // Initialize category counters - tracks distribution of elements
        int positive = 0;
        int negative = 0;
        int zero = 0;
        int n = arr.size(); // Cache size to avoid repeated method calls

        // Single-pass classification: categorize each element exactly once
        for (Integer num : arr) {
            if (num < 0) {
                negative++;
            } else if (num == 0) {
                zero++;
            } else {
                positive++; // Implicitly handles num > 0 case
            }
        }

        // Calculate ratios with explicit double casting for floating-point precision
        // Cast prevents integer division which would truncate decimal results
        double positiveRatio = (double) positive / n;
        double negativeRatio = (double) negative / n;
        double zeroRatio = (double) zero / n;

        // Output formatted to exactly 6 decimal places per problem requirements
        System.out.printf("%.6f%n", positiveRatio);
        System.out.printf("%.6f%n", negativeRatio);
        System.out.printf("%.6f%n", zeroRatio);
    }

    /**
    * Modern functional approach using Java Streams for element classification.
    * Trades some performance for code readability and functional programming style.
    * Shown simply for education of modern java features.
    * 
    * Time Complexity: O(3n) - three separate stream passes
    * Space Complexity: O(1) - constant space for counters
    *
    * @param arr the input list of integers to analyze
    */
    public static void plusMinusModern(List<Integer> arr) {
        // Edge case: handle null or empty arrays gracefully
        if (arr == null || arr.isEmpty()) {
            System.out.println("0.000000\n0.000000\n0.000000");
            return;
        }

        double size = arr.size(); // Cache size for ratio calculations

        // Use streams to count each category with functional filtering
        long negativeCount = arr.stream().filter(n -> n < 0).count();
        long zeroCount = arr.stream().filter(n -> n == 0).count();
        long positiveCount = arr.stream().filter(n -> n > 0).count();

        // Pipeline: convert counts to ratios and format output in one operation
        Stream.of(positiveCount, negativeCount, zeroCount)
                .mapToDouble(count -> count / size)
                .forEach(ratio -> System.out.printf("%.6f%n", ratio));
    }
}