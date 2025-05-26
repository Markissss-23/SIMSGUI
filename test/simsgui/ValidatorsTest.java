package simsgui;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
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
public class ValidatorsTest {

    IdValidator idValidator;
    GradeValidator gradeValidator;
    NameValidator nameValidator;
    DegreeValidator degreeValidator;
    StudentValidator studentValidator;

    MockStudentDAO mockDAO;

    public ValidatorsTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        mockDAO = new MockStudentDAO();
        idValidator = new IdValidator(mockDAO);
        gradeValidator = new GradeValidator();
        nameValidator = new NameValidator();
        degreeValidator = new DegreeValidator();
        studentValidator = new StudentValidator(mockDAO);
    }

    @After
    public void tearDown() {
    }

    /* ID VALIDATOR */
    @Test
    public void testValidId() {
        assertTrue(idValidator.validate(12345678));
    }

    @Test
    public void testIdTooShort() {
        assertFalse(idValidator.validate(100));
    }

    @Test
    public void testIdTooLong() {
        assertFalse(idValidator.validate(123456789));
    }

    @Test
    public void testIdNotUnique() {
        mockDAO.setExists(true); // Simulates ID already existing
        assertTrue(idValidator.validate(12345678));
    }

    @Test
    public void testIdIsUnique() {
        mockDAO.setExists(false); // Simulates ID not existing
        assertTrue(idValidator.validate(12345678));
    }

    /* GRADE VALIDATOR */
    @Test
    public void testValidGrade() {
        assertTrue(gradeValidator.validate("A+"));
        assertTrue(gradeValidator.validate("B-"));
        assertTrue(gradeValidator.validate("C"));
    }

    @Test
    public void testInvalidGrade() {
        assertFalse(gradeValidator.validate("AA"));
        assertFalse(gradeValidator.validate("90"));
        assertFalse(gradeValidator.validate("Z"));
    }

    /* NAME VALIDATOR */
    @Test
    public void testValidName() {
        assertTrue(nameValidator.validate("Markus Salvador"));
    }

    @Test
    public void testInValidName() {
        assertFalse(nameValidator.validate(""));
        assertFalse(nameValidator.validate(null));
        assertFalse(nameValidator.validate("Markus S123"));

    }

    /* DEGREE VALIDATOR */
    @Test
    public void testValidDegree() {
        assertTrue(degreeValidator.validate("Computer Science"));
    }

    @Test
    public void testInValidDegree() {
        assertFalse(degreeValidator.validate(""));
        assertFalse(degreeValidator.validate(null));
        assertFalse(degreeValidator.validate("Health123"));
    }

    /* STUDENT VALIDATOR */
    @Test
    public void testValidStudent() {
        mockDAO.setExists(false);
        assertTrue(studentValidator.validate(new StudentInfo(12345678, "Markus", "BCIS", "A+")));
    }

      
            
    @Test
    public void testInvalidIdInStudent() {
        mockDAO.setExists(false);
        assertFalse(studentValidator.validate(new StudentInfo(100, "Markus", "BCIS", "A+"))); // Invalid ID
    }

    @Test
    public void testNonUniqueIdInStudent() {
        mockDAO.setExists(true); // Simulate ID already exists
        assertTrue(studentValidator.validate(new StudentInfo(12345678, "Markus", "BCIS", "A+"))); // Non-unique ID
    }

    @Test
    public void testInvalidGradeInStudent() {
        mockDAO.setExists(false);
        assertFalse(studentValidator.validate(
                new StudentInfo(12345678, "Markus", "BCIS", "AA"))); // Invalid grade
    }

    @Test
    public void testInvalidNameInStudent() {
        mockDAO.setExists(false);
        assertFalse(studentValidator.validate(
                new StudentInfo(12345678, "Markus12", "BCIS", "A+"))); // Invalid name
    }

    @Test
    public void testInvalidDegreeInStudent() {
        mockDAO.setExists(false);
        assertFalse(studentValidator.validate(
                new StudentInfo(12345678, "Markus", "BCIS23", "A+"))); // Invalid degree
    }
}
