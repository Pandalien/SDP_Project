/*
 * SearchParams java bean for validating search parameters and holding them
 * as a bean for access by search.jsp.
 */
package utils;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author AD
 */
public class SearchParams {

  // search types  
  final public static int WORKER = 0;
  final public static int JOB = 1;
  
  // object fields
  public int suburb_id;
  public int classification_id;
  public int skills_id;
  public List<String> keywords;
  public int type;
  
  public SearchParams() {
    suburb_id = -1;
    classification_id = -1;
    skills_id = -1;
    keywords = null;
    type = WORKER;
  }
  
  // -- getters and setters --
  
  public int getSuburbId() {
    return suburb_id;
  }
  
  public void setSuburbId(int suburb_id) {
    this.suburb_id = SearchParams.validateSuburbId(suburb_id) ? suburb_id : -1;
  }
  
  public int getClassificationId() {
    return classification_id;
  }
  
  public void setClassificationId(int classification_id) {
    this.classification_id = SearchParams.validateClassificationId(classification_id) ? classification_id : -1;
  }
  
  public int getSkillsId() {
    return skills_id;
  }

  public void setSkillsId(int skills_id) {
    this.skills_id = SearchParams.validateSkillsId(skills_id) ? skills_id : -1;
  }
  
  public String getKeywords() {
    return keywords == null ? "" : String.join(" ", keywords);
  }
  
  public void setKeywords(String keywords) {
    this.keywords = SearchParams.parseKeywords(keywords);
  }
  
  public int getType() {
    return type;
  }
  
  public void setType(int type) {
    this.type = type;
  }
  
  
  // ----- static methods: -----
  
  // -- parsers -- 

  // must be positive integer, -1 indicates invalid value and should be ignored 
  public static int parseId(String id_param) {
    try {
      return Integer.parseInt(id_param);
    }
    catch (NumberFormatException e) {
      return -1;  
    }    
  }
  
  // return list of all text strings separated by non-word characters
  public static List<String> parseKeywords(String keywords) {
    return keywords == null ? null : Arrays.asList(keywords.split("\\W+"));    
  }
  
  public static int parseType(String type) {
    if (type == null)
      return JOB;
    String s = type.trim().toLowerCase();    
    return s.startsWith("worker") ? WORKER :
           s.startsWith("job") ? JOB :
           JOB;
  }
  
  // -- validators --
  
  public static boolean validateSuburbId(int suburb_id) {
    return suburb_id >= 0;
  }
  
  public static boolean validateClassificationId(int classification_id) {
    return classification_id >= 0;
  }

  public static boolean validateSkillsId(int skills_id) {
    return skills_id >= 0;
  }
  
  public static boolean validateKeywords(String keywords) {
    return true; // may be empty or null
  }
  
  public static boolean validateType(String type) {
    String s = type.toLowerCase();
    return s.equals("workers") || s.equals("jobs");
  }
  
}
