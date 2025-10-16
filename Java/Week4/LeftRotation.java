
/**
 * File: LeftRotation.java
 *
 * Problem:
 * Given an array (list) of integers and an integer d, return a new list that is
 * the result of rotating the array d positions to the left. (HackerRank: "Array Left Rotation")
 *
 * Description:
 * Normalize the rotation distance to the array length and map each output index i
 * to the appropriate input element:
 *   1) Let n = arr.size(); if n == 0, return empty.
 *   2) Compute k = floorMod(d, n) to handle large or negative d.
 *   3) Build the result with: result[i] = arr[(i + k) % n].
 * This is a single pass with O(n) work and avoids per-step shifting.
 *
 * Complexity:
 * Time  O(n) — single pass to build the rotated list.
 * Space O(n) — new list (required when the input is unmodifiable like List.of()).
 *
 * Notes:
 * - Use Math.floorMod(d, n) (or ((d % n) + n) % n) to keep k in [0, n).
 * - Guard for n == 0 before any modulo to avoid divide-by-zero.
 * - For mutable lists and in-place requirements, the "reverse-3-times" trick works in O(n) and O(1) extra space.
 * - JDK alternative: Collections.rotate(list, -d) where a negative distance rotates left.
 *
 * HackerRank — Array Left Rotation
 * @see Link: https://www.hackerrank.com/challenges/three-month-preparation-kit-array-left-rotation/
 *
 * Author: Drew Mayberry
 * Since:  2025-09-30
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LeftRotation {

    public static void main(String[] args) {
        int d = 10;
        List<Integer> arr = List.of(1, 2, 3, 4, 5);
        List<Integer> arr2 = List.of(41, 73, 89, 7, 10, 1, 59, 58, 84, 77, 77, 97, 58, 1, 86, 58, 26, 10, 86, 51);

        System.out.println(rotateLeft(d, arr)); // [1, 2, 3, 4, 5] rotated by 10 -> unchanged
        System.out.println(rotateLeft(d, arr2)); // demo with larger list

        System.out.println();
        System.out.println(rotateLeftJdk(d, arr2)); // same using Collections.rotate
        System.out.println();
        System.out.println(rotateLeftBrute(d, arr2)); // same using Collections.rotate
    }

    /**
     * Left-rotate by d positions using index mapping.
     * Returns a new list; input is not modified.
     */
    public static List<Integer> rotateLeft(int d, List<Integer> arr) {
        int n = arr.size();
        if (n == 0)
            return List.of(); // guard before modulo

        int k = Math.floorMod(d, n); // normalize d into [0, n)

        // Build result in O(n). We copy to a mutable list and overwrite positions.
        List<Integer> rotatedList = new ArrayList<>(arr); // size n already
        for (int i = 0; i < n; i++) {
            rotatedList.set(i, arr.get((i + k) % n)); // pick source and place at i
        }
        return rotatedList;
    }

    /**
     * Left-rotate by d positions using the JDK helper.
     * Collections.rotate rotates right for positive distance; use -d for left.
     */
    public static List<Integer> rotateLeftJdk(int d, List<Integer> arr) {
        List<Integer> rotated = new ArrayList<>(arr); // must be mutable
        Collections.rotate(rotated, -d); // negative = left
        return rotated;
    }

    /**
     * Left-rotate by d positions the "brute-force" way:
     * repeat k times: remove first element, append to end.
     * Time: O(n * k) Space: O(1) extra (beyond the copy).
     */
    public static List<Integer> rotateLeftBrute(int d, List<Integer> arr) {
        int n = arr.size();
        if (n == 0)
            return List.of(); // guard empty input

        int k = Math.floorMod(d, n); // normalize d into [0, n)
        List<Integer> rotated = new ArrayList<>(arr); // mutable working copy

        // One left-rotation step = take head → push to tail
        for (int step = 0; step < k; step++) {
            int first = rotated.remove(0); // shifts others left by one
            rotated.add(first); // wrap the saved head to the end
        }
        return rotated;
    }
}
