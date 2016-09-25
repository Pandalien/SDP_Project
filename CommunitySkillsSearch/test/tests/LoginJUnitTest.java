package tests;

import entities.Suburb;
import entities.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import utils.Login;
import utils.UserCtrl;

/**
 *
 * @author matt
 */
public class LoginJUnitTest {
    Login login;
    UserCtrl userController;
    int dummyUserId = 0;
    User dummyUser;
    String username;
    String password;
    String email;
    String phone;
    String intro;
    boolean visible;

    public LoginJUnitTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.login = new Login();
        this.userController = new UserCtrl();
        username = "test";
        password = "test";
        email = "test@test.com";
        phone = "1234567890";
        intro = "This is a test.";
        visible = true;
    }
    
    @Test
    public void testLoginValidUsername() {
        assertTrue(login.validateUsername("this.is-user_name"));    // valid username
    }
    
    @Test
    public void testLoginEmptyUsername() {
        assertFalse(login.validateUsername(""));    // empty username
    }
    
    @Test
    public void testLoginInvalidUsername1() {
        assertFalse(login.validateUsername("cyo"));  // less than 4 characters
    }
    
    @Test
    public void testLoginInvalidUsername2() {
        assertFalse(login.validateUsername("user@name"));  // contains @ symbol
    }
    @Test
    public void testLoginInvalidUsername3() {
        assertFalse(login.validateUsername("user name"));  // contains whitespace
    }
    
    @Test
    public void testLoginValidPassword() {
        assertFalse(login.validatePassword("pAssWord"));     // less than 4 characters
    }  
    
    @Test
    public void testLoginEmptyPassword() {
        assertFalse(login.validatePassword(""));    // empty password
    }
    
    @Test
    public void testLoginInvalidPassword1() {
        assertFalse(login.validatePassword("Pw1"));     // less than 4 characters
    }
    
    @Test
    public void testLoginInvalidPassword2() {
        assertFalse(login.validatePassword("Password 1"));      // whitespace in password
    }

    @Test
    public void testLoginInvalidPassword3() {
        assertFalse(login.validatePassword("PASSWORD1"));       // at least one lowercase letter
    }
    @Test
    public void testLoginInvalidPassword4() {
        assertFalse(login.validatePassword("password1"));       // at least one uppercase letter
    }
    @Test
    public void testLoginInvalidPassword5() {
        assertFalse(login.validatePassword("12345678"));        // at least one uppercase and lowercase letter
    }

    @Test
    public void testLoginFunction() {
        User user = createDummyUser();
        // create a dummy user in database
        dummyUser = userController.create(user);
        assertNotNull(dummyUser);
        assertTrue(testLoginHelper("test", "test"));        // login matches with database
        assertFalse(testLoginHelper("test", "wrongpw"));    // login does not match with database
        testDelete();       // remove dummy user
    }

    // create a dummy user in database
    private User createDummyUser() {
        User user = new User();
        user.setName(username);     // unchangable
        user.setPassword(password);
        user.setEmail(email);
        user.setPhone(phone);
        user.setSuburbId(new Suburb(1));
        user.setIntroduction(intro);
        user.setVisible(visible);
        return user;
    }
    
    public boolean testLoginHelper(String username, String password) {
        return username.equals(dummyUser.getName()) && password.equals(dummyUser.getPassword());
    }

    public void testDelete() {
        dummyUserId = dummyUser.getId();
        userController.delete(dummyUser);
        dummyUser = userController.findById(dummyUserId);
        //if not null then failed to delete
        assertNull(dummyUser);
    }
    
    @After
    public void tearDown() {
    }
}
