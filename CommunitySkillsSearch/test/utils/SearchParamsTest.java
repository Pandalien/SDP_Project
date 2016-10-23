/*
 * Software Development Practice, Stream 50 Team 2
 * Community Skills Search
 * Unit test for search function
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
public class SearchParamsTest {
  
  private SearchParams search;
  
  public SearchParamsTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
  }
  
  @AfterClass
  public static void tearDownClass() {
  }
  
  @Before
  public void setUp() {
    search = new SearchParams();
  }
  
  @After
  public void tearDown() {
  }
  
  @Test
  public void testGetKeywords() {
    assertNotNull(search.getKeywords());
  }
  
  @Test
  public void testParseId() {
    assertEquals(-1, SearchParams.parseId(""));
    assertEquals(0, SearchParams.parseId("0"));    
    assertEquals(-10, SearchParams.parseId("-10"));    
    assertEquals(123, SearchParams.parseId("123"));      
  }
  
  @Test
  public void testKeywords() {
    assertEquals("Hello", SearchParams.parseKeywords("Hello, World!").get(0));
    assertEquals("World", SearchParams.parseKeywords("Hello, World!").get(1));    
    assertNull(SearchParams.parseKeywords(null));
  }
  
  @Test 
  public void testParseType() {
    assertEquals(SearchParams.WORKER, SearchParams.parseType("workers"));
    assertEquals(SearchParams.JOB, SearchParams.parseType("jobs"));     
    assertEquals(SearchParams.JOB, SearchParams.parseType(null));
  }

  @Test
  public void testSuburbIDNegative() {
    int suburb_id = -1;
    assertFalse(SearchParams.validateSuburbId(suburb_id));
  }

  @Test
  public void testClassificationIDNegative() {
    int classification_id = -1;
    assertFalse(SearchParams.validateClassificationId(classification_id));
  }

  @Test
  public void testSkillsIDNegative() {
    int skills_id = -1;
    assertFalse(SearchParams.validateSkillsId(skills_id));
  }

  @Test
  public void testValidateKeywords() {
    String keywords = "";
    assertTrue(SearchParams.validateKeywords(keywords));
  }

  @Test
  public void testSearchFor() {
    assertTrue(SearchParams.validateType("Workers"));
    assertTrue(SearchParams.validateType("jobs"));    
    assertFalse(SearchParams.validateType("ForWorkers"));
    assertFalse(SearchParams.validateType(""));
  }
  
}
