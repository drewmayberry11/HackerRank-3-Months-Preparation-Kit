/**
 * Mars.java
 *
 * Problem:
 * A space explorer repeatedly sends the message "SOS" from Mars to Earth.
 * During transmission, some characters may be altered by cosmic radiation.
 * Given the received message string, determine how many characters differ
 * from the expected repeated "SOS" sequence.
 *
 * Description:
 * Implement two approaches that return the number of corrupted letters:
 *
 * 1) marsExploration(String):
 * - Brute-force method.
 * - Constructs the full expected "SOS" sequence of the same length as the
 * received string.
 * - Compares each character one by one against the expected string.
 * - Time: O(n), Space: O(n).
 *
 * 2) marsExplorationOptimized(String):
 * - Optimized method.
 * - Exploits the fact that the expected pattern repeats every 3 letters.
 * - Uses i % 3 to determine whether the expected letter is 'S' or 'O'.
 * - Time: O(n), Space: O(1).
 *
 * Complexity:
 * Both methods run in linear time O(n), where n = s.length().
 * Brute-force requires O(n) extra space (building the expected string),
 * while the optimized approach requires only O(1).
 *
 * Notes:
 * - Input contains only uppercase letters [A-Z].
 * - The message length is always a multiple of 3.
 * - For interviews, the optimized i % 3 approach is preferred.
 *
 * HackerRank (related): Mars Exploration
 * (Your project references: "3 Months Preparation Kit — Mars Exploration")
 *
 * @see https://www.hackerrank.com/challenges/three-month-preparation-kit-mars-exploration
 * @author Drew Mayberry
 * @since 2025-09-17
 */
public class Mars {

    public static void main(String[] args) {
        String s1 = "SOSTOT"; // Example with 1 corrupted letter
        String s2 = "SOSSPSSQSSOR"; // Example with 3 corrupted letters

        System.out.println(marsExploration(s1));
        System.out.println(marsExplorationOptimized(s1));

        System.out.println(marsExploration(s2));
        System.out.println(marsExplorationOptimized(s2));
    }

    /**
     * Brute-force method:
     * Builds the entire expected SOS string of the same length and compares
     * each character.
     */
    public static int marsExploration(String s) {
        int changed = 0;

        // Step 1: Build expected string
        StringBuilder expected = new StringBuilder();
        int repeats = s.length() / 3;
        for (int i = 0; i < repeats; i++) {
            expected.append("SOS");
        }

        // Step 2: Compare character by character
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != expected.charAt(i)) {
                changed++;
            }
        }

        return changed;
    }

    /**
     * Optimized method:
     * Uses modulo (%) to cycle through the repeating "SOS" pattern without
     * building a new string.
     */
    public static int marsExplorationOptimized(String s) {
        int changed = 0;
        String pattern = "SOS";

        for (int i = 0; i < s.length(); i++) {
            char expected = pattern.charAt(i % 3); // cycle S → O → S
            if (s.charAt(i) != expected) {
                changed++;
            }
        }

        return changed;
    }
}
