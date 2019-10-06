package com.juliecortez.persistence;


import com.juliecortez.entity.Role;
import com.juliecortez.entity.User;
import com.juliecortez.test.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Role dao test.
 */
class RoleDaoTest {

    RoleDao dao;

    /**
     * Creating the dao.
     */
    @BeforeEach
    void setUp() {
        dao = new RoleDao();

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

    }

    /**
     * Verifies gets all roles successfully.
     */
    @Test
    void getAllSuccess() {
        List<Role> roles = dao.getAll();
        assertEquals(1, roles.size());
    }


    /**
     * Verifies a Role is returned correctly based on id search
     */
    @Test
    void getByIdSuccess() {
        Role retrievedRole = dao.getById(1);
        assertNotNull(retrievedRole);
        assertEquals("Administrator", retrievedRole.getRole());
    }

    /**
     * Verify successful insert of a Role
     */
    @Test
    void insertSuccess() {

        UserDao userDao = new UserDao();
        // retrieve user object by id
        User user = userDao.getById(1);
        // create the new order including the user object
        Role newRole = new Role("Manager", user);
        // add the Role to the set of Roles for the user object
        user.addRole(newRole);
        // insert the Role, which will update the user object
        int id = dao.insert(newRole);

        assertNotEquals(0,id);
        Role insertedRole = dao.getById(id);
        assertEquals("Manager", insertedRole.getRole());
        assertNotNull(insertedRole.getUser());
        // For the inserted Role object, get the user object and get the users first name, and compare to expected value
        assertEquals("System", insertedRole.getUser().getFirstName());
        // Could continue comparing all values, but
        // it may make sense to use .equals()
        // TODO review .equals recommendations http://docs.jboss.org/hibernate/orm/5.2/orderguide/html_single/Hibernate_Order_Guide.html#mapping-model-pojo-equalshashcode
    }


    /**
     * Verify successful delete of order
     */
    @Test
    void deleteSuccess() {
        dao.delete(dao.getById(1));
        assertNull(dao.getById(1));
    }

    /**
     * Verify successful update of Role
     */
    @Test
    void updateSuccess() {
        String role = "Manager";
        Role roleToUpdate = dao.getById(1);
        roleToUpdate.setRole(role);
        dao.saveOrUpdate(roleToUpdate);
        Role retrievedRole = dao.getById(1);
        assertEquals(role, retrievedRole.getRole());
    }

    /**
     * Verify successful get by property (equal match)
     */
    @Test
    void getByPropertyEqualSuccess() {
        List<Role> roles = dao.getByPropertyEqual("role", "Administrator");
        assertEquals(1, roles.size());
        assertEquals(1, roles.get(0).getId());
    }

    /**
     * Verify successful get by property (like match)
     */
    @Test
    void getByPropertyLikeSuccess() {
        List<Role> roles = dao.getByPropertyLike("role", "S");
        assertEquals(1, roles.size());
    }
}
