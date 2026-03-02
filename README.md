# Student Management System (JDBC)

A console-based Student Management System built using Java and JDBC.

## 📌 Features
- Add Student
- View Students
- Update Student
- Delete Student
- Search by ID
- Search by Name
- Show Average Marks
- Show Topper
- Count Students
- Filter by Marks
- Filter by Course

## 🛠 Technologies Used
- Core Java
- JDBC
- MySQL
- Eclipse

## Sql table creation

CREATE TABLE IF NOT EXISTS students (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    course VARCHAR(100) NOT NULL,
    marks INT NOT NULL
);

