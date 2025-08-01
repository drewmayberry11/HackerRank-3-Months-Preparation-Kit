import java.util.ArrayList;
import java.util.List;

/**
 * Given five positive integers, find the minimum and maximum values that can be calculated by summing exactly 
 * four of the five integers.Then print the respective minimum and maximum values as a single line of 
 * two space-separated long integers. 
 * 
 * File: MiniMaxSum
 * HackerRank 3 Months Preparation Kit - Week 1: Mini Max Sum
 * @see Problem URL: https://www.hackerrank.com/challenges/three-month-preparation-kit-mini-max-sum/problem
 *
 * @author Drew Mayberry
 * @since 2025-07-31
 */
public class MiniMaxSum {

    public static void main(String[] args) {

        List<Integer> arr = new ArrayList<>(List.of(1, 3, 5, 7, 9));
        miniMaxSumBasic(arr);
        miniMaxSumOptimized(arr);

    }

    // Basic 
    public static void miniMaxSumBasic(List<Integer> arr) {

        long min = Integer.MAX_VALUE;
        long max = Integer.MIN_VALUE;

        for (int i = 0; i < arr.size(); i++) {
            long sum = 0;

            for (int j = 0; j < arr.size(); j++) {
                if (i != j) {

                    sum += arr.get(j);
                }
            }
            if (sum > max) {
                max = sum;
            }

            if (sum < min) {
                min = sum;
            }
        }
        System.out.println("Max Sum: " + max + " Min: " + min);
    }

    public static void miniMaxSumOptimized(List<Integer> arr) {
        long totalSum = 0;
        long minElement = Long.MAX_VALUE;
        long maxElement = Long.MIN_VALUE;

        for (int num : arr) {
            totalSum += num;
            minElement = Math.min(num, minElement);
            maxElement = Math.max(num, maxElement);
        }

        long maxSum = totalSum - minElement;
        long minSum = totalSum - maxElement;

        System.out.println("Max: " + maxSum + " Min: " + minSum);

    }
}
