/**
 * File: DrawingBook.java
 *
 * Problem:
 * A book has n pages. Starting from either the front or the back, how many page
 * turns are needed (minimum) to reach page p? Each turn flips one sheet, i.e.,
 * advances by one two-page spread.
 *
 * Description (spread math → greedy min):
 * - Pages are viewed in spreads: (1), (2,3), (4,5), (6,7), ...
 * - The spread index holding page p is floor(p/2).
 * - Total spreads in an n-page book is floor(n/2).
 * - Turns from front = p/2
 * - Turns from back = (n/2) - (p/2)
 * - Answer = min(front, back)
 *
 * Complexity:
 * Time : O(1)
 * Space : O(1)
 *
 * Notes:
 * - Integer division naturally handles odd/even n and the single last page
 * case.
 * - No loops, no special cases required.
 *
 * Related:
 * HackerRank — Drawing Book
 * 
 * @see Link:
 *      https://www.hackerrank.com/challenges/three-month-preparation-kit-drawing-book
 *      Author: Drew Mayberry
 *      Since : 2025-09-26
 */
public class DrawingBook {

    public static void main(String[] args) {
        int n = 5;
        int p = 3;
        System.out.println(pageCount(n, p)); // -> 1
    }

    public static int pageCount(int n, int p) {
        int fromFront = p / 2;
        int fromBack = (n / 2) - (p / 2);
        return Math.min(fromFront, fromBack);
    }
}
