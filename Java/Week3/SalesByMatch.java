
/**
 * File: SalesByMatch.java
 *
 * Problem:
 *   Given a list of sock colors, count how many color-matching pairs exist.
 *
 * Description:
 *   Three approaches:
 *     1) Basic O(n^2): nested scan with a used[] mask.
 *     2) Optimized O(n): HashSet "toggle"—track colors with an odd count so far.
 *     3) Streams O(n): build a frequency map and sum floor(count/2).
 *
 * Complexity:
 *   - Basic:     Time O(n^2), Space O(n) for used[]
 *   - Optimized: Time O(n) amortized, Space O(k) distinct colors
 *   - Streams:   Time O(n), Space O(k) for frequency map
 *
 * Notes:
 *   - HashSet.add(x) returns false if x already exists; use that to detect a completed pair.
 *   - Streams approach is declarative and concise; same asymptotics as the HashSet method.
 *
 * Related: HackerRank — Sales By Match
 * @see https://www.hackerrank.com/challenges/three-month-preparation-kit-sock-merchant/
 *
 * Author: Drew Mayberry
 * Since:  2025-09-22
 */

import java.util.HashSet;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SalesByMatch {

    public static void main(String[] args) {
        // Demo input
        List<Integer> ar = List.of(1, 2, 1, 2, 1, 3, 2);
        int n = ar.size();

        System.out.println("Basic      = " + sockMerchantBasic(n, ar)); // expect 2
        System.out.println("Optimized  = " + sockMerchantOptimized(n, ar)); // expect 2
        System.out.println("Streams    = " + sockMerchantStreams(n, ar)); // expect 2
    }

    // Approach 1: O(n^2) — try to pair each unused index i with the first matching
    // j > i.
    public static int sockMerchantBasic(int n, List<Integer> ar) {
        boolean[] used = new boolean[n];
        int pairs = 0;

        for (int i = 0; i < n; i++) {
            if (used[i])
                continue; // skip if already paired
            for (int j = i + 1; j < n; j++) {
                if (!used[j] && ar.get(i).equals(ar.get(j))) {
                    used[i] = true; // mark both as paired
                    used[j] = true;
                    pairs++; // one pair formed
                    break; // move to next i
                }
            }
        }
        return pairs;
    }

    // Approach 2: O(n) — HashSet toggle: add on first sight, remove and count on
    // second.
    public static int sockMerchantOptimized(int n, List<Integer> ar) {
        var unpaired = new HashSet<Integer>(Math.max(16, (int) (n * 1.5) + 1)); // presize (optional)
        int pairs = 0;

        for (int color : ar) {
            if (!unpaired.add(color)) { // already present ⇒ complete a pair
                unpaired.remove(color);
                pairs++;
            }
        }
        return pairs;
    }

    // Approach 3: O(n) — Streams: group by color, count, sum floor(count/2).
    public static int sockMerchantStreams(int n, List<Integer> ar) {
        var freq = ar.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return (int) freq.values().stream().mapToLong(c -> c / 2).sum();
    }
}
