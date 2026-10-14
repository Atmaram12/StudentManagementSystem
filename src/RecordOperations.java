/**
 * RecordOperations.java
 *
 * Interface defining the contract for all CRUD operations on student records.
 *
 * OOP concepts demonstrated:
 *   - Abstraction  : method signatures with no implementation
 *   - Polymorphism : StudentManager provides the concrete behaviour
 *
 * Design decision: Using an interface rather than an abstract class keeps
 * the contract decoupled from any particular storage implementation.
 * StudentManager implements this interface and can later be swapped for a
 * file-backed or database-backed implementation without changing Main.java.
 */
public interface RecordOperations {

    /**
     * Adds a new student to the system.
     *
     * @param student the Student object to add
     * @throws DuplicateStudentException if a student with the same ID exists
     */
    void addStudent(Student student) throws DuplicateStudentException;

    /**
     * Displays all student records in a tabular format.
     */
    void displayAllStudents();

    /**
     * Searches for a student by their unique ID.
     *
     * @param studentId the ID to search
     * @return the matching Student object
     * @throws StudentNotFoundException if no record matches the given ID
     */
    Student searchStudentById(int studentId) throws StudentNotFoundException;

    /**
     * Updates an existing student's mutable fields.
     *
     * @param studentId the ID of the student to update
     * @param newName   the replacement name
     * @param newAge    the replacement age
     * @param newCourse the replacement course
     * @param newMarks  the replacement marks
     * @throws StudentNotFoundException  if the student ID is not found
     */
    void updateStudent(int studentId, String newName, int newAge,
                       String newCourse, double newMarks)
            throws StudentNotFoundException;

    /**
     * Deletes the student record matching the given ID.
     *
     * @param studentId the ID of the student to delete
     * @throws StudentNotFoundException if the student ID is not found
     */
    void deleteStudent(int studentId) throws StudentNotFoundException;

    /**
     * Returns the total number of student records currently in the system.
     *
     * @return the count of student records
     */
    int getStudentCount();
}
