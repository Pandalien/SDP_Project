
package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author matt
 */
public class EditAccount {
    private String phone;
    private String email;
    
    public void setEmail(String email) {
        if (validateEmail(email))
            this.email = email;
    }
    
    public void setPhone(String phone) {
        if (validatePhone(phone))
            this.phone = phone;
    }
       
    public boolean validateEmail(String email) {
        // restrict leading, trailing, consecutive dots
        // top-level domain consists of 2 - 6 letters only
        Pattern pattern = Pattern.compile("^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    
    public boolean validatePhone(String phone) {
        // phone number should contain at least 10 digits
        
        // validate phone number of format "1234567890"
        if (phone.matches("\\d{10}"))
            return true;
        // validate phone number with -, . or spaces
        else if (phone.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}"))
            return true;
        // validate phone number with extension length from 3 to 5
        else if (phone.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}"))
            return true;
        // validate phone number where area code is in braces ()
        else if (phone.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}"))
            return true;
        return false;
    }
}
