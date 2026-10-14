import java.util.ArrayList;
import java.util.Iterator;

/**
 * StudentManager.java
 *
 * Implements RecordOperations and manages the in-memory list of students.
 *
 * OOP concepts demonstrated:
 *   - Interface implementation (RecordOperations)
 *   - Encapsulation: the ArrayList is private; accessed only through methods
 *   - Polymorphism : callers use the RecordOperations type, not StudentManager
 *
 * Collection Framework usage:
 *   - ArrayList<Student> : the primary data store
 *   - Iterator<Student>  : used for safe removal during delete
 *   - add(), size(), iterator(), and manual traversal
 *
 * Note: All data is held in memory only. Records are lost when the
 * application terminates. No file or database storage is used.
 */
public class StudentManager implements RecordOperations {

    // -------------------------------------------------------------------------
    // Data store — ArrayList<Student> (Collection Framework)
    // -------------------------------------------------------------------------

    /** In-memory list of all student records. */
    private ArrayList<Student> studentList;

    // -------------------------------------------------------------------------
    // Constructor
    // -------------------------------------------------------------------------

    /**
     * Initialises the StudentManager with an empty ArrayList.
     */
    public StudentManager() {
        studentList = new ArrayList<Student>();
    }

    // -------------------------------------------------------------------------
    // RecordOperations implementations
    // -------------------------------------------------------------------------

    /**
     * Adds a new student to the list after verifying ID uniqueness.
     *
     * @param student the Student object to add
     * @throws DuplicateStudentException if a student with the same ID exists
     */
    @Override
    public void addStudent(Student student) throws DuplicateStudentException {
        // Check for an existing record with the same ID
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getStudentId() == student.getStudentId()) {
                throw new DuplicateStudentException(student.getStudentId());
            }
        }
        studentList.add(student);   // Collection Framework: add()
    }

    /**
     * Displays all students in an aligned table format.
     * Shows an informational message when the list is empty.
     */
    @Override
    public void displayAllStudents() {
        if (studentList.size() == 0) {
            System.out.println("\n  No student records found. The list is empty.");
            return;
        }

        printTableHeader();

        // Traverse using a classic for-loop (Iterator shown separately in delete)
        for (int i = 0; i < studentList.size(); i++) {
            System.out.println(studentList.get(i).toString());
        }

        printTableFooter();
        System.out.println("  Total records: " + studentList.size());
    }

    /**
     * Searches for a student by ID.
     *
     * @param studentId the target ID
     * @return the matching Student
     * @throws StudentNotFoundException if no record matches
     */
    @Override
    public Student searchStudentById(int studentId) throws StudentNotFoundException {
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getStudentId() == studentId) {
                return studentList.get(i);
            }
        }
        // No match found — throw custom exception
        throw new StudentNotFoundException(studentId);
    }

    /**
     * Updates a student's mutable fields atomically.
     * All new values are validated before any field is changed,
     * preventing partial updates when validation fails.
     *
     * @param studentId the ID of the student to update
     * @param newName   validated new name
     * @param newAge    validated new age
     * @param newCourse validated new course
     * @param newMarks  validated new marks
     * @throws StudentNotFoundException if the student ID is not found
     */
    @Override
    public void updateStudent(int studentId, String newName, int newAge,
                              String newCourse, double newMarks)
            throws StudentNotFoundException {

        Student student = searchStudentById(studentId);  // throws if not found

        // All validation has already been performed by Main.java before this
        // call, so we apply updates atomically here.
        student.setName(newName);
        student.setAge(newAge);
        student.setCourse(newCourse);
        student.setMarks(newMarks);
    }

    /**
     * Deletes the student with the given ID.
     * Uses an Iterator for safe removal from the ArrayList during traversal.
     *
     * @param studentId the ID of the student to remove
     * @throws StudentNotFoundException if the student ID is not found
     */
    @Override
    public void deleteStudent(int studentId) throws StudentNotFoundException {
        // Iterator<Student> demonstrates Collection Framework usage
        Iterator<Student> iterator = studentList.iterator();
        while (iterator.hasNext()) {
            Student current = iterator.next();
            if (current.getStudentId() == studentId) {
                iterator.remove();   // safe removal using Iterator
                return;
            }
        }
        // Loop completed without a match
        throw new StudentNotFoundException(studentId);
    }

    /**
     * Returns the number of student records using the Collection Framework.
     *
     * @return current count of student records
     */
    @Override
    public int getStudentCount() {
        return studentList.size();   // Collection Framework: size()
    }

    // -------------------------------------------------------------------------
    // Private helper methods for table formatting
    // -------------------------------------------------------------------------

    /** Prints the header row of the student display table. */
    private void printTableHeader() {
        String line = "+-------+----------------------+------+----------------------+--------+";
        System.out.println("\n" + line);
        System.out.printf("| %-5s | %-20s | %-4s | %-20s | %-6s |%n",
                "ID", "Name", "Age", "Course", "Marks");
        System.out.println(line);
    }

    /** Prints the footer separator of the student display table. */
    private void printTableFooter() {
        System.out.println("+-------+----------------------+------+----------------------+--------+");
    }
}
