/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package simsgui;

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

// AI GENNED MOCK USERVALIDATOR TEST
public class UserValidatorTest {

    private UserValidator userValidator;
    private MockUserDAO mockDAO;

    public UserValidatorTest() {

    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        mockDAO = new MockUserDAO();
        userValidator = new UserValidator(mockDAO);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testValidRegistration() {
        // Arrange
        mockDAO.setExists(false); // Simulate username not exists
        mockDAO.setTestUser(null); // Simulate no existing user
        String username = "validUser"; // 10 characters
        String password = "ValidPass123"; // 12 characters
        String confirmPassword = password;

        // Act & Assert
        assertTrue(userValidator.validateRegistration(username, password, confirmPassword));
    }

    @Test
    public void testInvalidUsername() {
        // Arrange
        mockDAO.setExists(false);
        String username = "a"; // Too short (4 characters)
        String password = "ValidPass123";
        String confirmPassword = password;

        // Act & Assert
        assertFalse(userValidator.validateRegistration(username, password, confirmPassword));
    }

    @Test
    public void testInvalidPassword() {
        // Arrange
        mockDAO.setExists(false);
        String username = "validUser";
        String password = "Short1"; // Only 7 characters → invalid
        String confirmPassword = password;

        // Act & Assert
        assertFalse(userValidator.validateRegistration(username, password, confirmPassword));
    }

    @Test
    public void testNonUniqueUsername() {
        // Arrange
        mockDAO.setExists(true); // Simulate username already exists
        String username = "existingUser";
        String password = "ValidPass123";
        String confirmPassword = password;

        // Act & Assert
        assertFalse(userValidator.validateRegistration(username, password, confirmPassword));
    }

    @Test
    public void testPasswordMismatch() {
        // Arrange
        mockDAO.setExists(false);
        String username = "validUser";
        String password = "ValidPass123";
        String confirmPassword = "DifferentPass"; // Doesn't match

        // Act & Assert
        assertFalse(userValidator.validateRegistration(username, password, confirmPassword));
    }

    @Test
    public void testValidLogin() {
        // Arrange
        UserInfo testUser = new UserInfo("admin", "admin123", "admin");
        mockDAO.setTestUser(testUser);

        // Act & Assert
        assertTrue(userValidator.validateLogin("admin", "admin123"));
    }

    @Test
    public void testInvalidLogin() {
        // Arrange
        UserInfo testUser = null; // Simulate no user found
        mockDAO.setTestUser(testUser);

        // Act & Assert
        assertFalse(userValidator.validateLogin("invalid", "password"));
    }
}
