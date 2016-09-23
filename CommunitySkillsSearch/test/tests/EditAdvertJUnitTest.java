/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import entities.Adverts;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import services.clients.AdvertsClient;

/**
 *
 * @author andyc
 */
public class EditAdvertJUnitTest {
    
    public EditAdvertJUnitTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

     //TODO add test methods here.
     //The methods must be annotated with annotation @Test. For example:
    
     @Test
     public void hello() {
         AdvertsClient ac = new AdvertsClient();
         
         Adverts ad = ac.find_XML(Adverts.class, "3");
         
         System.out.println(ad.getContent());
     }
}
