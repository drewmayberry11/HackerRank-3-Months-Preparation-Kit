
/**
 * Pangram.java
 *
 * Problem:
 *   Determine whether a given string is a pangram (contains every English
 *   letter 'a'..'z' at least once), ignoring case and non-letter symbols.
 *
 * Description:
 *   Implement three approaches that all return the string "Pangram" or
 *   "Not Pangram":
 *
 *   1) pangramBasic(String):
 *      - Uses a HashMap<Character,Integer> to count letter frequencies.
 *      - Early-exits when 26 distinct letters have been observed.
 *
 *   2) pangramBoolean(String):
 *      - Uses a boolean[26] to mark whether each letter a..z has appeared.
 *      - Tracks a 'distinct' counter; early-exits at 26.
 *
 *   3) isPangramBitmask(String):
 *      - Uses an int bitmask; bit i indicates letter (char)('a'+i) is present.
 *      - When mask == (1<<26)-1, all letters have appeared -> pangram.
 *
 * Complexity:
 *   All three methods:
 *     - Time: O(n), where n = s.length() (single pass).
 *     - Space: O(1) auxiliary (bounded by 26 letters).
 *
 * Notes:
 *   - Inputs may include spaces, punctuation, digits, etc. We only consider A–Z/a–z.
 *   - Case-insensitive: normalize to lowercase per character.
 *   - For interviews, boolean-array or bitmask approaches are typically preferred.
 *
 * HackerRank (related): Pangram
 *   (Your project references: "3 Months Preparation Kit —Pangram)
 *
 * @see  https://www.hackerrank.com/challenges/three-month-preparation-kit-pangrams
 * @author  Drew Mayberry
 * @since   2025-09-15
 */

import java.util.HashMap;

public class Pangram {

    public static void main(String[] args) {
        String s1 = "We promptly judged antique ivory buckles for the next prize"; // Pangram
        String s2 = "We promptly judged antique kles for the next prize"; // Not Pangram
        String s3 = "The quick brown fox jumps over the lazy dog"; // Pangram
        String s4 = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"; // Not Pangram

        // Basic HashMap approach
        System.out.println(pangramBasic(s1)); // Pangram
        System.out.println(pangramBasic(s2)); // Not Pangram

        // Boolean-array approach
        System.out.println(pangramBoolean(s1)); // Pangram
        System.out.println(pangramBoolean(s2)); // Not Pangram

        // Bitmask approach
        System.out.println(isPangramBitmask(s1)); // Pangram
        System.out.println(isPangramBitmask(s3)); // Pangram
        System.out.println(isPangramBitmask(s4)); // Not Pangram
    }

    /**
     * Approach 1 — HashMap frequency table.
     * Counts occurrences for each letter; early-exits when 26 distinct letters are
     * seen.
     */
    public static String pangramBasic(String s) {
        var freqMap = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // Normalize to lowercase and keep only ASCII letters.
            if (c >= 'A' && c <= 'Z')
                c = (char) (c + 32);
            if (c < 'a' || c > 'z')
                continue;

            // Count and early-exit when all 26 letters have appeared.
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
            if (freqMap.size() == 26)
                return "Pangram";
        }
        return (freqMap.size() == 26) ? "Pangram" : "Not Pangram";
    }

    /**
     * Approach 2 — Boolean set (26-length array).
     * Marks letters a..z as they appear and counts distinct letters.
     */
    public static String pangramBoolean(String s) {
        boolean[] seen = new boolean[26];
        int distinct = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // Uppercase -> lowercase (ASCII), then filter non-letters.
            if (c >= 'A' && c <= 'Z')
                c = (char) (c + 32);
            if (c < 'a' || c > 'z')
                continue;

            int idx = c - 'a'; // 0..25
            if (!seen[idx]) {
                seen[idx] = true;
                if (++distinct == 26)
                    return "Pangram"; // early exit
            }
        }
        return (distinct == 26) ? "Pangram" : "Not Pangram";
    }

    /**
     * Approach 3 — Bitmask (most optimized).
     * Each letter a..z corresponds to a bit in 'mask'. When all 26 bits are set,
     * the string is a pangram.
     */
    public static String isPangramBitmask(String s) {
        int mask = 0;
        final int FULL = (1 << 26) - 1; // 26 ones in the least-significant bits

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // Normalize to lowercase ASCII; skip non-letters.
            if (c >= 'A' && c <= 'Z')
                c = (char) (c + 32);
            if (c < 'a' || c > 'z')
                continue;

            // Set the bit corresponding to this letter.
            mask |= 1 << (c - 'a');

            // Early exit: all letters have been seen.
            if (mask == FULL)
                return "Pangram";
        }
        return (mask == FULL) ? "Pangram" : "Not Pangram";
    }
}
