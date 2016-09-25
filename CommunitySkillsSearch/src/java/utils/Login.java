
package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author matt
 */
public class Login {
    private String username;
    private String password;
    
    public void setUsername(String username) {
        if (validateUsername(username))
            this.username = username;
    }
    
    public void setPassword(String password) {
        if (validatePassword(password))
            this.password = password;
    }
    
    public boolean validateUsername(String username) {
        // at least 4 characters, whitespace is not allowed
        Pattern pattern = Pattern.compile("^[A-Za-z0-9._-]{4,}$");
        Matcher matcher = pattern.matcher(username);
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
