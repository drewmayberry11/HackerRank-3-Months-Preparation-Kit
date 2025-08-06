import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * File: LonelyInteger.java
 *
 * HackerRank 3 Months Preparation Kit - Week 1: Lonely Integer
 * 
 * Problem: Given an array of integers where all elements but one occur twice, 
 * find the unique element that appears exactly once.
 * 
 * Multiple solution approaches demonstrated:
 *   1. HashMap frequency counting - O(n) time, O(n) space
 *   2. Nested loop brute force - O(n²) time, O(1) space  
 *   3. XOR bit manipulation - O(n) time, O(1) space (optimal)
 * 
 * @see Problem URL: https://www.hackerrank.com/challenges/three-month-preparation-kit-lonely-integer/problem
 * @author Drew Mayberry
 * @since 2025-08-06
 */
public class LonelyInteger {

    public static void main(String[] args) {
        // Test case: All methods should return 4 as the lonely integer
        List<Integer> arr = new ArrayList<>(List.of(1, 2, 3, 4, 3, 2, 1));
        System.out.println("Result: " + lonelyIntegerMap(arr));
        System.out.println("Result: " + lonelyIntegerLoop(arr));
        System.out.println("Result: " + lonelyIntegerXOR(arr));
    }

    /**
     * HASHMAP APPROACH: Build frequency map, then find element with count = 1
     * 
     * Algorithm: Two-pass solution
     *   Pass 1: Count frequency of each element using HashMap
     *   Pass 2: Find element with frequency = 1
     * 
     * Time Complexity: O(n) - two linear passes through array
     * Space Complexity: O(n) - HashMap stores up to n/2 unique elements
     * 
     * Pros: Clear, readable, handles general case (not just pairs)
     * Cons: Uses extra memory, requires two passes
     */
    public static int lonelyIntegerMap(List<Integer> arr) {
        int result = 0;
        Map<Integer, Integer> frequencyMap = new HashMap<>();

        // Pass 1: Build frequency map - count occurrences of each element
        for (Integer num : arr) {
            // getOrDefault() safely handles missing keys, avoids null checks
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        // Pass 2: Find element that appears exactly once
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue().equals(1)) { // Found the lonely integer
                result = entry.getKey();
            }
        }

        return result;
    }

    /**
     * BRUTE FORCE APPROACH: For each element, count its occurrences in array
     * 
     * Algorithm: Nested loops - outer iterates elements, inner counts matches
     * 
     * Time Complexity: O(n²) - for each of n elements, scan n elements
     * Space Complexity: O(1) - no additional data structures
     * 
     * Pros: Simple logic, minimal memory usage
     * Cons: Quadratic time complexity, inefficient for large arrays
     */
    // Example trace: 1, 2, 3, 4, 3, 2, 1
    public static int lonelyIntegerLoop(List<Integer> arr) {
        int result = 0;

        // Check each element in the array
        for (int i = 0; i < arr.size(); i++) {
            int currentElement = arr.get(i);
            int count = 0;

            // Count how many times current element appears in entire array
            for (int j = 0; j < arr.size(); j++) {
                if (arr.get(j).equals(currentElement)) {
                    count++; // Found a match
                }
            }

            // If element appears exactly once, we found our answer
            if (count == 1) {
                result = currentElement;
            }
        }

        return result;
    }

    /**
     * OPTIMAL XOR APPROACH: Use bit manipulation properties to cancel pairs
     * 
     * Algorithm: XOR all elements together
     * Key insight: XOR properties make this work elegantly
     *   - a ⊕ a = 0 (identical numbers cancel out)
     *   - a ⊕ 0 = a (XOR with 0 returns original number)
     *   - XOR is commutative (order doesn't matter)
     * 
     * Result: All pairs cancel to 0, leaving only the unique element
     * 
     * Time Complexity: O(n) - single pass through array
     * Space Complexity: O(1) - no additional data structures
     * 
     * This is the mathematically elegant solution!
     */
    public static int lonelyIntegerXOR(List<Integer> arr) {
        int result = 0;

        // XOR all elements - pairs will cancel out, unique element remains
        for (int num : arr) {
            result ^= num; // XOR operation: pairs cancel, unique survives
        }

        return result;
    }
}