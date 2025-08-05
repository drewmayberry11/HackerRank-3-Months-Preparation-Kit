
/**
 * BasicCamelCase4.java
 *
 * A straightforward, “brute-force” demo of splitting and combining
 * CamelCase identifiers. Reads a hard-coded list of test strings,
 * applies the camelCase logic, and prints the output.
 *
 * @author Drew Mayberry
 * @since 2025-08-05
 */
import java.util.ArrayList;
import java.util.List;

public class BasicCamelCase4 {

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

        for (String input : inputs) {
            System.out.printf("%-25s → %s%n",
                    input, camelCase(input));
        }
    }

    /**
     * Parses one instruction line and returns the converted string.
     * Basic implementation using explicit loops and character checks.
     */
    public static String camelCase(String input) {
        String[] parts = input.split(";", 3);
        String op = parts[0]; // "S" or "C"
        String type = parts[1]; // "M", "C", or "V"
        String data = parts[2];

        if ("S".equals(op)) {
            // — SPLIT —
            if ("M".equals(type) && data.endsWith("()")) {
                data = data.substring(0, data.length() - 2);
            }
            List<String> words = new ArrayList<>();
            StringBuilder buf = new StringBuilder();

            for (char c : data.toCharArray()) {
                if (Character.isUpperCase(c)) {
                    words.add(buf.toString().toLowerCase());
                    buf.setLength(0);
                }
                buf.append(c);
            }
            words.add(buf.toString().toLowerCase());
            return String.join(" ", words);

        } else {
            // — COMBINE —
            String[] words = data.split(" ");
            StringBuilder sb = new StringBuilder();

            // first word
            if ("C".equals(type)) {
                // class: capitalize first letter
                sb.append(Character.toUpperCase(words[0].charAt(0)))
                        .append(words[0].substring(1).toLowerCase());
            } else {
                // var or method: all lowercase
                sb.append(words[0].toLowerCase());
            }

            // subsequent words always capitalized
            for (int i = 1; i < words.length; i++) {
                String w = words[i];
                sb.append(Character.toUpperCase(w.charAt(0)))
                        .append(w.substring(1).toLowerCase());
            }

            // append "()" for methods
            if ("M".equals(type)) {
                sb.append("()");
            }
            return sb.toString();
        }
    }
}
