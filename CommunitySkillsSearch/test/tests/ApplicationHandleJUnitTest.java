/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import entities.Responders;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import utils.AdvertCtrl;

/**
 *
 * @author andyc
 */
public class ApplicationHandleJUnitTest {
    
    public ApplicationHandleJUnitTest() {
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

    @Test
    public void testSelect() {
        AdvertCtrl adController = new AdvertCtrl();
        int idAd = 1;
        int idUser = 1;
        
        Responders responder = adController.getResponder(idAd, idUser);
        assertNotNull(responder);
        
        assertTrue(adController.selectResponder(idAd, idUser));
        
        assertTrue(adController.deselectResponder(idAd, idUser));
    }
}
