/*
 * Software Development Practice, Stream 50 Team 2
 * Community Skills Search
 * Unit test for applying to an ad
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
