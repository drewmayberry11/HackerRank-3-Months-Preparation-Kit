import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * File: PermutingTwoArrays.java
 *
 * Problem:
 * Given two integer arrays A and B (same length n) and an integer k,
 * determine whether there exist permutations A' and B' such that for all i:
 * A'[i] + B'[i] >= k
 * Return "YES" if such permutations exist, otherwise "NO".
 *
 * Description (Greedy Insight):
 * Pair the smallest elements of A with the largest elements of B.
 * - Sort A in ascending order (small → large).
 * - Sort B in descending order (large → small).
 * - If every pairwise sum A[i] + B[i] meets k, then some permutation works.
 * - If any index fails, no permutation can satisfy the condition.
 *
 * Correctness Intuition:
 * The “weakest” A-elements get maximum help from the “strongest” B-elements.
 * If even this best alignment fails at any index, no different shuffling helps.
 *
 * Complexity:
 * Time: O(n log n) due to sorting (the check is O(n)).
 * Space: O(1) extra (in-place sorts), excluding list backing.
 *
 * Notes:
 * - Assumes A and B have equal size n (as per problem constraint).
 * - If overflow could be a concern, compute sums in long: (long)A.get(i) +
 * B.get(i).
 *
 * Related: HackerRank — Permuting Two Arrays
 * 
 * @see https://www.hackerrank.com/challenges/three-month-preparation-kit-two-arrays/
 *
 *      Author: Drew Mayberry
 *      Since: 2025-09-17
 */
public class PermutingTwoArrays {

    public static void main(String[] args) {
        // Demo inputs
        List<Integer> A = new ArrayList<>(List.of(0, 1));
        List<Integer> B = new ArrayList<>(List.of(0, 2));
        int k = 1;

        System.out.println(twoArrays(k, A, B)); // Expected: YES
    }

    /**
     * Returns "YES" if there exist permutations of A and B such that
     * A[i] + B[i] >= k for all i; otherwise returns "NO".
     */
    public static String twoArrays(int k, List<Integer> A, List<Integer> B) {
        // Greedy alignment:
        // - Sort A ascending (smallest first)
        // - Sort B descending (largest first)
        Collections.sort(A);
        B.sort(Collections.reverseOrder());

        // Check each aligned pair; fail fast on the first violation
        for (int i = 0; i < A.size(); i++) {
            // If overflow could happen in your constraints, use long:
            // long sum = (long) A.get(i) + B.get(i);
            int sum = A.get(i) + B.get(i);
            if (sum < k) {
                return "NO";
            }
        }
        return "YES";
    }
}
