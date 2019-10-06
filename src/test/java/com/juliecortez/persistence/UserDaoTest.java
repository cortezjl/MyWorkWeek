package com.juliecortez.persistence;

import com.juliecortez.entity.Role;
import com.juliecortez.entity.User;
import com.juliecortez.test.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserDaoTest {

    /**
     * The Dao variable for the UserDao object.
     */
    UserDao dao;

    /**
     * Sets up before each test - instantiates a UserDao object
     */
    @BeforeEach
    void setUp() {
        dao = new UserDao();

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    /**
     * Gets User by id and tests for success.
     */
    @Test
    void getByIdSuccess() {
        User retrievedUser = dao.getById(1);
        // validate an entry was retrieved
        assertNotNull(retrievedUser);
        // validate one of the object values is the value expected
        assertEquals("System", retrievedUser.getFirstName());
    }

    /**
     * Gets all users, tests for success.
     */
    @Test
    void getAllSuccess() {
        List<User> users = dao.getAll();
        assertEquals(1, users.size());
    }


    /**
     * Verify successful insert of a user
     */
    @Test
    void insertSuccess() {
        User newUser = new User("Fred", "Flintstone", "fflintstone", LocalDate.parse("1968-01-01"));
        int id = dao.insert(newUser);
        assertNotEquals(0,id);
        User insertedUser = dao.getById(id);
        assertEquals("Fred", insertedUser.getFirstName());
        // Could continue comparing all values, but
        // it may make sense to use .equals()
        // TODO review .equals recommendations http://docs.jboss.org/hibernate/orm/5.2/userguide/html_single/Hibernate_User_Guide.html#mapping-model-pojo-equalshashcode
    }


    /**
     * Verify successful insert of a user with a Role
     */
    @Test
    void insertWithRoleSuccess() {
        // Instantiate and create a new user
        User newUser = new User("Fred", "Flintstone", "fflintstone", LocalDate.parse("1168-01-01"));
        String roleTitle = "Front Of House";
        // Instantiate and create a new Role and add the user object to the Role object
        Role role = new Role(roleTitle, newUser);
        // add the Role to the set of Roles for the User
        newUser.addRole(role);
        // insert the User object, which will create the Role as well
        int id = dao.insert(newUser);

        assertNotEquals(0,id);
        User insertedUser = dao.getById(id);
        assertEquals("Fred", insertedUser.getFirstName());
        assertEquals(1, insertedUser.getRoles().size());
        // Could continue comparing all values, but
        // it may make sense to use .equals()
        //  review .equals recommendations http://docs.jboss.org/hibernate/orm/5.2/userguide/html_single/Hibernate_User_Guide.html#mapping-model-pojo-equalshashcode
    }

    /**
     * Verify successful delete of user
     */
    @Test
    void deleteSuccess() {
        dao.delete(dao.getById(1));
        assertNull(dao.getById(1));
    }

    /**
     * Verify successful update of user
     */
    @Test
    void updateSuccess() {
        String newLastName = "Davis";
        User userToUpdate = dao.getById(1);
        userToUpdate.setLastName(newLastName);
        dao.saveOrUpdate(userToUpdate);
        User retrievedUser = dao.getById(1);
        assertEquals(newLastName, retrievedUser.getLastName());
    }

    /**
     * Gets by property for a specified field with a value equal to the specified vakue, tests for success
     */
    @Test
    void getByPropertyEqualSuccess() {
        List<User> users = dao.getByPropertyEqual("firstName","System");
        assertEquals(1, users.size());
    }

    /**
     * Gets by property for a specified field with a value like the specified value, tests for success
     */
    @Test
    void getByPropertyLikeSuccess() {
        List<User> users = dao.getByPropertyLike("lastName","A");
        assertEquals(1, users.size());
    }

}
