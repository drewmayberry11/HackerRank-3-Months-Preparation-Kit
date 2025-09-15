
/**
 * Counting Sort (Frequency Array)
 *
 * Problem:
 * Given a list of integers where each value is guaranteed to be in the range [0, 99],
 * build and return a frequency array of exactly 100 elements such that freq[i] equals
 * the count of value i in the input.
 *
 * Key points:
 * - This is the "Counting Sort 1" / frequency-array step (not reconstructing the sorted list).
 * - Time: O(n) over input; Space: O(1) fixed (100 counters).
 *
 * File: CountingSortDemo.java
 * HackerRank: 3 Months Preparation Kit — Counting Sort 1
 * @see Problem URL: https://www.hackerrank.com/challenges/three-month-preparation-kit-countingsort1/problem
 *
 * @author Drew Mayberry
 * @since 2025-09-15
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CountingSort {

    public static void main(String[] args) {
        // Demo input: 100 values in [0, 99] (matches HackerRank sample input shape)
        List<Integer> arr = new ArrayList<>(List.of(
                63, 25, 73, 1, 98, 73, 56, 84, 86, 57,
                16, 83, 8, 25, 81, 56, 9, 53, 98, 67,
                99, 12, 83, 89, 80, 91, 39, 86, 76, 85,
                74, 39, 25, 90, 59, 10, 94, 32, 44, 3,
                89, 30, 27, 79, 46, 96, 27, 32, 18, 21,
                92, 69, 81, 40, 40, 34, 68, 78, 24, 87,
                42, 69, 23, 41, 78, 22, 6, 90, 99, 89,
                50, 30, 20, 1, 43, 3, 70, 95, 33, 46,
                44, 9, 69, 48, 33, 60, 65, 16, 82, 67,
                61, 32, 21, 79, 75, 75, 13, 87, 70, 33));

        // 1) Straightforward list-based approach (boxed ints, easy to read)
        System.out.println(countingSortBasic(arr)); // prints as a list
        System.out.println();

        // 2) Optimized primitive-array approach (faster, less memory)
        System.out.println(Arrays.toString(countingSortOptimized(arr))); // prints as array
        System.out.println();

        // 3) Modern-init list approach (Streams for initialization, still O(n))
        System.out.println(countingSortModern(arr)); // prints as a list
    }

    /**
     * Brute-force, beginner-friendly version:
     * - Initializes a mutable List<Integer> of size 100 with zeros.
     * - Increments counts by index.
     *
     * Pros: clear and idiomatic for boxed collections.
     * Cons: boxing/unboxing overhead vs. int[].
     *
     * @param arr input values in [0, 99]
     * @return List<Integer> frequency list of size 100
     */
    public static List<Integer> countingSortBasic(List<Integer> arr) {
        // Pre-size with 100 zeros (index i corresponds to value i)
        List<Integer> freq = new ArrayList<>(100);
        for (int i = 0; i < 100; i++) {
            freq.add(0);
        }

        // Increment the counter at the index equal to the observed value
        for (int num : arr) {
            // No range-check here to keep it "basic"; assumes valid input.
            freq.set(num, freq.get(num) + 1);
        }
        return freq;
    }

    /**
     * Professional / optimized version:
     * - Uses a primitive int[100] to avoid boxing and extra indirection.
     * - Validates input defensively and fails fast on invalid values.
     *
     * @param arr input values (must be non-null; each in [0, 99])
     * @return int[] frequency array of size 100
     * @throws NullPointerException     if arr is null
     * @throws IllegalArgumentException if a value is out of range
     */
    public static int[] countingSortOptimized(List<Integer> arr) {
        Objects.requireNonNull(arr, "arr must not be null");

        int[] freq = new int[100]; // zero-initialized by default
        for (int num : arr) {
            if (num < 0 || num >= 100) {
                throw new IllegalArgumentException("Value out of range: " + num);
            }
            freq[num]++; // O(1) increment at the target index
        }
        return freq;
    }

    /**
     * Modern-init version:
     * - Uses streams only for the zero-initialization of a List<Integer>.
     * - Counting logic remains the same as the basic version.
     *
     * Note: This remains boxed and is not faster than the optimized int[] version.
     * It's here to demonstrate modern collection initialization patterns.
     *
     * @param arr input values in [0, 99]
     * @return List<Integer> frequency list of size 100
     */
    public static List<Integer> countingSortModern(List<Integer> arr) {
        // Create a mutable List<Integer> with 100 zeros using Streams
        var freq = IntStream.range(0, 100)
                .mapToObj(i -> 0)
                .collect(Collectors.toCollection(ArrayList::new));

        // Increment counts by index
        for (int num : arr) {
            freq.set(num, freq.get(num) + 1);
        }
        return freq;
    }
}
