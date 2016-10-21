/*
 * Software Development Practice, Stream 50 Team 2
 * Community Skills Search
 * A Unit test suite for advert and user tests
 */
package suites;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tests.AdvertContentJUnitTest;
import tests.CreateAdvertJUnitTest;
import tests.CreateUserJUnitTest;
import tests.EditAdvertJUnitTest;

/**
 *
 * @author andyc
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    AdvertContentJUnitTest.class,
    CreateAdvertJUnitTest.class,
    CreateUserJUnitTest.class,
    EditAdvertJUnitTest.class
})
public class AC_TestSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
