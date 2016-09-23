/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import entities.Suburb;
import entities.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import utils.UserCtrl;

/**
 *
 * @author andyc
 */
public class CreateUserJUnitTest {

    UserCtrl userController;
    int createdUserId = 0;
    User createdUser;

    String name;
    String email;
    String password;

    public CreateUserJUnitTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        userController = new UserCtrl();

        name = "SomeName";
        email = "somename@example.com";

        password = "scretStuff";
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testCreate() {
        //test case: user should be able to create an advert
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setSuburbId(new Suburb(1));

        createdUser = userController.create(user);

        //if null then failed to create
        assertNotNull(createdUser);
        createdUserId = createdUser.getId();
    }

    @Test
    public void testQuery() {
        //test case: user should be able to receive an advert
        createdUser = userController.findById(createdUserId);

        //if null then failed to receive
        assertNotNull(createdUser);

        //check values are the same
        assertEquals(name, createdUser.getName());
        assertEquals(email, createdUser.getEmail());
    }

    @Test
    public void testUpdate() {
        //test case: user should be able to update an advert
        createdUser.setPassword(password);
        userController.update(createdUser);

        User updatedUser = userController.findById(createdUserId);

        //if null then failed to receive
        assertNotNull(updatedUser);

        assertEquals(password, updatedUser.getPassword());
        createdUser = updatedUser;
    }

    @Test
    public void testDelete() {
        //test case: user should be able to delete advert
        userController.delete(createdUser);
        createdUser = userController.findById(createdUserId);

        //if not null then failed to delete
        assertNull(createdUser);
    }
}
