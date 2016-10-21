/*
 * Software Development Practice, Stream 50 Team 2
 * Community Skills Search
 * Java bean for request data
 */
package beans;

import entities.User;
import java.util.List;

/**
 *
 * @author andyc
 */
public class RequestData {
    public User user;
    public int id;
    public List<Integer> ids;
    
    public RequestData(User user, int id){
        this.user = user;
        this.id = id;
    }
}
