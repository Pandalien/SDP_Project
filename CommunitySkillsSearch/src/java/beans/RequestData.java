/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
