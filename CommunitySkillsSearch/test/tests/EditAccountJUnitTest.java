/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import utils.EditAccount;

/**
 *
 * @author matt
 */
public class EditAccountJUnitTest {
    EditAccount editAcc;
    
    
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
    
    @After
    public void tearDown() {
    }
}
