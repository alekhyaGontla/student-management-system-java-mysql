package student_Management_System;

import java.sql.*;
import java.util.Scanner;

public class StudentManagementSystem {

    static Scanner sc = new Scanner(System.in);

    // DATABASE CONNECTION
    public static Connection getConnection() throws Exception {
        String url = "jdbc:mysql://localhost:3306/btm";
        String user = "root";
        String password = "user_password"; 

        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, user, password);
    }

    // ADD STUDENT
    public static void addStudent() {

        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = getConnection();

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Course: ");
            String course = sc.nextLine();

            System.out.print("Enter Marks: ");
            int marks = sc.nextInt();
            sc.nextLine();

            String sql = "INSERT INTO students(name, course, marks) VALUES (?, ?, ?)";

            ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, course);
            ps.setInt(3, marks);

            ps.executeUpdate();

            System.out.println("Student inserted successfully!\n");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                	ps.close();
                }
                if (con != null) {
                	con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // VIEW STUDENTS
    public static void viewStudents() {

        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            st = con.createStatement();

            rs = st.executeQuery("SELECT * FROM students");

            System.out.println("\nID | Name | Course | Marks");
            System.out.println("------------------------------");

            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                        rs.getString("name") + " | " +
                        rs.getString("course") + " | " +
                        rs.getInt("marks")
                );
            }

            System.out.println();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                	rs.close();
                }
                if (st != null) {
                	st.close();
                }
                if (con != null) {
                	con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // UPDATE STUDENT
    public static void updateStudent() {

        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = getConnection();

            System.out.print("Enter Student ID to Update: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter New Marks: ");
            int marks = sc.nextInt();
            sc.nextLine();

            String sql = "UPDATE students SET marks = ? WHERE id = ?";

            ps = con.prepareStatement(sql);
            ps.setInt(1, marks);
            ps.setInt(2, id);

            int rows = ps.executeUpdate();

            if (rows > 0)
                System.out.println("Student updated successfully!\n");
            else
                System.out.println("Student ID not found!\n");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                	ps.close();
                }
                if (con != null) {
                	con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // DELETE STUDENT
    public static void deleteStudent() {

        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = getConnection();

            System.out.print("Enter Student ID to Delete: ");
            int id = sc.nextInt();
            sc.nextLine();

            String sql = "DELETE FROM students WHERE id = ?";

            ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0)
                System.out.println("Student deleted successfully!\n");
            else
                System.out.println("Student ID not found!\n");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static void searchById() {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = getConnection();

            System.out.print("Enter Student ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            String sql = "SELECT * FROM students WHERE id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("\nStudent Found:");
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Course: " + rs.getString("course"));
                System.out.println("Marks: " + rs.getInt("marks") + "\n");
            } else {
                System.out.println("Student not found!\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static void searchByName() {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = getConnection();

            System.out.print("Enter Student Name: ");
            String name = sc.nextLine();

            String sql = "SELECT * FROM students WHERE name = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, name);

            rs = ps.executeQuery();

            boolean found = false;

            while (rs.next()) {
                found = true;
                System.out.println(
                    rs.getInt("id") + " | " +
                    rs.getString("name") + " | " +
                    rs.getString("course") + " | " +
                    rs.getInt("marks")
                );
            }

            if (!found)
                System.out.println("No student found!\n");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void showAverageMarks() {

        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            st = con.createStatement();

            rs = st.executeQuery("SELECT AVG(marks) AS avgMarks FROM students");

            if (rs.next()) {
                System.out.println("Average Marks: " + rs.getDouble("avgMarks") + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void showTopper() {

        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            st = con.createStatement();

            rs = st.executeQuery("SELECT * FROM students ORDER BY marks DESC LIMIT 1");

            if (rs.next()) {
                System.out.println("\nTopper Student:");
                System.out.println(
                    rs.getInt("id") + " | " +
                    rs.getString("name") + " | " +
                    rs.getString("course") + " | " +
                    rs.getInt("marks") + "\n"
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void studentsAboveMarks() {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = getConnection();

            System.out.print("Enter minimum marks: ");
            int marks = sc.nextInt();
            sc.nextLine();

            String sql = "SELECT * FROM students WHERE marks > ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, marks);

            rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(
                    rs.getInt("id") + " | " +
                    rs.getString("name") + " | " +
                    rs.getString("course") + " | " +
                    rs.getInt("marks")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    
    public static void studentsBelowMarks() {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = getConnection();

            System.out.print("Enter maximum marks: ");
            int marks = sc.nextInt();
            sc.nextLine();

            String sql = "SELECT * FROM students WHERE marks < ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, marks);

            rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(
                    rs.getInt("id") + " | " +
                    rs.getString("name") + " | " +
                    rs.getString("course") + " | " +
                    rs.getInt("marks")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void countStudents() {

        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            st = con.createStatement();

            rs = st.executeQuery("SELECT COUNT(*) AS total FROM students");

            if (rs.next()) {
                System.out.println("Total Students: " + rs.getInt("total") + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void studentsByCourse() {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = getConnection();

            System.out.print("Enter Course Name: ");
            String course = sc.nextLine();

            String sql = "SELECT * FROM students WHERE course = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, course);

            rs = ps.executeQuery();

            boolean found = false;

            while (rs.next()) {
                found = true;

                System.out.println(
                    rs.getInt("id") + " | " +
                    rs.getString("name") + " | " +
                    rs.getString("course") + " | " +
                    rs.getInt("marks")
                );
            }

            if (!found) {
                System.out.println("Invalid Course! No students found.\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    
    public static void deleteAllStudents() {

        Connection con = null;
        Statement st = null;

        try {
            con = getConnection();
            st = con.createStatement();

            int rows = st.executeUpdate("DELETE FROM students");

            System.out.println(rows + " students deleted.\n");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) st.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public static String calculateGrade(int marks) {
        if (marks >= 90) return "A";
        else if (marks >= 75) return "B";
        else if (marks >= 50) return "C";
        else return "Fail";
    }
    
    
    // MAIN METHOD
public static void main(String[] args) {

        while (true) {

            System.out.println("===== Student Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Search Student by Id");
            System.out.println("6. Search Student by Name");
            System.out.println("7. show Average Marks");
            System.out.println("8. Show Topper Student");
            System.out.println("9. Show Students Above Given Marks");
            System.out.println("10. Show Students Below Given Marks");
            System.out.println("11. Count Total Students");
            System.out.println("12. View Students By Course");
            System.out.println("13. Delete All Students");
            System.out.println("14. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1: addStudent(); 
                	break;
                case 2: viewStudents(); 
                	break;
                case 3: updateStudent(); 
                	break;
                case 4: deleteStudent(); 
                	break;
                case 5: searchById();
                	break;
                case 6: searchByName();
                	break;
                case 7: showAverageMarks();
                	break;
                case 8: showTopper();
                	break;
                case 9: studentsAboveMarks();
                	break;
                case 10: studentsBelowMarks();
                	break;
                case 11: countStudents();
                	break;
                case 12: studentsByCourse();
                	break;
                case 13: deleteAllStudents();
                	break;
                case 14:
                    System.out.println("Thank you!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!\n");
            }
        }
    }
}