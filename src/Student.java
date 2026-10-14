/**
 * Student.java
 *
 * Represents a single student record.
 *
 * OOP concepts demonstrated:
 *   - Inheritance   : extends Person (inherits name and age)
 *   - Encapsulation : private fields with public getters/setters
 *   - Polymorphism  : overrides displayInfo() and toString() from Person
 *   - 'super' keyword to invoke the Person constructor
 *   - 'this' keyword in setters and constructors
 *   - Constructor overloading (two constructors provided)
 */
public class Student extends Person {

    // -------------------------------------------------------------------------
    // Private fields (encapsulation)
    // -------------------------------------------------------------------------
    private int    studentId;
    private String course;
    private double marks;

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Full parameterized constructor.
     * Uses 'super' to initialise inherited Person fields.
     *
     * @param studentId unique positive integer identifier
     * @param name      full name of the student
     * @param age       age of the student
     * @param course    enrolled course name
     * @param marks     marks obtained (0.0 – 100.0)
     */
    public Student(int studentId, String name, int age,
                   String course, double marks) {
        super(name, age);            // invoke Person constructor via 'super'
        this.studentId = studentId;
        this.course    = course;
        this.marks     = marks;
    }

    /**
     * Overloaded constructor — creates a Student with marks defaulting to 0.0.
     * Demonstrates constructor overloading.
     *
     * @param studentId unique positive integer identifier
     * @param name      full name of the student
     * @param age       age of the student
     * @param course    enrolled course name
     */
    public Student(int studentId, String name, int age, String course) {
        this(studentId, name, age, course, 0.0);   // delegates to full constructor
    }

    // -------------------------------------------------------------------------
    // Getters
    // -------------------------------------------------------------------------

    /** Returns the student's unique ID. */
    public int getStudentId() {
        return studentId;
    }

    /** Returns the student's enrolled course. */
    public String getCourse() {
        return course;
    }

    /** Returns the student's marks. */
    public double getMarks() {
        return marks;
    }

    // -------------------------------------------------------------------------
    // Setters
    // -------------------------------------------------------------------------

    /**
     * Updates the course.
     *
     * @param course the new course name
     */
    public void setCourse(String course) {
        this.course = course;
    }

    /**
     * Updates the marks.
     *
     * @param marks the new marks value (0.0 – 100.0)
     */
    public void setMarks(double marks) {
        this.marks = marks;
    }

    // -------------------------------------------------------------------------
    // Overridden methods (runtime polymorphism)
    // -------------------------------------------------------------------------

    /**
     * Displays a formatted summary of all student fields.
     * Overrides the abstract displayInfo() method declared in Person.
     */
    @Override
    public void displayInfo() {
        System.out.println(this.toString());
    }

    /**
     * Returns a human-readable string representation of this Student.
     * Overrides toString() inherited from Person / Object.
     *
     * @return formatted multi-field string
     */
    @Override
    public String toString() {
        return String.format(
            "| %-5d | %-20s | %-4d | %-20s | %-6.2f |",
            studentId, getName(), getAge(), course, marks
        );
    }
}
