/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author AD
 */
public class RandomPasswordTest {
  
  private Login passwordChecker;
  
  public RandomPasswordTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
  }
  
  @AfterClass
  public static void tearDownClass() {
  }
  
  @Before
  public void setUp() {
    passwordChecker = new Login();
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of makeRandomPassword method, of class RandomPassword.
   */
  @Test
  public void testMakeRandomPassword() {
    System.out.println("makeRandomPassword");
    
    // should always return a valid password:
    String password;
            
    password = RandomPassword.makeRandomPassword();
    System.out.println(password);    
    assertTrue(passwordChecker.validatePassword(password));   
    
    password = RandomPassword.makeRandomPassword();
    System.out.println(password);    
    assertTrue(passwordChecker.validatePassword(password));       
    
    password = RandomPassword.makeRandomPassword();
    System.out.println(password);    
    assertTrue(passwordChecker.validatePassword(password));       
   }
  
}
