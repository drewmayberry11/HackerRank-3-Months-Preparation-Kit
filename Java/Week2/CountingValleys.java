/**
 * CountingValleys.java
 *
 * Problem:
 * Given a hike described by a string of steps ('U' for up, 'D' for down),
 * count how many valleys the hiker walks through. A valley is any contiguous
 * sequence of steps strictly below sea level, starting with a 'D' from sea
 * level and ending with a matching 'U' back to sea level.
 *
 * Description:
 * Altitude starts at 0 (sea level). Each 'U' increases altitude by +1, each
 * 'D' decreases altitude by -1. We scan the path and increment the valley
 * count whenever a step 'U' returns the altitude to 0 (i.e., we just climbed
 * out of a valley).
 *
 * Complexity:
 * - Time: O(N) — each step processed once.
 * - Space: O(1) — constant counters only.
 *
 * HackerRank: 3 Months Preparation Kit — Counting Valleys
 * 
 * @see https://www.hackerrank.com/challenges/three-month-preparation-kit-counting-valleys
 *
 *      Author: Drew Mayberry
 * @since 2025-09-15
 */
public class CountingValleys {

    public static void main(String[] args) {
        // Pre-made test cases
        String[] paths = {
                "UDDDUDUU", // sample -> 1
                "DUDUDU", // -> 3
                "UUUD", // -> 0
                "DDUU", // -> 1
                "" // -> 0
        };

        for (String path : paths) {
            int brute = countingValleysBrute(path);
            int opt = countingValleysOptimized(path);
            if (brute != opt)
                throw new AssertionError("Mismatch for '" + path + "': brute=" + brute + ", opt=" + opt);
            System.out.println("path='" + path + "' -> valleys=" + opt);
        }
    }

    // Basic brute force: build altitude profile and count returns to sea level from
    // below.
    static int countingValleysBrute(String path) {
        int n = path.length();
        int[] alt = new int[n + 1];
        for (int i = 0; i < n; i++) {
            char c = path.charAt(i);
            alt[i + 1] = alt[i] + (c == 'U' ? 1 : -1);
        }
        int valleys = 0;
        for (int i = 1; i <= n; i++) {
            if (alt[i] == 0 && alt[i - 1] < 0)
                valleys++;
        }
        return valleys;
    }

    // Optimized: single pass, count when an 'U' step returns altitude to 0.
    static int countingValleysOptimized(String path) {
        int alt = 0, valleys = 0;
        for (int i = 0; i < path.length(); i++) {
            char c = path.charAt(i);
            alt += (c == 'U') ? 1 : -1;
            if (c == 'U' && alt == 0)
                valleys++;
        }
        return valleys;
    }
}
