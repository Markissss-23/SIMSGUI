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
public class UserDAOTest {
    
    private UserDAO userDAO;
    
    public UserDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws SQLException {
        userDAO = new UserDAO();
        
        userDAO.deleteAllUsers();
        
    }
    
    @After
    public void tearDown() {
    }

    
    @Test
    public void testAddAndGetUser() {
        UserInfo user = new UserInfo("admin", "test", "admin");
        userDAO.addUser(user);
        
        UserInfo retrieved = userDAO.getUserByUsername(user.getUsername());
        assertNotNull(retrieved);
        assertEquals(user.getUsername(), retrieved.getUsername());
    }
    
    @Test
    public void testDeleteAllUsers() throws SQLException {
        UserInfo user1 = new UserInfo("u1", "123", "user");
        UserInfo user2 = new UserInfo("u2", "123", "user");
        
        userDAO.addUser(user1);
        userDAO.addUser(user2);
        
        userDAO.deleteAllUsers();
        List<UserInfo> allUsers = userDAO.getAllUsers();
        assertTrue(allUsers.isEmpty());
    }
    
    @Test
    public void testUserExists() {
        UserInfo user1 = new UserInfo("u1", "123", "user");
        UserInfo user2 = new UserInfo("u2", "123", "user");
        
        userDAO.addUser(user1);
        userDAO.addUser(user2);
        
        assertTrue(userDAO.userExists(user1.getUsername()));
    }
    
}
