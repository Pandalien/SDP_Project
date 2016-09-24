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
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import utils.AdvertCtrl;

/**
 *
 * @author andyc
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CreateAdvertJUnitTest {

    AdvertCtrl adController;
    int createdAdId;
    Adverts createdAd;

    String title;
    String content;
    String newTitle;

    public CreateAdvertJUnitTest() {
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
    }

    private Adverts createTestData(){
        Adverts ad = new Adverts();
        ad.setUserId(new User(1));
        ad.setTitle(title);
        ad.setContent(content);
        ad.setClassificationId(new Classification(1));
        ad.setSuburbId(new Suburb(1));
        
        return ad;
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testCreate() {
        //test case: user should be able to create an advert
        Adverts ad = createTestData();

        createdAd = adController.create(ad);

        //if null then failed to create
        assertNotNull(createdAd);
        createdAdId = createdAd.getId();
    }

    @Test
    public void testQuery() {
        Adverts ad = createTestData();
        createdAd = adController.create(ad);
        
        //if null then failed to create
        assertNotNull(createdAd);

        //test case: user should be able to receive an advert
        Adverts queriedAd = adController.findById(createdAd.getId());

        //if null then failed to receive
        assertNotNull(queriedAd);

        //check values are the same
        assertEquals(title, queriedAd.getTitle());
        assertEquals(content, queriedAd.getContent());
    }
}
