
/**
 * File: Picking_Numbers.java
 *
 * Problem:
 * Given an array of integers, return the length of the largest subset such that
 * the absolute difference between any two elements is ≤ 1. (HackerRank: "Picking Numbers")
 *
 * Description:
 * Use a small fixed histogram over the value domain [1..99]:
 * 1) Count occurrences of each value v.
 * 2) For each v, compute freq[v] + freq[v+1] (the best subset using only v and v+1).
 * 3) The maximum of those sums is the answer.
 * This avoids sorting and nested scans.
 *
 * Complexity:
 * Time  O(n + K)  where K ≤ 100 (tiny constant domain) — effectively linear in n.
 * Space O(K)      for the frequency array (constant-sized).
 *
 * Notes:
 * - Inputs satisfy 1 ≤ a[i] ≤ 99; duplicates are expected.
 * - If the domain were large/unknown, a HashMap count is an alternative (higher constant factor).
 * - A sort+scan solution is O(n log n) and unnecessary here.
 *
 * Related:
 * HackerRank — Picking Numbers
 * @see Link: https://www.hackerrank.com/challenges/three-month-preparation-kit-picking-numbers
 *
 * Author: Drew Mayberry
 * Since:  2025-09-28
 */
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Picking_Numbers {

    public static void main(String[] args) {
        System.out.println("Picking Numbers\n");

        // Demo data; expected answer is 5 (subset of 4s and 5s).
        List<Integer> a = List.of(1, 1, 2, 2, 4, 4, 5, 5, 5);

        System.out.println(pickingNumbers(a)); // simple version
        System.out.println(pickingNumbersOptimized(a)); // optimized version
    }

    /**
     * Optimized: histogram over value domain [0..100] then take
     * max(freq[v] + freq[v+1]) for v in [0..99].
     * Time: O(n + K), Space: O(K).
     */
    public static int pickingNumbersOptimized(List<Integer> a) {
        if (a == null || a.isEmpty())
            return 0; // minimal guard

        int[] freq = new int[101]; // 0..100 for safe v+1 access
        for (int v : a) {
            // If inputs are guaranteed 1..99, this check is optional:
            if (v >= 0 && v <= 100)
                freq[v]++; // count occurrences
        }

        System.out.println("Frequency " + Arrays.toString(freq));

        int bestCount = 0;
        for (int v = 0; v <= 99; v++) { // consider v and v+1
            int candidate = freq[v] + freq[v + 1];
            if (candidate > bestCount)
                bestCount = candidate;
        }
        return bestCount;
    }

    /**
     * Simple: for each distinct value v, count elements equal to v or v+1.
     * Time: O(n * u) where u = distinct values (≤100), Space: O(u).
     */
    public static int pickingNumbers(List<Integer> a) {
        if (a == null || a.isEmpty())
            return 0;

        // Unique values to anchor each (v, v+1) check.
        Set<Integer> seen = new HashSet<>(a);

        int best = 0;
        for (int v : seen) {
            int cnt = 0;
            for (int x : a) {
                if (x == v || x == v + 1)
                    cnt++; // subset uses only v and v+1
            }
            best = Math.max(best, cnt);
        }
        return best;
    }
}