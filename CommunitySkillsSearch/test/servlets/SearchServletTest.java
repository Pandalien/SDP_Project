/*
 * Software Development Practice, Stream 50 Team 2
 * Community Skills Search
 * Test showall method
 */
package servlets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author macbookair
 */
public class SearchServletTest {
  
  public SearchServletTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
  }
  
  @AfterClass
  public static void tearDownClass() {
  }
  
  @Before
  public void setUp() {
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of showall method, of class SearchServlet.
   */
  @Test
  public void testShowall() {
    System.out.println("showall");
    HttpServletRequest request = null;
    HttpServletResponse response = null;
    SearchServlet instance = new SearchServlet();
    //instance.showall(request, response);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }
  
}
