/**
 * File: NumberLineJumps.java
 *
 * Problem:
 * Two kangaroos start at positions x1 and x2 on a number line and jump forward
 * at constant rates v1 and v2 (meters per jump). Determine if there exists an
 * integer jump count n ≥ 0 such that their positions are equal at the same
 * time.
 *
 * Description:
 * Two implementations:
 * 1) kangaroo (simulation): includes the n = 0 check, then advances both
 * positions
 * in lockstep up to a safe cap (pedagogical, not optimal).
 * 2) kangarooOptimal (algebraic, O(1)): solve x1 + n·v1 = x2 + n·v2 ⇒
 * n = (x2 - x1) / (v1 - v2)
 * Valid iff:
 * - v1 == v2 ⇒ they meet only if x1 == x2 (n = 0)
 * - else (x2 - x1) and (v1 - v2) have the same sign (n ≥ 0), and
 * (x2 - x1) % (v1 - v2) == 0 (n is integer).
 *
 * Complexity:
 * - kangaroo: O(N) steps where N is the chosen cap; O(1) space.
 * - kangarooOptimal: O(1) time and O(1) space.
 *
 * Notes:
 * - HackerRank counts meeting at n = 0 as a valid "YES".
 * - Prefer the algebraic solution in interviews; no arbitrary loop limits.
 * - Cast to long for intermediate products in sign checks to avoid overflow
 * habits.
 *
 * Related:
 * HackerRank — Number Line Jumps
 * 
 * @see Link:
 *      https://www.hackerrank.com/challenges/three-month-preparation-kit-kangaroo/
 *
 *      Author: Drew Mayberry
 *      Since: 2025-10-16
 */

public class NumberLineJumps {

    public static void main(String[] args) {
        int x1 = 1;
        int v1 = 1;
        int x2 = 1;
        int v2 = 2;

        System.out.println(kangaroo(x1, v1, x2, v2));
        System.out.println(kangarooOptimal(x1, v1, x2, v2));
    }

    /**
     * Simulation that includes the n = 0 instant:
     * - Start at initial positions (p1 = x1, p2 = x2).
     * - First check equality (n = 0).
     * - Then step both forward and repeat up to a cap.
     * Pedagogical; not optimal and relies on a loop limit.
     */
    public static String kangaroo(int x1, int v1, int x2, int v2) {
        int p1 = x1, p2 = x2; // positions at jump count n
        for (int n = 0; n <= 10000; n++) { // includes n = 0; arbitrary cap for demo
            if (p1 == p2)
                return "YES"; // meet at this jump count n
            p1 += v1; // advance to next jump count (n+1)
            p2 += v2;
        }
        return "NO"; // not observed within cap
    }

    /**
     * Optimal O(1) algebraic solution:
     * Solve x1 + n·v1 = x2 + n·v2 ⇒ n = (x2 - x1) / (v1 - v2).
     * Conditions:
     * - If v1 == v2: meet only if x1 == x2 (they start together at n = 0).
     * - Else:
     * * n must be non-negative ⇒ (x2 - x1) and (v1 - v2) have same sign or zero
     * gap.
     * * n must be integral ⇒ (x2 - x1) % (v1 - v2) == 0.
     */
    public static String kangarooOptimal(int x1, int v1, int x2, int v2) {
        if (v1 == v2) // same speed
            return (x1 == x2) ? "YES" : "NO"; // only meet if they start together

        int num = x2 - x1; // initial position gap
        int den = v1 - v2; // relative speed (gap closure per jump)

        // If num and den have opposite signs, n would be negative ⇒ no meeting for n ≥
        // 0
        if ((long) num * (long) den < 0) // use long for safety habits
            return "NO";

        // Must land exactly at the same position on an integer jump count
        return (num % den == 0) ? "YES" : "NO";
    }
}
