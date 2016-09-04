
package servlets;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author matt
 */
public class Login {
    private String email;
    private String password;

    public void setEmail(String email) {
        if (validateEmail(email))
            this.email = email;
    }

    public void setPassword(String password) {
        if (validatePassword(password))
            this.password = password;
    }
    
    public boolean validateEmail(String email) {
        // restrict leading, trailing, consecutive dots
        // top-level domain consists of 2 - 6 letters only
        Pattern pattern = Pattern.compile("^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    
    public boolean validatePassword(String password) {
        // at least 4 characters, whitespace is not allowed
        // digit, lowercase letter and uppercase letter must occur at least once
        Pattern pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{4,}$");
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
