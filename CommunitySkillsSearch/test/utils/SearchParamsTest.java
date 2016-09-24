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
import utils.SearchParams;
import static org.junit.Assert.*;

/**
 *
 * @author AD
 */
public class SearchParamsTest {
  
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
  }
  
  @After
  public void tearDown() {
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
    assertEquals(Contract.WORKER, SearchParams.parseType("workers"));
    assertEquals(Contract.JOB, SearchParams.parseType("jobs"));     
    assertEquals(-1, SearchParams.parseType(null));
  }

  @Test
  public void testSuburbIDNegative() {
    int suburb_id = -1;
    assertFalse(SearchParams.validateSuburbID(suburb_id));
  }

  @Test
  public void testClassificationIDNegative() {
    int classification_id = -1;
    assertFalse(SearchParams.validateClassificationID(classification_id));
  }

  @Test
  public void testSkillsIDNegative() {
    int skills_id = -1;
    assertFalse(SearchParams.validateSkillsID(skills_id));
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
