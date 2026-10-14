import java.util.Scanner;

/**
 * InputValidator.java
 *
 * Centralises all console input reading and validation logic.
 *
 * Responsibilities:
 *   - Safe integer reading (recovers from InputMismatchException)
 *   - Safe double reading
 *   - Safe non-blank string reading
 *   - Business-rule validators for age, marks, and student ID
 *
 * Design decision: All methods are static because InputValidator holds no
 * state — it is a utility class. This avoids unnecessary object creation.
 */
public class InputValidator {

    // -------------------------------------------------------------------------
    // Safe input readers
    // -------------------------------------------------------------------------

    /**
     * Reads a valid integer from the console, re-prompting on invalid input.
     * Handles InputMismatchException internally so callers never crash on
     * non-numeric input.
     *
     * @param scanner the shared Scanner for System.in
     * @param prompt  the message displayed to the user
     * @return the integer entered by the user
     */
    public static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = scanner.nextInt();
                scanner.nextLine();   // consume the leftover newline
                return value;
            } catch (java.util.InputMismatchException e) {
                scanner.nextLine();   // discard the bad input line
                System.out.println("  [Error] Please enter a valid whole number.");
            }
        }
    }

    /**
     * Reads a valid double from the console, re-prompting on invalid input.
     *
     * @param scanner the shared Scanner for System.in
     * @param prompt  the message displayed to the user
     * @return the double entered by the user
     */
    public static double readDouble(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double value = scanner.nextDouble();
                scanner.nextLine();   // consume the leftover newline
                return value;
            } catch (java.util.InputMismatchException e) {
                scanner.nextLine();   // discard the bad input line
                System.out.println("  [Error] Please enter a valid decimal number.");
            }
        }
    }

    /**
     * Reads a non-blank string from the console, re-prompting if the user
     * enters an empty or whitespace-only value.
     *
     * @param scanner the shared Scanner for System.in
     * @param prompt  the message displayed to the user
     * @return the trimmed, non-blank string entered by the user
     */
    public static String readNonBlankString(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("  [Error] This field cannot be empty. Please try again.");
        }
    }

    // -------------------------------------------------------------------------
    // Business-rule validators
    // -------------------------------------------------------------------------

    /**
     * Validates a student ID.
     * A valid student ID must be a positive integer (>= 1).
     *
     * @param id the candidate student ID
     * @return true if valid, false otherwise
     */
    public static boolean isValidStudentId(int id) {
        return id >= 1;
    }

    /**
     * Validates a student's age.
     * A reasonable age is between 1 and 120 (inclusive).
     *
     * @param age the candidate age
     * @return true if valid, false otherwise
     */
    public static boolean isValidAge(int age) {
        return age >= 1 && age <= 120;
    }

    /**
     * Validates a student's marks.
     * Marks must fall between 0.0 and 100.0 (inclusive).
     *
     * @param marks the candidate marks value
     * @return true if valid, false otherwise
     */
    public static boolean isValidMarks(double marks) {
        return marks >= 0.0 && marks <= 100.0;
    }
}
