/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import utils.Login;

/**
 *
 * @author matt
 */
public class LoginJUnitTest {
    Login login;
    
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
    }
    
    @Test
    public void testLoginEmptyEmail() {
        assertFalse(login.validateEmail(""));                       // empty email
    }
    
    @Test
    public void testLoginInvalidEmail1() {
        assertFalse(login.validateEmail("usernamedomain.com"));     // missing @ symbol
    }
    
    @Test
    public void testLoginInvalidEmail2() {
        assertFalse(login.validateEmail("user@name@domain.com"));   // more than one @ symbol
    }
    
    @Test
    public void testLoginInvalidEmail3() {
        assertFalse(login.validateEmail("user name@domain.com"));   // whitespace in username
    }
    
    @Test
    public void testLoginInvalidEmail4() {
        assertFalse(login.validateEmail("username@domain..com"));   // consecutive dots in domain
    }
    
    @Test
    public void testLoginInvalidEmail5() {
        assertFalse(login.validateEmail(".username@domain.com"));   // username starts with dot
    }
    
    @Test
    public void testLoginEmptyPassword() {
        assertFalse(login.validatePassword(""));                    // empty password
    }
    
    @Test
    public void testLoginInvalidPassword1() {
        assertFalse(login.validatePassword("Pw1"));                 // less than 4 characters
    }
    
    @Test
    public void testLoginInvalidPassword2() {
        assertFalse(login.validatePassword("Password 1"));          // whitespace in password
    }

    @Test
    public void testLoginInvalidPassword3() {
        assertFalse(login.validatePassword("PASSWORD1"));            // at least one lowercase letter
    }
    @Test
    public void testLoginInvalidPassword4() {
        assertFalse(login.validatePassword("password1"));            // at least one uppercase letter
    }
    @Test
    public void testLoginInvalidPassword5() {
        assertFalse(login.validatePassword("12345678"));            // at least one uppercase and lowercase letter
    }    

    @After
    public void tearDown() {
    }
}
