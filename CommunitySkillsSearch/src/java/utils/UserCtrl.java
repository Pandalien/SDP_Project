/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import entities.User;
import services.clients.UserClient;

/**
 *
 * @author andyc
 */
public class UserCtrl {
    Login loginValidator;
    EditAccount emailValidator;
    UserClient userClient;
    
    public UserCtrl(){
        loginValidator = new Login();
        emailValidator = new EditAccount();
        userClient = new UserClient();
    }
    
    public boolean verifyUserName(String name){
        if (StringUtils.isBlank(name)) {
            return false;
        }
        
        return name.length() <= 45;
    }
    
    public boolean verifyEmail(String email){
        return emailValidator.validateEmail(email);
    }
    
    public boolean verifyPassword(String password){
        return loginValidator.validatePassword(password);
    }
    
    public User create(User user){
        return userClient.create_XML(user, User.class);
    }
    
    public User findById(int id){
        return userClient.find_XML(User.class, String.format("%d", id));
    }
    
    public void update(User user){
        userClient.edit_XML(user, String.format("%d", user.getId()));
    }
    
    public void delete(User user){
        userClient.remove(String.format("%d", user.getId()));
    }
}
