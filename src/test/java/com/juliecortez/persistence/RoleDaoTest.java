package com.juliecortez.persistence;

import com.juliecortez.entity.Role;
import com.juliecortez.test.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

public class RoleDaoTest {
    /**
     * The Dao variable for the RoleDao object.
     */
    RoleDao dao;

    /**
     * Set up wil instantiate a BookDao object and "reset" the database so any changes from other tests do not remain.
     */
    @BeforeEach
    void setUp() {
        dao = new RoleDao();

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    /**
     * Tests get by id for success.
     */
    @Test
    void getByIdSuccess() {
        Role retrievedRole = dao.getById(2);
        // validate an entry was retrieved
        assertNotNull(retrievedRole);
        // validate one of the object values is the value expected
        assertEquals("Manager", retrievedRole.getName());
    }

    /**
     * Tests Save or update for success.
     */
    @Test
    void saveOrUpdateSuccess() {
        String newName = "Administrator";
        Role roleToUpdate = dao.getById(1);
        roleToUpdate.setName(newName);
        dao.saveOrUpdate(roleToUpdate);
        Role retrievedRole = dao.getById(1);
        assertEquals(newName, retrievedRole.getName());
    }

    /**
     * Tests Insert for success.
     */
    @Test
    void insertSuccess() {
        Role newRole = new Role("Test Role");
        int id = dao.insert(newRole);
        assertNotEquals(0,id);
        Role insertedRole = dao.getById(id);
        assertEquals("Test Role", insertedRole.getName());
    }

    /**
     * Tests Delete for success.
     */
    @Test
    void deleteSuccess() {
        dao.delete(dao.getById(3));
        assertNull(dao.getById(3));
    }

    /**
     * Test Getting all Roles for success.
     */
    @Test
    void getAllSuccess() {
        List<Role> roles = dao.getAll();
        assertEquals(5, roles.size());
    }


    /**
     * Gets by property for a specified field with a value equal to the specified value, tests for success
     */
    @Test
    void getByPropertyEqualSuccess() {
        List<Role> roles = dao.getByPropertyEqual("name","Busser");
        assertEquals(1, roles.size());
    }

    /**
     * Gets by property for a specified field with a value like the specified value, tests for success
     */
    @Test
    void getByPropertyLikeSuccess() {
        List<Role> roles = dao.getByPropertyLike("name","House");
        assertEquals(2, roles.size());
    }
}
