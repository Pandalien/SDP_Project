
package tests;

import entities.Suburb;
import entities.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import utils.EditAccount;
import utils.UserCtrl;

/**
 *
 * @author matt
 */
public class EditAccountJUnitTest {
    EditAccount editAcc;
    UserCtrl userController;
    int dummyUserId = 0;
    User dummyUser;
    String username;
    String password;
    String email;
    String phone;
    String intro;
    boolean visible;
    String newEmail;
    String newPhone;
    String newIntro;
    boolean newVisible;
    
    public EditAccountJUnitTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.editAcc = new EditAccount();
        this.userController = new UserCtrl();
        username = "test";
        password = "test";
        email = "test@test.com";
        phone = "1234567890";
        intro = "This is a test.";
        visible = true;
        newEmail = "matt@matt.com";
        newPhone = "0987654321";
        newIntro = "I'm Matt.";
        newVisible = false;
    }

    @Test
    public void testValidEmail() {
        assertTrue(editAcc.validateEmail("username@domain.com.nz"));    // valid email
    }
    
    @Test
    public void testEmptyEmail() {
        assertFalse(editAcc.validateEmail(""));     // empty email
    }
    
    @Test
    public void testInvalidEmail1() {
        assertFalse(editAcc.validateEmail("usernamedomain.com"));     // missing @ symbol
    }
    
    @Test
    public void testInvalidEmail2() {
        assertFalse(editAcc.validateEmail("user@name@domain.com"));   // more than one @ symbol
    }
    
    @Test
    public void testInvalidEmail3() {
        assertFalse(editAcc.validateEmail("user name@domain.com"));   // whitespace in username
    }
    
    @Test
    public void testInvalidEmail4() {
        assertFalse(editAcc.validateEmail("username@domain..com"));   // consecutive dots in domain
    }
    
    @Test
    public void testInvalidEmail5() {
        assertFalse(editAcc.validateEmail(".username@domain.com"));   // username starts with dot
    }

    @Test
    public void testValidPhone1() {
        assertTrue(editAcc.validatePhone("1234567890"));
    }
    
    @Test
    public void testValidPhone2() {
        assertTrue(editAcc.validatePhone("123-456-7890"));      // contains - symbol
    }
    
    @Test
    public void testValidPhone3() {
        assertTrue(editAcc.validatePhone("(123)-456-7890"));    // contains bracket and - symbol
    }
    
    @Test
    public void testValidPhone4() {
        assertTrue(editAcc.validatePhone("123 456 7890"));      // contains whitespaces
    }

    @Test
    public void testValidPhone5() {
        assertTrue(editAcc.validatePhone("123-456-7890 x0000"));      // contains extension
    }
    
    @Test
    public void testInvalidPhone1() {
        assertFalse(editAcc.validatePhone("123abc7890"));      // accept digits only
    }
    
    @Test
    public void testInvalidPhone2() {
        assertFalse(editAcc.validatePhone("123456789"));      // too short
    } 
    
    @Test
    public void testInvalidPhone3() {
        assertFalse(editAcc.validatePhone("1234-567-890"));      // wrong format
    } 
    
    @Test
    public void testUpdateAccount() {
        User user = createDummyUser();
        // create a dummy user in database
        dummyUser = userController.create(user);
        // update the dummy user in the database
        updateDummyUser();
        // retrieve the dummy user from the database
        User updatedUser = userController.findById(dummyUser.getId());
        // test whether the data is updated
        assertEquals(newEmail, updatedUser.getEmail());
        assertEquals(newPhone, updatedUser.getPhone());
        assertEquals(newIntro, updatedUser.getIntroduction());
        assertEquals(newVisible, updatedUser.getVisible());
        testDelete();
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
    
    // update the dummy user in the database
    private void updateDummyUser() {
        dummyUser.setEmail(newEmail);
        dummyUser.setPhone(newPhone);
        dummyUser.setIntroduction(newIntro);
        dummyUser.setVisible(newVisible);
        
        userController.update(dummyUser);
    }
    
    public void testDelete() {
        userController.delete(dummyUser);
    }
    
    @After
    public void tearDown() {
    }
}
