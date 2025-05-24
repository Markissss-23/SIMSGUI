/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simsgui;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author marku
 */
public class StudentDAO {

    private final Connection conn;

    public StudentDAO() {
        this.conn = DBHandler.getInstance().getConnection();
    }

    // Partially genned with AI particularly the ResultSet
    public List<StudentInfo> qetStudents() {
        List<StudentInfo> students = new ArrayList<>();
        String sql = "SELECT * FROM Students";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                students.add(new StudentInfo(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("degree"),
                        rs.getString("grade")
                ));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return students;
    }

    public void addStudent(StudentInfo student) {
        String sql = "INSERT INTO Students (id, name, degree, grade) VALUES (?, ?, ?, ?)";

        // PreparedStatement is better for repeated Queries
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, student.getId());
            pstmt.setString(2, student.getName());
            pstmt.setString(3, student.getDegree());
            pstmt.setString(4, student.getGrade());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void updateStudent(StudentInfo student) {
        String sql = "UPDATE Students SET name = ?, degree = ?, grade = ? WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getDegree());
            pstmt.setString(3, student.getGrade());
            pstmt.setInt(4, student.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void deleteStudent(int id) {
        String sql = "DELETE FROM Students WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public List<StudentInfo> searchStudent(String query) {
        List<StudentInfo> results = new ArrayList<>();
        String sql = "SELECT * FROM Students WHERE id = ? OR LOWER(name) LIKE LOWER(?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            try {
                int id = Integer.parseInt(query);
                pstmt.setInt(1, id);
                pstmt.setString(2, "%" + query + "%");
            } catch (NumberFormatException e) {
                pstmt.setNull(1, Types.INTEGER); // Ignores ID 
                pstmt.setString(2, "%" + query + "%");
            }

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                StudentInfo student = new StudentInfo(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("degree"),
                        rs.getString("grade")
                );
                results.add(student);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return results;
    }
    
    public boolean IdExists(int id) {
        boolean exists = false;
        String sql = "SELECT COUNT(*) FROM Students WHERE id = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                exists = rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return exists; // WILL NEVER BE CALLED
    }    
}
