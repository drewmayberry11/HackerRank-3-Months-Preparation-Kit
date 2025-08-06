import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * File: SparseArrays.java
 *
 * HackerRank 3 Months Preparation Kit - Week 1: Sparse Arrays
 * 
 * Problem: Given an array of strings and an array of query strings, 
 * determine how many times each query string occurs in the original array.
 * 
 * Solution approach: Use HashMap for O(1) frequency lookups vs O(n) linear search
 * 
 * @see Problem URL: https://www.hackerrank.com/challenges/sparse-arrays/problem
 * @author Drew Mayberry
 * @since 2025-08-05
 */
public class SparseArrays {

    public static void main(String[] args) {
        // Test Case 1: Basic functionality test
        List<String> strings1 = new ArrayList<>(List.of("aba", "baba", "aba", "xzxb"));
        List<String> queries1 = new ArrayList<>(List.of("aba", "xzxb", "ab"));
        System.out.println("Test 1 (Brute Force): " + matchingStrings(strings1, queries1)); // Expected: [2, 1, 0]
        System.out.println("Test 1 (Optimized): " + matchingStringsOptimized(strings1, queries1)); // Expected: [2, 1, 0]
    }

    /**
     * BRUTE FORCE SOLUTION: For each query, scan entire strings array
     * 
     * Algorithm: Nested loops - outer loop iterates queries, inner loop counts matches
     * Time Complexity: O(n * q) where n = strings.size(), q = queries.size()
     * Space Complexity: O(q) for result list only
     * 
     * Use Case: Small datasets or when memory is extremely constrained
     * 
     * @param strings Array of strings to search through
     * @param queries Array of query strings to count
     * @return List of integers representing frequency of each query in strings
     */
    public static List<Integer> matchingStrings(List<String> strings, List<String> queries) {
        List<Integer> result = new ArrayList<>();

        // For each query string, count occurrences in strings array
        for (String query : queries) {
            int count = 0;

            // Linear search through all strings - this is the bottleneck
            for (String string : strings) {
                if (string.equals(query)) { // String.equals() handles null safety
                    count++;
                }
            }

            result.add(count);
        }

        return result;
    }

    /**
     * OPTIMIZED SOLUTION: Build frequency map once, then answer queries in O(1) time
     * 
     * Algorithm: Two-phase approach
     *   Phase 1: Build HashMap of string frequencies (O(n))
     *   Phase 2: Answer each query via HashMap lookup (O(q))
     * 
     * Time Complexity: O(n + q) - linear in total input size
     * Space Complexity: O(u + q) where u = unique strings count
     * 
     * Key Insight: Trade memory for speed - HashMap enables constant-time lookups
     * 
     * @param strings Array of strings to search through
     * @param queries Array of query strings to count
     * @return List of integers representing frequency of each query in strings
     */
    public static List<Integer> matchingStringsOptimized(List<String> strings, List<String> queries) {
        // Phase 1: Build frequency map - count each string's occurrences
        Map<String, Integer> frequencyMap = new HashMap<>();

        for (String string : strings) {
            // getOrDefault() safely handles missing keys, returns 0 if key not found
            // This avoids null pointer exceptions and verbose null checking
            frequencyMap.put(string, frequencyMap.getOrDefault(string, 0) + 1);
        }

        // Phase 2: Answer queries using O(1) HashMap lookups
        List<Integer> results = new ArrayList<>();

        for (String query : queries) {
            // HashMap lookup is O(1) average case - this is where we gain massive speed
            // getOrDefault() returns 0 for strings not in original array
            results.add(frequencyMap.getOrDefault(query, 0));
        }

        return results;
    }
}