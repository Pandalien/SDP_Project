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
import utils.AdvertCtrl;

/**
 *
 * @author andyc
 */
public class AdvertApplyJUnitTest {
    
    public AdvertApplyJUnitTest() {
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
    public void testApply() {
        AdvertCtrl adController = new AdvertCtrl();
        int idAd = 1;
        int idUser = 1;

        Adverts ad = adController.findById(idAd);
        assertNotNull(ad);

        if (!ad.getClosed()) {
            if(adController.isApplied(idUser)){
                assertTrue(adController.apply(idAd, idUser));
            }else{
                assertTrue(adController.cancel(idAd, idUser));
            }
        }
    }
}
