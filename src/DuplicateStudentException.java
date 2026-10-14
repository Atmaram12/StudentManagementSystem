/**
 * DuplicateStudentException.java
 *
 * Custom checked exception thrown when an attempt is made to add a student
 * whose ID already exists in the system.
 *
 * This is a business-rule violation, so it is a checked exception — callers
 * must explicitly handle or declare it.
 */
public class DuplicateStudentException extends Exception {

    /** The duplicate student ID that triggered this exception. */
    private final int studentId;

    /**
     * Constructs a DuplicateStudentException with a descriptive message
     * and the conflicting student ID.
     *
     * @param studentId the ID that already exists in the system
     */
    public DuplicateStudentException(int studentId) {
        super("Student with ID " + studentId + " already exists.");
        this.studentId = studentId;
    }

    /**
     * Returns the student ID that caused the duplication conflict.
     *
     * @return the duplicate student ID
     */
    public int getStudentId() {
        return studentId;
    }
}
