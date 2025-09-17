import java.util.ArrayList;
import java.util.Arrays;

/**
 * File: DiagonalDifference.java
 * Challenge: HackerRank — Diagonal Difference
 *
 * Problem:
 * Given a square matrix, calculate the absolute difference between the sums
 * of its primary diagonal (top-left → bottom-right) and secondary diagonal
 * (top-right → bottom-left).
 *
 * Example:
 * Matrix:
 * 11 2 4
 * 4 5 6
 * 10 8 -12
 *
 * Primary diagonal sum = 11 + 5 + (-12) = 4
 * Secondary diagonal sum = 4 + 5 + 10 = 19
 * Absolute difference = |4 - 19| = 15
 *
 * @see https://www.hackerrank.com/challenges/three-month-preparation-kit-diagonal-difference
 * 
 *      Author: Drew Mayberry
 *      Since: 2025-09-15
 */
public class DiagonalDifference {

    public static void main(String[] args) {

        // ==== Version 1: Basic (int[][] matrix) ====
        int[][] matrix = {
                { 11, 2, 4 },
                { 4, 5, 6 },
                { 10, 8, -12 }
        };

        int n = matrix.length;
        int primarySum = 0;
        int secondarySum = 0;

        for (int i = 0; i < n; i++) {
            primarySum += matrix[i][i];
            secondarySum += matrix[i][n - 1 - i];
        }

        int difference = Math.abs(primarySum - secondarySum);

        System.out.println("Basic Array Version");
        System.out.println("Primary Sum: " + primarySum);
        System.out.println("Secondary Sum: " + secondarySum);
        System.out.println("Difference: " + difference);

        // ==== Version 2: Using ArrayList<ArrayList<Integer>> ====
        ArrayList<ArrayList<Integer>> matrix2 = new ArrayList<>();
        matrix2.add(new ArrayList<>(Arrays.asList(11, 2, 4)));
        matrix2.add(new ArrayList<>(Arrays.asList(4, 5, 6)));
        matrix2.add(new ArrayList<>(Arrays.asList(10, 8, -12)));

        int primarySum2 = 0;
        int secondarySum2 = 0;

        for (int i = 0; i < matrix2.size(); i++) {
            primarySum2 += matrix2.get(i).get(i);
            secondarySum2 += matrix2.get(i).get(matrix2.size() - 1 - i);
        }

        int difference2 = Math.abs(primarySum2 - secondarySum2);

        System.out.println("\nArrayList Version");
        System.out.println("Primary Sum: " + primarySum2);
        System.out.println("Secondary Sum: " + secondarySum2);
        System.out.println("Difference: " + difference2);

        // ==== Version 3: Optimized Interview-Ready ====
        int result = diagonalDifference(matrix);
        System.out.println("\nOptimized Interview Version");
        System.out.println("Diagonal Difference: " + result);
    }

    /**
     * Optimized helper function for interviews:
     * Single O(n) loop, no extra space.
     */
    private static int diagonalDifference(int[][] arr) {
        int n = arr.length;
        int primary = 0, secondary = 0;
        for (int i = 0; i < n; i++) {
            primary += arr[i][i];
            secondary += arr[i][n - 1 - i];
        }
        return Math.abs(primary - secondary);
    }
}
