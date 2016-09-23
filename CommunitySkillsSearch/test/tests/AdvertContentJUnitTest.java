package tests;

import entities.Adverts;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import utils.AdvertCtrl;
import utils.StringUtils;

/**
 *
 * @author andyc
 */
public class AdvertContentJUnitTest {

    AdvertCtrl adController;
    int createdAdId = 0;
    Adverts createdAd;

    String title;
    String content;
    String newTitle;

    public AdvertContentJUnitTest() {
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
    public void testTitleValidation() {
        //test case: user input title should be verified
        assertTrue(adController.verifyTitle(title));

        //empty title
        assertFalse(adController.verifyTitle(""));

        //empty space title
        assertFalse(adController.verifyTitle(" "));

        //empty title length (90 too long)
        assertTrue(adController.verifyTitle(StringUtils.repeat("a", 45)));
        assertFalse(adController.verifyTitle(StringUtils.repeat("a", 46)));
    }

    @Test
    public void testContentValidation() {
        //test case: user input conten should be verified
        assertTrue(adController.verifyContent(content));

        //empty conten
        assertFalse(adController.verifyTitle(""));

        //empty conten length (too long)
        assertTrue(adController.verifyTitle(StringUtils.repeat("a", 500)));
        assertFalse(adController.verifyTitle(StringUtils.repeat("a", 501)));

    }

    @Test
    public void testExpiryDateValidation() {
        //test case: user input Expiry date should be verified
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        String date = dateFormat.format(cal.getTime());

        //cannot set to yesterday
        assertFalse(adController.verifyExpiryDate(date));

        Calendar cal2 = Calendar.getInstance();
        cal2.add(Calendar.DATE, 1);
        String date2 = dateFormat.format(cal.getTime());

        //can set to tomorrow
        assertTrue(adController.verifyExpiryDate(date2));
    }
}
