
/**
 * File: Maximum_Perimeter_Triangle.java
 *
 * Problem:
 * Given a multiset of stick lengths, choose 3 to form a **non-degenerate**
 * triangle (strict inequality) with the **maximum possible perimeter**.
 * Tie-breakers if multiple triangles share the same max perimeter:
 *   1) Prefer the one with the **largest maximum side**.
 *   2) If still tied, prefer the one with the **largest minimum side**.
 * If no non-degenerate triangle exists, return [-1].
 *
 * Description (Greedy after sort):
 * 1) Sort the lengths in non-decreasing order.
 * 2) Scan from right to left, considering consecutive triples (a,b,c).
 * 3) The first triple that satisfies a + b > c is optimal:
 *      - Being farthest right maximizes perimeter,
 *      - and automatically satisfies the tie-breakers.
 *
 * Complexity:
 * Time  : O(n log n)  — dominated by the sort
 * Space : O(n)        — only because we copy the (possibly immutable) input List
 *                       (O(1) extra if you sort a primitive array in place)
 *
 * Notes:
 * - Check inputs early (null or fewer than 3 elements ⇒ [-1]).
 * - Use (long)a + b > c to avoid integer overflow on the sum.
 * - Consecutive-triple check after sorting is sufficient; if the two largest
 *   smaller sides fail with c, any smaller pair will also fail.
 *
 * @see Link: https://www.hackerrank.com/challenges/three-month-preparation-kit-maximum-perimeter-triangle/
 * Related: HackerRank — Maximum Perimeter Triangle
 *
 * Author: Drew Mayberry
 * Since : 2025-09-26
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Maximum_Perimeter_Triangle {

    public static void main(String[] args) {
        System.out.println(maximumPerimeterTriangle(List.of(1, 2, 3, 4, 5, 10))); // [3, 4, 5]
        System.out.println(maximumPerimeterTriangle(List.of(1, 1, 1, 3, 3))); // [1, 1, 1]
        System.out.println(maximumPerimeterTriangle(List.of(1, 2, 3))); // [-1]
    }

    public static List<Integer> maximumPerimeterTriangle(List<Integer> sticks) {
        // Guard early
        if (sticks == null || sticks.size() < 3)
            return List.of(-1);

        // Sort a modifiable copy
        List<Integer> s = new ArrayList<>(sticks);
        Collections.sort(s); // non-decreasing

        // Scan from the right; first valid triple wins (max perimeter + tie-breakers)
        for (int i = s.size() - 1; i >= 2; i--) {
            int a = s.get(i - 2);
            int b = s.get(i - 1);
            int c = s.get(i);
            if ((long) a + b > c) {
                return List.of(a, b, c); // already non-decreasing
            }
        }
        return List.of(-1);
    }
}
