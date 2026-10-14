/**
 * Person.java
 *
 * Abstract superclass representing a generic person with a name and age.
 *
 * OOP concepts demonstrated:
 *   - Abstraction  : declared abstract; cannot be instantiated directly
 *   - Encapsulation: private fields exposed through public getters/setters
 *   - Inheritance  : Student extends Person
 *   - Polymorphism : displayInfo() is overridden in subclasses
 *   - 'this' keyword used in constructors and setters
 */
public abstract class Person {

    // Private fields — accessible only through getters and setters (encapsulation)
    private String name;
    private int    age;

    /**
     * Parameterized constructor for Person.
     * Uses 'this' to distinguish instance fields from parameters.
     *
     * @param name the person's full name
     * @param age  the person's age
     */
    public Person(String name, int age) {
        this.name = name;
        this.age  = age;
    }

    // -------------------------------------------------------------------------
    // Getters
    // -------------------------------------------------------------------------

    /** Returns the person's name. */
    public String getName() {
        return name;
    }

    /** Returns the person's age. */
    public int getAge() {
        return age;
    }

    // -------------------------------------------------------------------------
    // Setters
    // -------------------------------------------------------------------------

    /**
     * Sets the person's name.
     *
     * @param name the new name (must not be null or blank)
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the person's age.
     *
     * @param age the new age (must be a positive integer)
     */
    public void setAge(int age) {
        this.age = age;
    }

    // -------------------------------------------------------------------------
    // Abstract method — subclasses must provide their own display logic
    // -------------------------------------------------------------------------

    /**
     * Displays the person's information.
     * Each subclass overrides this to include its own specific fields.
     */
    public abstract void displayInfo();

    /**
     * Returns a string representation of the Person.
     * Overridden by Student to include additional fields.
     *
     * @return formatted string with name and age
     */
    @Override
    public String toString() {
        return "Name: " + name + ", Age: " + age;
    }
}
