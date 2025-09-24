
/**
 * File: MigratoryBirds.java
 *
 * Problem:
 * Given a list of bird sighting IDs, return the ID that occurs most frequently.
 * If multiple IDs share the maximum frequency, return the smallest such ID.
 *
 * Description (Counting Insight):
 * This is a frequency-aggregation problem with a deterministic tie-break:
 * - First, tally occurrences for each ID.
 * - Then, select the ID with the highest count; on ties, choose the smaller ID.
 *
 * Implementations:
 * 1) {@code migratoryBirds} (bounded, IDs 1..5): Uses a fixed-size int[]
 * bucket.
 * Time: O(n); Space: O(1). This is optimal when the domain is known and tiny.
 *
 * 2) {@code migratoryBirdsOptimal} (general IDs): Uses a
 * HashMap<Integer,Integer>.
 * Time: O(n); Space: O(u) where u = distinct IDs. This is optimal for
 * unknown/sparse domains.
 *
 * 3) {@code migratoryBirdsModern} (streams): Uses groupingBy + counting().
 * Time: O(n); Space: O(u). Expressive, idiomatic; slightly more overhead than
 * (1).
 *
 * Correctness Intuition:
 * Counting captures exact frequencies. During selection, “higher frequency
 * wins”
 * implements the max criterion; “smaller ID on equal frequency” enforces the
 * tie-break.
 *
 * Complexity:
 * - Counting: O(n) over the sightings.
 * - Selection: O(1) for (1) because only 5 buckets; O(u) for (2) and (3).
 * - Space: O(1) for (1); O(u) for (2) and (3).
 *
 * Notes:
 * - The bounded version assumes inputs are restricted to IDs 1..5 (as per
 * HackerRank spec).
 * - If input may be dirty (IDs outside 1..5), either guard or use the
 * general/modern versions.
 * - Stream counting returns Long; convert with Math.toIntExact if needed.
 * 
 * Related: HackerRank — Migratory Birds
 * @see https://www.hackerrank.com/challenges/three-month-preparation-kit-migratory-birds
 *
 * Author: Drew Mayberry
 * Since: 2025-09-24
 */
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MigratoryBirds {

    public static void main(String[] args) {
        // Quick smoke test
        List<Integer> arr = new ArrayList<>(List.of(1, 1, 2, 2, 3));
        System.out.println(migratoryBirds(arr)); // bounded domain (1..5) → 1 or 2 (tie -> 1)
        System.out.println(migratoryBirdsOptimal(arr)); // general domain → 1
        System.out.println(migratoryBirdsModern(arr)); // streams → 1
    }

    /**
     * Bounded-domain optimal solution (HackerRank constraint IDs ∈ [1..5]).
     * Uses an int[] of fixed size to achieve O(1) space.
     *
     * @param arr list of bird IDs (assumed 1..5)
     * @return smallest ID among those with maximal frequency
     */
    public static int migratoryBirds(List<Integer> arr) {
        // Buckets: index 0 unused; indices 1..5 store counts for IDs 1..5.
        int[] freq = new int[6];

        // Tally counts in O(n). Assumes IDs are valid (1..5).
        for (int bird : arr) {
            freq[bird]++;
        }

        // Seed with smallest valid ID so ties naturally prefer lower IDs.
        int bestId = 1;
        int bestCount = freq[1];

        // Scan remaining IDs; “higher count wins”, tie → “smaller ID wins”.
        for (int id = 2; id <= 5; id++) {
            if (freq[id] > bestCount || (freq[id] == bestCount && id < bestId)) {
                bestCount = freq[id];
                bestId = id;
            }
        }
        return bestId; // Return the winning ID (not the count).
    }

    /**
     * General optimal solution for arbitrary integer IDs (unbounded domain).
     * Uses HashMap to count in O(n) and selects best in O(u).
     *
     * @param arr list of bird IDs (any integers)
     * @return smallest ID among those with maximal frequency
     */
    public static int migratoryBirdsOptimal(List<Integer> arr) {
        // Count frequencies: O(n). getOrDefault avoids null checks.
        Map<Integer, Integer> count = new HashMap<>();
        for (int id : arr) {
            count.put(id, count.getOrDefault(id, 0) + 1);
        }

        // Select best: O(u). Track both best count and best ID for tie-break.
        int bestId = Integer.MAX_VALUE; // ensures the first candidate promotes
        int bestCount = -1;

        for (Map.Entry<Integer, Integer> e : count.entrySet()) {
            int id = e.getKey();
            int c = e.getValue();
            // Promote on strictly higher frequency; on tie, prefer smaller ID.
            if (c > bestCount || (c == bestCount && id < bestId)) {
                bestId = id;
                bestCount = c;
            }
        }
        return bestId;
    }

    /**
     * Stream/modern variant: expressive aggregation using groupingBy + counting().
     * Returns the ID with max frequency; tie → smaller ID.
     *
     * @param arr list of bird IDs
     * @return smallest ID among those with maximal frequency
     */
    public static int migratoryBirdsModern(List<Integer> arr) {
        // Build frequency map: key = ID, value = Long count.
        Map<Integer, Long> freqMap = arr.stream()
                .collect(Collectors.groupingBy(x -> x, Collectors.counting()));

        // Select by (count desc, id asc) using a min over a reversed count comparator
        // chained with key comparator.
        return freqMap.entrySet().stream()
                .min(Comparator.<Map.Entry<Integer, Long>>comparingLong(Map.Entry::getValue).reversed()
                        .thenComparingInt(Map.Entry::getKey))
                .map(Map.Entry::getKey)
                .orElseThrow(); // arr non-empty per typical problem constraints
    }
}
