/**
 * File: FlippingBits.java
 * Author: Drew Mayberry
 * Date: 9-10-2025
 * 
 * Purpose:
 * Solve the "Flipping Bits" problem from HackerRank.
 *
 * Problem:
 * Given a 32-bit unsigned integer, flip all its bits (0 → 1, 1 → 0)
 * and return the resulting unsigned 32-bit value.
 *
 * Details:
 * - Input is treated as a 32-bit unsigned number, but Java does not have
 * an unsigned int type. We use long (64-bit) to hold the result.
 * - Output must be in the range [0, 4294967295].
 *
 * Approach:
 * Two equivalent solutions are provided:
 * 1) flippingBits – Uses XOR (^) with a 32-bit mask (0xFFFFFFFFL).
 * 2) flippingBits2 – Uses bitwise NOT (~) and masks with 0xFFFFFFFFL.
 *
 * Complexity:
 * - Time Complexity: O(1) (constant number of bitwise operations)
 * - Space Complexity: O(1)
 *
 * References:
 * HackerRank Problem:
 * https://www.hackerrank.com/challenges/three-month-preparation-kit-flipping-bits
 */
public class FlippingBits {

    public static void main(String[] args) {
        int num = 1;

        System.out.println("=== Flipping Bits with XOR ===");
        System.out.println("Input: " + num + " → Output: " + flippingBits(num));

        System.out.println("\n=== Flipping Bits with ~ and Mask ===");
        System.out.println("Input: " + num + " → Output: " + flippingBits2(num));
    }

    /**
     * Flips all 32 bits of the input using XOR with a mask.
     *
     * @param n The input number (promoted to long for unsigned correctness)
     * @return The 32-bit flipped result as an unsigned value in a long
     *
     *         Example:
     *         n = 1 → binary: 000...0001
     *         mask = 0xFFFFFFFFL (32 ones)
     *         mask ^ n = 111...1110 → 4294967294
     */
    public static long flippingBits(long n) {
        long mask = 0xFFFFFFFFL; // Mask with lower 32 bits = 1, upper 32 bits = 0
        return mask ^ n; // XOR flips each bit where mask has 1s
    }

    /**
     * Flips all 32 bits using bitwise NOT (~) and a mask to clear upper 32 bits.
     *
     * @param n The input number (long)
     * @return The 32-bit flipped result as an unsigned value in a long
     *
     *         Explanation:
     *         ~n → Flips all 64 bits of n
     *         & mask → Zeros out bits above position 31 (keeps only lower 32
     *         flipped bits)
     */
    public static long flippingBits2(long n) {
        return ~n & 0xFFFFFFFFL;
    }
}
