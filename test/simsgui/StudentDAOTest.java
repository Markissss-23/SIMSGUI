/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package simsgui;

import java.sql.SQLException;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author marku
 */
public class StudentDAOTest {

    StudentDAO studentDAO;

    public StudentDAOTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        studentDAO = new StudentDAO();
        try {
            studentDAO.deleteAllStudents();
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testAddStudent() {
        StudentInfo student1 = new StudentInfo(12345678, "Markus", "BCIS", "A");
        studentDAO.addStudent(student1);

        List<StudentInfo> allStudents = studentDAO.getStudents();
        assertTrue(allStudents.contains(student1));
    }

    @Test
    public void testUpdateStudent() {
        StudentInfo student1 = new StudentInfo(12345678, "Markus", "BCIS", "A");
        studentDAO.addStudent(student1);

        StudentInfo updatedStudent1 = new StudentInfo(12345678, "Markus S", "Computer Science", "A+");
        studentDAO.updateStudent(updatedStudent1);

        List<StudentInfo> result = studentDAO.getStudents();
        assertEquals("Markus S", result.get(0).getName());
        assertEquals("Computer Science", result.get(0).getDegree());
        assertEquals("A+", result.get(0).getGrade());
    }

    @Test
    public void testDeleteStudent() {
        StudentInfo student1 = new StudentInfo(12345678, "Markus", "BCIS", "A");
        studentDAO.addStudent(student1);

        studentDAO.deleteStudent(student1.getId());

        List<StudentInfo> res = studentDAO.getStudents();
        assertFalse(res.contains(student1));
    }

    @Test
    public void testSearchByID() {
        StudentInfo student1 = new StudentInfo(12345678, "Markus", "BCIS", "A");
        studentDAO.addStudent(student1);

        List<StudentInfo> result = studentDAO.searchStudent("12345678");
        assertTrue(result.contains(student1));
    }

    @Test
    public void testSearchByName() {
        StudentInfo student1 = new StudentInfo(12345678, "Markus", "BCIS", "A");
        StudentInfo student2 = new StudentInfo(87654321, "Markus", "Health", "A+");

        studentDAO.addStudent(student1);
        studentDAO.addStudent(student2);

        List<StudentInfo> result = studentDAO.searchStudent("Markus");
        assertTrue(result.contains(student1));
    }


    /* Edge Cases */
    @Test
    public void testSearchNonExistent() {
        List<StudentInfo> result = studentDAO.searchStudent("99999999");
        assertTrue(result.isEmpty());
    }

    @Test
    public void testUpdateStudentNonExistent() {
        StudentInfo student1 = new StudentInfo(11111111, "Fake", "DegreeS", "A");
        studentDAO.updateStudent(student1);

        List<StudentInfo> result = studentDAO.getStudents();
        assertTrue(result.isEmpty());
    }

    @Test
    public void testDeleteStudentNonExistent() {
        studentDAO.deleteStudent(99999999);
        List<StudentInfo> result = studentDAO.getStudents();
        assertTrue(result.isEmpty());
    }

    @Test
    public void testSearchInvalidInput() {
        List<StudentInfo> result = studentDAO.searchStudent(null);
        assertTrue(result.isEmpty());

        result = studentDAO.searchStudent("");
        assertTrue(result.isEmpty());
    }

}
