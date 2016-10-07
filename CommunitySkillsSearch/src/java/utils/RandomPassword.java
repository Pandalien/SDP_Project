/*
 * Generate a valid random password for this system.
 */
package utils;

import java.util.Random;

/**
 *
 * @author AD
 */
public class RandomPassword {
  
  public static String makeRandomPassword() { 
    String upper_chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String lower_chars = "abcdefghijklmnopqrstuvwxyz";
    String number_chars = "0123456789";      
    String special_chars = "!@#$%^&*_";     

    Random rand = new Random();

    // must be minimum 4 characters.
    // digit, uppercase and lowercase characters must occur at least once.
    int upper_count = rand.nextInt(4) + 1;
    int lower_count = rand.nextInt(4) + 1;
    int number_count = rand.nextInt(4) + 1;
    int special_count = rand.nextInt(4) + 1;

    String password = new String();    
    
    for (int i = upper_count; i > 0; i--) {
      int j = rand.nextInt(upper_chars.length());
      char ch = upper_chars.charAt(j);      
      // check for empty password only has to be done once here, 
      // since minimum upper case chars is 1
      if (password.length() == 0) 
        password += ch;      
      else {
        int k = rand.nextInt(password.length());
        password = password.substring(0, k) + ch + password.substring(k);
      }
    }

    for (int i = lower_count; i > 0; i--) {
      int j = rand.nextInt(lower_chars.length());
      int k = rand.nextInt(password.length());
      char ch = lower_chars.charAt(j);
      password = password.substring(0, k) + ch + password.substring(k);
    }

    for (int i = number_count; i > 0; i--) {
      int j = rand.nextInt(number_chars.length());
      int k = rand.nextInt(password.length());
      char ch = number_chars.charAt(j);
      password = password.substring(0, k) + ch + password.substring(k);
    }

    for (int i = special_count; i > 0; i--) {
      int j = rand.nextInt(special_chars.length());
      int k = rand.nextInt(password.length());
      char ch = special_chars.charAt(j);
      password = password.substring(0, k) + ch + password.substring(k);
    }      

    return password;
  }  
  
}
