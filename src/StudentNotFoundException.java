/**
 * StudentNotFoundException.java
 *
 * Custom checked exception thrown when a student record is not found during
 * search, update, or delete operations.
 *
 * Using a checked exception here forces callers to handle the "not found"
 * case explicitly, which produces more robust error handling.
 */
public class StudentNotFoundException extends Exception {

    /** The student ID that was not found. */
    private final int studentId;

    /**
     * Constructs a StudentNotFoundException with a descriptive message
     * and the missing student ID.
     *
     * @param studentId the ID that could not be located
     */
    public StudentNotFoundException(int studentId) {
        super("No student found with ID: " + studentId);
        this.studentId = studentId;
    }

    /**
     * Returns the student ID that was not found.
     *
     * @return the missing student ID
     */
    public int getStudentId() {
        return studentId;
    }
}
