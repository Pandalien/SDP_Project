/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entities.Suburb;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import sb.SuburbFacade;

/**
 *
 * @author andyc
 */
public class NewClass {

    public NewClass() {
        SuburbFacade suburbFacade = lookupSuburbFacadeBean();
        
        suburbFacade.create(new Suburb(1, "CDB"));
        System.out.println("OK");
    }
    
    public static void main(String[] args){
        NewClass c = new NewClass();
    }
    
    private SuburbFacade lookupSuburbFacadeBean() {
        try {
            Context c = new InitialContext();
            return (SuburbFacade) c.lookup("java:global/CommunitySkillsSearch/SuburbFacade!sb.SuburbFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
