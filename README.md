# 🎓 Student Management System

A **menu-driven, console-based** student record management application built in **Core Java**. Designed as an MCA mini project to demonstrate the four pillars of Object-Oriented Programming, the Java Collection Framework, and robust exception handling — all without any third-party libraries.

---

## 📋 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Project Structure](#project-structure)
- [Class Architecture](#class-architecture)
- [OOP Concepts Demonstrated](#oop-concepts-demonstrated)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Compile](#compile)
  - [Run](#run)
- [Menu Reference](#menu-reference)
- [Sample Output](#sample-output)
- [Input Validation Rules](#input-validation-rules)
- [Exception Handling](#exception-handling)
- [Limitations](#limitations)
- [Future Enhancements](#future-enhancements)

---

## Overview

The **Student Management System** allows an administrator to manage student academic records entirely in memory during a session. It provides a clean numbered menu for performing all standard CRUD operations on student data, with field-level input validation and meaningful error messages at every step.

> **Note:** All records are stored in memory only. Data will be lost when the program exits. No file or database storage is used.

---

## Features

| # | Feature | Description |
|---|---------|-------------|
| 1 | **Add Student** | Register a new student with validated ID, name, age, course, and marks |
| 2 | **Display All** | View all records in a formatted, aligned table |
| 3 | **Search by ID** | Look up a specific student by their unique ID |
| 4 | **Update** | Modify a student's name, age, course, and marks atomically |
| 5 | **Delete** | Remove a student record with a confirmation prompt |
| 6 | **Student Count** | Display the total number of records currently stored |
| 7 | **Exit** | Clean termination with Scanner closed and farewell message |

---

## Project Structure

```
Student Management System1/
├── src/
│   ├── Person.java                    # Abstract superclass (name, age)
│   ├── Student.java                   # Extends Person; adds ID, course, marks
│   ├── RecordOperations.java          # Interface defining the CRUD contract
│   ├── StudentManager.java            # Implements RecordOperations; owns ArrayList<Student>
│   ├── InputValidator.java            # Static utility for safe console input
│   ├── DuplicateStudentException.java # Checked exception — duplicate student ID
│   ├── StudentNotFoundException.java  # Checked exception — student ID not found
│   └── Main.java                      # Entry point; menu loop and top-level I/O
├── bin/                               # Compiled .class files (generated)
└── docs/
    └── ProjectDocumentation.md        # Full academic documentation
```

---

## Class Architecture

```
                    ┌─────────────────┐
                    │     Person      │  (abstract)
                    │─────────────────│
                    │ - name: String  │
                    │ - age: int      │
                    │─────────────────│
                    │ +getName()      │
                    │ +getAge()       │
                    │ +displayInfo()  │ ← abstract
                    └────────┬────────┘
                             │ extends
                    ┌────────▼────────┐
                    │     Student     │
                    │─────────────────│
                    │ - studentId:int │
                    │ - course:String │
                    │ - marks: double │
                    │─────────────────│
                    │ +displayInfo()  │ ← @Override
                    │ +toString()     │ ← @Override
                    └─────────────────┘

  ┌──────────────────────┐          ┌──────────────────────────────────┐
  │   RecordOperations   │◄─────────│          StudentManager          │
  │  (interface)         │implements│──────────────────────────────────│
  │──────────────────────│          │ - studentList: ArrayList<Student> │
  │ +addStudent()        │          │ +addStudent()                    │
  │ +displayAllStudents()│          │ +displayAllStudents()            │
  │ +searchStudentById() │          │ +searchStudentById()             │
  │ +updateStudent()     │          │ +updateStudent()                 │
  │ +deleteStudent()     │          │ +deleteStudent()                 │
  │ +getStudentCount()   │          │ +getStudentCount()               │
  └──────────────────────┘          └──────────────────────────────────┘
         ▲ declared as interface type
         │
  ┌──────┴──────┐
  │    Main     │ ─── uses ─── InputValidator
  │─────────────│
  │ main()      │
  │ printMenu() │
  └─────────────┘
```

---

## OOP Concepts Demonstrated

| Concept | Where Applied |
|---------|--------------|
| **Encapsulation** | All fields in `Person` and `Student` are `private`; accessed only via getters/setters |
| **Inheritance** | `Student extends Person`, inheriting `name`, `age`, and their accessors |
| **Abstraction** | `Person` is `abstract`; `RecordOperations` is a pure-contract interface |
| **Polymorphism** | `manager` declared as `RecordOperations` but holds `StudentManager` at runtime; `displayInfo()` and `toString()` overridden in `Student` |
| **Constructor Overloading** | `Student` has two constructors — 5-arg and 4-arg (marks defaults to `0.0`) |
| **`this` keyword** | Used in constructors and setters to distinguish instance fields from parameters |
| **`super` keyword** | `Student`'s constructor calls `super(name, age)` to initialise `Person` fields |
| **`toString()` override** | `Student.toString()` returns a formatted table row via `String.format()` |
| **Interface programming** | `Main.java` is decoupled from `StudentManager` — uses only the `RecordOperations` type |

### Java Collection Framework

| Feature | Used In | Purpose |
|---------|---------|---------|
| `ArrayList<Student>` | `StudentManager` | Primary in-memory data store |
| `add()` | `addStudent()` | Insert a new student |
| `size()` | `getStudentCount()`, `displayAllStudents()` | Record count |
| `get(i)` | Search, display loops | Index-based access |
| `Iterator<Student>` | `deleteStudent()` | Safe removal during traversal |
| `iterator.remove()` | `deleteStudent()` | Avoids `ConcurrentModificationException` |

---

## Getting Started

### Prerequisites

| Requirement | Minimum Version |
|-------------|-----------------|
| JDK | Java 8 or later |
| OS | Windows 10/11, Linux, or macOS |
| Terminal | Any 80-column console/terminal |

### Compile

From the project root directory:

```bash
javac -d bin src/*.java
```

### Run

```bash
java -cp bin Main
```

---

## Menu Reference

```
==================================================
         STUDENT MANAGEMENT SYSTEM
==================================================
  1. Add New Student
  2. Display All Students
  3. Search Student by ID
  4. Update Student Information
  5. Delete Student Record
  6. Display Total Student Count
  7. Exit
==================================================
```

---

## Sample Output

**Adding a student:**
```
  --- Add New Student ---
  Enter Student ID    : 101
  Enter Full Name     : Alice Johnson
  Enter Age           : 22
  Enter Course        : MCA
  Enter Marks (0-100) : 88.5

  [Success] Student added successfully.
  | 101   | Alice Johnson        | 22   | MCA                  | 88.50  |
```

**Displaying all records:**
```
+-------+----------------------+------+----------------------+--------+
| ID    | Name                 | Age  | Course               | Marks  |
+-------+----------------------+------+----------------------+--------+
| 101   | Alice Johnson        | 22   | MCA                  | 88.50  |
| 102   | Bob Smith            | 23   | BCA                  | 76.00  |
+-------+----------------------+------+----------------------+--------+
  Total records: 2
```

**Student not found:**
```
  Enter Student ID to search: 999
  [Error] No student found with ID: 999
```

---

## Input Validation Rules

| Field | Rule |
|-------|------|
| **Student ID** | Positive integer (>= 1); must be unique |
| **Name** | Non-blank string |
| **Age** | Integer between **1** and **120** |
| **Course** | Non-blank string |
| **Marks** | Decimal between **0.0** and **100.0** (inclusive) |
| **Menu choice** | Integer between **1** and **7** |

Non-numeric input is caught via `InputMismatchException` — the application re-prompts instead of crashing.

---

## Exception Handling

### Custom Checked Exceptions

| Exception | Thrown By | Reason |
|-----------|-----------|--------|
| `DuplicateStudentException` | `addStudent()` | A student with the same ID already exists |
| `StudentNotFoundException` | `searchStudentById()`, `updateStudent()`, `deleteStudent()` | No record matches the given ID |

Both are **checked exceptions**, forcing callers to handle them explicitly at compile time.

### Standard Exception Handling

| Scenario | Exception | Handled In |
|----------|-----------|------------|
| Non-numeric keyboard input | `InputMismatchException` | `InputValidator.readInt()` / `readDouble()` |

---

## Limitations

- No persistence — records are lost when the application exits
- Single user — no multi-user or authentication support
- Search by ID only — no name or course search capability
- No sorting or filtering — records displayed in insertion order
- No reporting — no grade summaries, toppers list, or statistics
- No pagination — all records displayed at once

---

## Future Enhancements

- File-based persistence using `FileReader` / `FileWriter`
- Database integration with JDBC and MySQL
- JavaFX or Swing GUI for a richer interface
- Multi-field search (name, course, marks range)
- Sorting using `Comparator`
- Automatic grade computation (A, B, C, D, F)
- Admin authentication with password protection
- CSV export for spreadsheet compatibility

---

## License

This project is developed for academic purposes as an **MCA Mini Project**. Feel free to use it as a learning reference.
