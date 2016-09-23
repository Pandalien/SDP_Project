package tests;

import entities.Adverts;
import entities.Classification;
import entities.Suburb;
import entities.User;
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
public class EditAdvertJUnitTest {

    AdvertCtrl adController;
    int createdAdId = 0;
    Adverts createdAd;

    String title;
    String content;
    String newTitle;

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
        adController = new AdvertCtrl();

        title = "Dog walker wanted!";
        content = "Requirements: bla bla, yada yada.";

        newTitle = "Cat walker wanted!";
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testCreate() {
        //test case: user should be able to create an advert
        Adverts ad = new Adverts();
        ad.setUserId(new User(1));
        ad.setTitle(title);
        ad.setContent(content);
        ad.setClassificationId(new Classification(1));
        ad.setSuburbId(new Suburb(1));

        createdAd = adController.create(ad);

        //if null then failed to create
        assertNotNull(createdAd);
        createdAdId = createdAd.getId();
    }

    @Test
    public void testQuery() {
        //test case: user should be able to receive an advert
        createdAd = adController.findById(createdAdId);

        //if null then failed to receive
        assertNotNull(createdAd);

        //check values are the same
        assertEquals(title, createdAd.getTitle());
        assertEquals(content, createdAd.getContent());
    }

    @Test
    public void testUpdate() {
        //test case: user should be able to update an advert
        createdAd.setTitle(newTitle);
        adController.update(createdAd);

        Adverts updatedAd = adController.findById(createdAdId);

        //if null then failed to receive
        assertNotNull(updatedAd);

        assertEquals(newTitle, updatedAd.getTitle());
        createdAd = updatedAd;
    }

    @Test
    public void testDelete() {
        //test case: user should be able to delete advert
        adController.delete(createdAd);
        createdAd = adController.findById(createdAdId);

        //if not null then failed to delete
        assertNull(createdAd);
    }
}
