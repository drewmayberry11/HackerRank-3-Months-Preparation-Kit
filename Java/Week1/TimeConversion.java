/**
 * HackerRank 3 Months Preparation Kit - Week 1: Time Conversion
 * @see Problem URL: https://www.hackerrank.com/challenges/three-month-preparation-kit-time-conversion/problem
 *
 * @author Drew Mayberry
 * @since 2025-08-04
 */
public class TimeConversion {

    /**
     * Main method to demonstrate and test both time conversion implementations.
     * Tests critical edge cases including noon, midnight, and regular AM/PM times.
     */
    public static void main(String[] args) {
        // Test Cases covering key edge cases
        String s1 = "12:01:00PM"; // Noon edge case - should stay 12:01:00
        String s2 = "07:05:45PM"; // Regular PM time - should become 19:05:45
        String s3 = "12:21:45AM"; // Midnight edge case - should become 00:21:45

        System.out.println("==Hacker Rank Week 1 : Time Conversion==\n");

        System.out.println("Optimized Time Conversion Function");
        System.out.println(TimeConversionOptimized(s1));
        System.out.println(TimeConversionOptimized(s2));
        System.out.println(TimeConversionOptimized(s3));

        System.out.println("\nBasic Time Conversion Function");
        System.out.println(timeConversion(s1));
        System.out.println(timeConversion(s2));
        System.out.println(timeConversion(s3));
    }

    /**
     * Converts 12-hour time format to 24-hour military time format using modern Java features.
     * This optimized version uses ternary operators and var declarations for cleaner code.
     *
     * @param s time string in format "HH:MM:SSAM" or "HH:MM:SSPM"
     * @return time string in 24-hour format "HH:MM:SS"
     */
    public static String TimeConversionOptimized(String s) {
        // Determine if this is AM time by checking the last 2 characters
        var isAm = s.endsWith("AM");

        // Remove the AM/PM suffix to get just the time portion
        var timeWithoutPeriod = s.substring(0, s.length() - 2);

        // Split into [hour, minute, second] components
        var timeParts = timeWithoutPeriod.split(":");

        // Parse the hour as an integer for mathematical operations
        int hour = Integer.parseInt(timeParts[0]);

        // Apply conversion logic using nested ternary operators:
        // AM: 12 becomes 0 (midnight), all others stay the same
        // PM: 12 stays 12 (noon), all others add 12
        var convertedHour = isAm
                ? (hour == 12 ? 0 : hour) // AM case
                : (hour == 12 ? hour : hour + 12); // PM case

        // Format with leading zero for hour and reconstruct the time string
        return String.format("%02d:%s:%s", convertedHour, timeParts[1], timeParts[2]);
    }

    /**
     * Converts 12-hour time format to 24-hour military time format using basic approach.
     * This foundational version prioritizes clarity and step-by-step logic over conciseness.
     *
     * @param s time string in format "HH:MM:SSAM" or "HH:MM:SSPM"
     * @return time string in 24-hour format "HH:MM:SS"
     */
    public static String timeConversion(String s) {
        // Extract the AM/PM indicator (last 2 characters)
        String amPmIndicator = s.substring(s.length() - 2);

        // Extract the time part without AM/PM
        String timePart = s.substring(0, s.length() - 2);

        // Split the time into hour, minute, second components
        String[] timeComponents = timePart.split(":");
        int hourValue = Integer.parseInt(timeComponents[0]);
        String minuteValue = timeComponents[1];
        String secondValue = timeComponents[2];

        // Apply conversion rules based on AM/PM
        if (amPmIndicator.equals("AM")) {
            // Special case: 12:XX:XXAM becomes 00:XX:XX (midnight)
            if (hourValue == 12) {
                hourValue = 0;
            }
            // All other AM hours stay the same (1-11 AM remain 1-11)
        } else { // PM case
            // Special case: 12:XX:XXPM stays 12:XX:XX (noon)
            if (hourValue != 12) {
                // All other PM hours get 12 added (1-11 PM become 13-23)
                hourValue = hourValue + 12;
            }
        }

        // Format the hour with leading zero if needed and reconstruct the full time
        String formattedHour = String.format("%02d", hourValue);
        return formattedHour + ":" + minuteValue + ":" + secondValue;
    }
}