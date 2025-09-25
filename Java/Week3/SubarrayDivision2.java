import java.util.List;

/**
 * File: SubarrayDivision2.java
 *
 * Problem:
 * Given a list of integers on chocolate squares, count how many contiguous
 * segments
 * of length m have a sum exactly equal to d. (HackerRank: "Subarray Division" /
 * "Birthday Chocolate")
 *
 * Description:
 * Use a fixed-length sliding window:
 * 1) Sum the first window [0..m-1].
 * 2) Slide the window one step at a time: add the new rightmost element and
 * subtract the element that fell off the left.
 * 3) Each time the window sum equals d, increment the counter.
 *
 * Complexity:
 * Time O(n) — each element enters and leaves the window once
 * Space O(1) — constant extra memory
 *
 * Notes:
 * - Guard for invalid inputs (null, m <= 0, or m > n).
 * - Works with zeros and duplicate values.
 * - Sliding window beats a nested O(n*m) scan by reusing partial sums.
 *
 * Related:
 * HackerRank — Subarray Division (Birthday Chocolate)
 * 
 * @see https://www.hackerrank.com/challenges/three-month-preparation-kit-the-birthday-bar
 *
 *      Author: Drew Mayberry
 *      Since: 2025-09-25
 */
public class SubarrayDivision2 {

    public static void main(String[] args) {
        // Minimal demo
        List<Integer> s = List.of(2, 2, 1, 3, 2);
        int d = 4; // target sum (day)
        int m = 2; // window length (month)

        System.out.println(birthday(s, d, m)); // expected: 2
    }

    /**
     * Counts contiguous segments of length m whose sum equals d.
     *
     * @param s list of non-negative integers (each square's value)
     * @param d target sum (day)
     * @param m window length (month)
     * @return number of valid segments
     */
    public static int birthday(List<Integer> s, int d, int m) {
        // Basic input validation to avoid IndexOutOfBounds / nonsense cases
        if (s == null || m <= 0 || s.size() < m)
            return 0;

        int count = 0;
        int windowSum = 0;

        // 1) Sum the first window [0 .. m-1]
        for (int i = 0; i < m; i++) {
            windowSum += s.get(i);
        }
        if (windowSum == d)
            count++;

        // 2) Slide the window across the list
        // At step j, the window covers [j-m+1 .. j]
        for (int j = m; j < s.size(); j++) {
            windowSum += s.get(j) // add the new rightmost element
                    - s.get(j - m); // remove the element that left the window
            if (windowSum == d) {
                count++;
            }
        }

        return count;
    }
}
