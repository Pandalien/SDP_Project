/*
 * Software Development Practice, Stream 50 Team 2
 * Community Skills Search
 * Test to call EJB
 */
package sb;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.embeddable.EJBContainer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author AD
 * Ref: https://netbeans.org/kb/docs/javaee/javaee-entapp-junit.html
 */
public class AdvertsFacadeTest {
  
  private static EJBContainer container;
  
  public AdvertsFacadeTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
    Map<String, Object> properties = new HashMap<String, Object>();
    properties.put(EJBContainer.MODULES, new File("build/jar"));      
    container = EJBContainer.createEJBContainer(properties);           
    System.out.println("Opening EJB Container");    
  }
  
  @AfterClass
  public static void tearDownClass() {
    container.close(); 
    System.out.println("Closing EJB Container.");    
  }
  
  @Before
  public void setUp() {
  }
  
  @After
  public void tearDown() {
  }


  /**
   * Test of find method, of class AdvertsFacade.
   */
  @Test
  public void testFind() throws Exception {
    System.out.println("find");
    
    System.out.println("Looking up EJB...");
    AdvertsFacade instance = (AdvertsFacade)container.getContext().lookup("java:global/classes/AdvertsFacade");
    
    
    assertNotNull(instance);
    
/*    
    Object id = new Integer(1);
    
    EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
    AdvertsFacade instance = (AdvertsFacade)container.getContext().lookup("java:global/classes/AdvertsFacade");
//    Adverts expResult = null;
    Adverts result = instance.find(id);
//    assertEquals(expResult, result);

    assertEquals(new Integer(7), result.getUserId().getId());
    
    container.close();
    
*/    
    // TODO review the generated test code and remove the default call to fail.
//    fail("The test case is a prototype.");
  }

  
}
