
/**
 * OptimizedCamelCase4.java
 *
 * A concise, regex-and-stream–based demo of splitting and combining
 * CamelCase identifiers. Reads a hard-coded list of test strings,
 * applies the camelCase logic in one pass, and prints the results.
 *
 * @author Drew Mayberry
 * @since 2025-08-05
 */
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class OptimizedCamelCase4 {

    // Zero-width split before every uppercase letter
    private static final Pattern UPPER_BOUNDARY = Pattern.compile("(?=[A-Z])");

    public static void main(String[] args) {
        String[] inputs = {
                "S;M;plasticCup()",
                "C;V;mobile phone",
                "C;C;coffee machine",
                "S;C;LargeSoftwareBook",
                "S;V;pictureFrame",
                "C;M;white sheet of paper",
                "C;C;mirror"
        };

        Arrays.stream(inputs)
                .forEach(line -> System.out.printf("%-25s → %s%n",
                        line, process(line)));
    }

    /**
     * Parses one instruction line and returns the converted result.
     * Uses regex split for uppercase boundaries and streams for joining.
     */
    private static String process(String input) {
        String[] parts = input.split(";", 3);
        String op = parts[0]; // "S" or "C"
        String type = parts[1]; // "M", "C", or "V"
        String data = parts[2];

        if ("S".equals(op)) {
            // — SPLIT —
            if ("M".equals(type) && data.endsWith("()")) {
                data = data.substring(0, data.length() - 2);
            }
            return Arrays.stream(UPPER_BOUNDARY.split(data))
                    .map(String::toLowerCase)
                    .collect(Collectors.joining(" "));
        } else {
            // — COMBINE —
            String[] words = data.split(" ");
            StringBuilder sb = new StringBuilder();

            // First word handling by type
            if ("C".equals(type)) {
                sb.append(capitalize(words[0])); // Class: Uppercase first
            } else {
                sb.append(words[0].toLowerCase()); // Var/Method: lowercase first
            }

            // Capitalize subsequent words
            for (int i = 1; i < words.length; i++) {
                sb.append(capitalize(words[i]));
            }

            // Append "()" for methods
            if ("M".equals(type)) {
                sb.append("()");
            }
            return sb.toString();
        }
    }

    /**
     * Capitalizes the first character and lowercases the rest.
     */
    private static String capitalize(String w) {
        if (w.isEmpty())
            return w;
        return Character.toUpperCase(w.charAt(0))
                + w.substring(1).toLowerCase();
    }
}
