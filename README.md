Student Record Management System

Overview

The Student Record Management System is a Java console-based application that allows users to manage student records efficiently. The system supports adding, searching, updating, deleting, displaying, reporting, and backing up student information. It uses the Strategy Design Pattern to support multiple storage mechanisms, including text files, binary files, and object serialization.


Features

- Add new student records
- Search students by ID
- Update student information
- Delete student records
- Display all students
- Generate GPA reports
- Create data backups
- Persistent data storage
- Multiple storage strategies


Technologies Used

- Java
- Object-Oriented Programming (OOP)
- Java Collections Framework
- File Handling
- Serialization
- Strategy Design Pattern


Project Structure

Student.java

Represents a student record.

Attributes:

- ID
- Name
- Department
- GPA

Responsibilities:

- Store student information
- Provide getters and setters
- Format student output

StorageStrategy.java

Defines a common interface for data storage.

Methods:

- save()
- load()

Responsibilities:

- Provide abstraction for storage implementations

TextStorageStrategy.java

Stores student records in text format.

Responsibilities:

- Save student data as comma-separated values
- Load student records from text files

BinaryStorageStrategy.java

Stores student records in binary format.

Responsibilities:

- Save records using DataOutputStream
- Load records using DataInputStream

SerializationStorageStrategy.java

Stores student records using Java serialization.

Responsibilities:

- Serialize student objects
- Deserialize student objects

StudentRecordManager.java

Manages all student operations.

Responsibilities:

- Add students
- Search students
- Update students
- Delete students
- Display all records
- Generate reports
- Create backups
- Handle file initialization and persistence

Main.java

Provides the user interface.

Responsibilities:

- Display menu options
- Accept user input
- Invoke StudentRecordManager methods
- Handle user interactions


Menu Options

1. Add Student
2. Search Student
3. Update Student
4. Delete Student
5. Display All Students
6. Generate Report
7. Backup Data
8. Exit


Report Information

The report feature displays:

- Total number of students
- Maximum GPA
- Minimum GPA
- Average GPA


Backup Feature

The system can create a backup copy of the active student database file.

Backup Location:

data/backup.dat



Design Pattern Used

Strategy Pattern

The StorageStrategy interface allows different storage implementations to be used interchangeably:

- TextStorageStrategy
- BinaryStorageStrategy
- SerializationStorageStrategy

Benefits:

- Flexibility
- Maintainability
- Extensibility



How to Run

1. Compile all Java files.

javac *.java

2. Run the application.

java Main

3. Select options from the menu.


Sample Student Record

ID: ST001
Name: John Smith
Department: Computer Science
GPA: 3.85


Author

Student Record Management System Project

Developed using Java, File Handling, Serialization, and the Strategy Design Pattern.