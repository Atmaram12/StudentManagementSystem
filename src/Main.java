import java.util.Scanner;

/**
 * Main.java
 *
 * Entry point of the Student Management System.
 *
 * Responsibilities:
 *   - Display the main menu
 *   - Accept and validate the user's choice
 *   - Collect field-level input for each operation
 *   - Call StudentManager (through the RecordOperations interface)
 *   - Handle all exceptions at the application boundary
 *   - Keep the menu loop running until the user chooses Exit
 *
 * OOP concept demonstrated:
 *   - Programming to an interface: 'manager' is declared as RecordOperations,
 *     not StudentManager, so the menu loop is decoupled from the implementation.
 */
public class Main {

    // Separator constants for consistent console appearance
    private static final String DOUBLE_LINE =
            "==================================================";
    private static final String SINGLE_LINE =
            "--------------------------------------------------";

    /**
     * Application entry point.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {

        // Single Scanner instance shared across all input operations
        Scanner scanner = new Scanner(System.in);

        // Declared as the interface type — demonstrates polymorphism
        RecordOperations manager = new StudentManager();

        System.out.println("\n" + DOUBLE_LINE);
        System.out.println("   Welcome to the Student Management System");
        System.out.println("   NOTE: All records are stored in memory only.");
        System.out.println("         Records will be lost when the program exits.");
        System.out.println(DOUBLE_LINE);

        boolean running = true;

        while (running) {
            printMenu();

            int choice = InputValidator.readInt(scanner, "Enter your choice: ");

            System.out.println(SINGLE_LINE);

            switch (choice) {

                case 1:
                    handleAddStudent(scanner, manager);
                    break;

                case 2:
                    handleDisplayAll(manager);
                    break;

                case 3:
                    handleSearch(scanner, manager);
                    break;

                case 4:
                    handleUpdate(scanner, manager);
                    break;

                case 5:
                    handleDelete(scanner, manager);
                    break;

                case 6:
                    handleCount(manager);
                    break;

                case 7:
                    running = false;
                    break;

                default:
                    System.out.println("  [Error] Invalid choice. Please enter a number between 1 and 7.");
            }
        }

        // Exit message
        System.out.println("\n" + DOUBLE_LINE);
        System.out.println("   Thank you for using the Student Management System.");
        System.out.println("   Goodbye!");
        System.out.println(DOUBLE_LINE);

        // Close the Scanner to release the System.in resource
        scanner.close();
    }

    // =========================================================================
    // Menu display
    // =========================================================================

    /** Prints the main menu to the console. */
    private static void printMenu() {
        System.out.println("\n" + DOUBLE_LINE);
        System.out.println("         STUDENT MANAGEMENT SYSTEM");
        System.out.println(DOUBLE_LINE);
        System.out.println("  1. Add New Student");
        System.out.println("  2. Display All Students");
        System.out.println("  3. Search Student by ID");
        System.out.println("  4. Update Student Information");
        System.out.println("  5. Delete Student Record");
        System.out.println("  6. Display Total Student Count");
        System.out.println("  7. Exit");
        System.out.println(DOUBLE_LINE);
    }

    // =========================================================================
    // Operation handlers
    // =========================================================================

    /**
     * Handles the Add New Student menu option.
     * Collects all student fields with validation, then calls addStudent().
     *
     * @param scanner the console Scanner
     * @param manager the RecordOperations implementation
     */
    private static void handleAddStudent(Scanner scanner, RecordOperations manager) {
        System.out.println("\n  --- Add New Student ---");

        // Read and validate Student ID
        int id;
        while (true) {
            id = InputValidator.readInt(scanner, "  Enter Student ID    : ");
            if (InputValidator.isValidStudentId(id)) {
                break;
            }
            System.out.println("  [Error] Student ID must be a positive integer (>= 1).");
        }

        // Read non-blank name
        String name = InputValidator.readNonBlankString(scanner, "  Enter Full Name     : ");

        // Read and validate age
        int age;
        while (true) {
            age = InputValidator.readInt(scanner, "  Enter Age           : ");
            if (InputValidator.isValidAge(age)) {
                break;
            }
            System.out.println("  [Error] Age must be between 1 and 120.");
        }

        // Read non-blank course
        String course = InputValidator.readNonBlankString(scanner, "  Enter Course        : ");

        // Read and validate marks
        double marks;
        while (true) {
            marks = InputValidator.readDouble(scanner, "  Enter Marks (0-100) : ");
            if (InputValidator.isValidMarks(marks)) {
                break;
            }
            System.out.println("  [Error] Marks must be between 0.0 and 100.0.");
        }

        // Create the Student object and attempt to add it
        Student student = new Student(id, name, age, course, marks);

        try {
            manager.addStudent(student);
            System.out.println("\n  [Success] Student added successfully.");
            System.out.println("  " + student.toString());
        } catch (DuplicateStudentException e) {
            // Business-rule violation — inform the user without crashing
            System.out.println("\n  [Error] " + e.getMessage());
        }
    }

    /**
     * Handles the Display All Students menu option.
     *
     * @param manager the RecordOperations implementation
     */
    private static void handleDisplayAll(RecordOperations manager) {
        System.out.println("\n  --- All Student Records ---");
        manager.displayAllStudents();
    }

    /**
     * Handles the Search Student by ID menu option.
     *
     * @param scanner the console Scanner
     * @param manager the RecordOperations implementation
     */
    private static void handleSearch(Scanner scanner, RecordOperations manager) {
        System.out.println("\n  --- Search Student by ID ---");
        int id = InputValidator.readInt(scanner, "  Enter Student ID to search: ");

        try {
            Student found = manager.searchStudentById(id);
            System.out.println("\n  [Found] Student record:");
            System.out.println("  +-------+----------------------+------+----------------------+--------+");
            System.out.printf("  | %-5s | %-20s | %-4s | %-20s | %-6s |%n",
                    "ID", "Name", "Age", "Course", "Marks");
            System.out.println("  +-------+----------------------+------+----------------------+--------+");
            System.out.println("  " + found.toString());
            System.out.println("  +-------+----------------------+------+----------------------+--------+");

            // Demonstrate polymorphism — calls the overridden displayInfo()
            // from Student via the Person reference
            System.out.println();
            found.displayInfo();

        } catch (StudentNotFoundException e) {
            System.out.println("\n  [Error] " + e.getMessage());
        }
    }

    /**
     * Handles the Update Student Information menu option.
     *
     * All new field values are collected and validated BEFORE updateStudent()
     * is called. This guarantees no partial updates occur when input is invalid.
     *
     * @param scanner the console Scanner
     * @param manager the RecordOperations implementation
     */
    private static void handleUpdate(Scanner scanner, RecordOperations manager) {
        System.out.println("\n  --- Update Student Information ---");
        int id = InputValidator.readInt(scanner, "  Enter Student ID to update: ");

        // First verify the student exists
        try {
            Student existing = manager.searchStudentById(id);
            System.out.println("\n  Current record:");
            System.out.println("  " + existing.toString());
            System.out.println("\n  Enter new values (leave blank = re-enter, all fields are required):");
        } catch (StudentNotFoundException e) {
            System.out.println("\n  [Error] " + e.getMessage());
            return;
        }

        // Collect and validate all new values
        // New name
        String newName = InputValidator.readNonBlankString(scanner, "  New Full Name     : ");

        // New age
        int newAge;
        while (true) {
            newAge = InputValidator.readInt(scanner, "  New Age           : ");
            if (InputValidator.isValidAge(newAge)) {
                break;
            }
            System.out.println("  [Error] Age must be between 1 and 120.");
        }

        // New course
        String newCourse = InputValidator.readNonBlankString(scanner, "  New Course        : ");

        // New marks
        double newMarks;
        while (true) {
            newMarks = InputValidator.readDouble(scanner, "  New Marks (0-100) : ");
            if (InputValidator.isValidMarks(newMarks)) {
                break;
            }
            System.out.println("  [Error] Marks must be between 0.0 and 100.0.");
        }

        // All values validated — perform the atomic update
        try {
            manager.updateStudent(id, newName, newAge, newCourse, newMarks);
            System.out.println("\n  [Success] Student record updated successfully.");

            // Display the updated record
            Student updated = manager.searchStudentById(id);
            System.out.println("  Updated record: " + updated.toString());

        } catch (StudentNotFoundException e) {
            // This should not normally happen here because we already verified
            // existence above, but we handle it defensively
            System.out.println("\n  [Error] " + e.getMessage());
        }
    }

    /**
     * Handles the Delete Student Record menu option.
     * Asks for confirmation before deleting.
     *
     * @param scanner the console Scanner
     * @param manager the RecordOperations implementation
     */
    private static void handleDelete(Scanner scanner, RecordOperations manager) {
        System.out.println("\n  --- Delete Student Record ---");
        int id = InputValidator.readInt(scanner, "  Enter Student ID to delete: ");

        // First verify the record exists and show it to the user
        try {
            Student toDelete = manager.searchStudentById(id);
            System.out.println("\n  Record to be deleted:");
            System.out.println("  " + toDelete.toString());

            // Confirmation step
            String confirm = InputValidator.readNonBlankString(
                    scanner, "\n  Are you sure you want to delete this record? (yes/no): ");

            if (confirm.equalsIgnoreCase("yes")) {
                manager.deleteStudent(id);
                System.out.println("\n  [Success] Student record with ID " + id + " has been deleted.");
            } else {
                System.out.println("\n  [Info] Delete operation cancelled.");
            }

        } catch (StudentNotFoundException e) {
            System.out.println("\n  [Error] " + e.getMessage());
        }
    }

    /**
     * Handles the Display Total Student Count menu option.
     *
     * @param manager the RecordOperations implementation
     */
    private static void handleCount(RecordOperations manager) {
        System.out.println("\n  --- Total Student Count ---");
        int count = manager.getStudentCount();
        System.out.println("  Total number of student records: " + count);
    }
}
