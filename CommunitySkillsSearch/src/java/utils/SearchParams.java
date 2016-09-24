/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author AD
 */
public class SearchParams {
  
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
      return -1;    
    String s = type.trim().toLowerCase();    
    return s.startsWith("worker") ? Contract.WORKER :
           s.startsWith("job") ? Contract.JOB :
           -1;
  }
  
  // -- validators --
  
  public static boolean validateSuburbID(int suburb_id) {
    return suburb_id >= 0;
  }
  
  public static boolean validateClassificationID(int classification_id) {
    return classification_id >= 0;
  }

  public static boolean validateSkillsID(int skills_id) {
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
