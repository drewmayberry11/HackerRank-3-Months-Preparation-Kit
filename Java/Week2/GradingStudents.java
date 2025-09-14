import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Program: GradingStudents
 *
 * Description:
 * HackerLand University rounds student grades according to the following rules:
 * 1. Any grade below 38 is failing and is NOT rounded (it stays as-is).
 * 2. For grades >= 38:
 * - Find the next multiple of 5.
 * - If the difference between the grade and that multiple is < 3, round up.
 * - Otherwise, keep the grade as it is.
 *
 * Example:
 * Grade = 84 → next multiple = 85 → difference = 1 → round to 85
 * Grade = 29 → below 38 → stay 29
 * Grade = 57 → next multiple = 60 → difference = 3 → keep 57
 *
 * This program contains:
 * 1. gradingStudents() - fundamental solution using loops and modulus (%).
 * 2. gradingStudentsStream() - modern, functional solution using Java Streams.
 * 3. gradingStudents2() - debugging helper that prints intermediate
 * calculations.
 *
 * Complexity:
 * - Time: O(n) where n = number of grades (single pass)
 * - Space: O(n) because we return a new list with the rounded results
 */
public class GradingStudents {

    public static void main(String[] args) {
        List<Integer> grades = new ArrayList<>(List.of(23, 78, 72, 89, 99, 32, 56, 66, 62, 87, 81, 91));

        System.out.println("=== Fundamental Solution ===");
        List<Integer> rounded = gradingStudents(grades);
        System.out.println("Rounded Grades: " + rounded);

        System.out.println("\n=== Modern Stream Solution ===");
        List<Integer> roundedStream = gradingStudentsStream(grades);
        System.out.println("Rounded Grades: " + roundedStream);

        System.out.println("\n=== Debug Visualization ===");
        gradingStudents2(grades);
    }

    /**
     * Fundamental solution:
     * Uses modulus (%) to find how close a grade is to the next multiple of 5.
     * Rounds only if the remainder is 3 or 4.
     */
    public static List<Integer> gradingStudents(List<Integer> grades) {
        List<Integer> result = new ArrayList<>();

        for (Integer grade : grades) {
            if (grade < 38) {
                // Failing grade - no rounding
                result.add(grade);
                continue;
            }
            if (grade > 100) {
                // Skip invalid grades (not required by HackerRank but good practice)
                continue;
            }

            // Remainder tells us how far we are from the next multiple of 5
            int remainder = grade % 5;

            if (remainder >= 3) {
                // If remainder is 3 or 4, push grade up to next multiple
                grade += (5 - remainder);
            }

            result.add(grade);
        }
        return result;
    }

    /**
     * Modern optimized solution:
     * Uses Java Streams to transform the list in a functional style.
     * Same logic as gradingStudents() but more concise.
     */
    public static List<Integer> gradingStudentsStream(List<Integer> grades) {
        return grades.stream()
                .filter(g -> g <= 100) // filter out invalid grades
                .map(g -> {
                    if (g < 38)
                        return g; // no rounding for failing grades
                    int remainder = g % 5;
                    return (remainder >= 3) ? g + (5 - remainder) : g;
                })
                .collect(Collectors.toList());
    }

    /**
     * Debugging helper:
     * Prints the math behind finding the next multiple of 5 for each grade.
     * Helps visualize how integer division and rounding work.
     */
    public static List<Integer> gradingStudents2(List<Integer> grades) {
        List<Integer> result = new ArrayList<>();

        for (Integer grade : grades) {
            if (grade < 38) {
                System.out.println("Grade " + grade + " is < 38 → No rounding");
                result.add(grade);
                continue;
            }
            if (grade > 100) {
                System.out.println("Grade " + grade + " is > 100 → Skipping");
                continue;
            }

            int quotient = grade / 5; // full "buckets" of 5
            int nextMultiple = (quotient + 1) * 5; // first number in next bucket
            int difference = nextMultiple - grade; // how far we are from that multiple

            // Detailed printout to visualize step-by-step process
            System.out.printf(
                    "Grade: %d | quotient (grade/5): %d | nextMultiple: %d | difference: %d%n",
                    grade, quotient, nextMultiple, difference);

            if (difference < 3) {
                System.out.println("→ Rounding UP to " + nextMultiple);
                result.add(nextMultiple);
            } else {
                System.out.println("→ Keeping original grade: " + grade);
                result.add(grade);
            }
        }

        return result;
    }
}
