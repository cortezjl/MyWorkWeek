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

    // RoleDao dao;
    GenericDao roleDao;


    /**
     * Creating the dao.
     */
    @BeforeEach
    void setUp() {
        // dao = new RoleDao();
        roleDao = new GenericDao(Role.class);

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

    }

    /**
     * Verifies gets all roles successfully.
     */
    @Test
    void getAllSuccess() {
        List<Role> roles = roleDao.getAll();
        assertEquals(2, roles.size());
    }


    /**
     * Verifies a Role is returned correctly based on id search
     */
    @Test
    void getByIdSuccess() {
        Role retrievedRole = (Role)roleDao.getById(1);
        assertNotNull(retrievedRole);
        assertEquals("Administrator", retrievedRole.getRole());
    }

    /**
     * Verify successful insert of a Role
     */
    @Test
    void insertSuccess() {

        GenericDao userDao = new GenericDao(User.class);
        // retrieve user object by id
        User user = (User)userDao.getById(1);
        // create the new role including the user object
        Role newRole = new Role("Busser", user);
        // add the Role to the set of Roles for the user object
        user.addRole(newRole);
        // insert the Role, which will update the user object
        int id = roleDao.insert(newRole);

        assertNotEquals(0,id);
        Role insertedRole = (Role)roleDao.getById(id);
        assertEquals("Busser", insertedRole.getRole());
        assertNotNull(insertedRole.getUser());
        // For the inserted Role object, get the user object and get the users first name, and compare to expected value
        assertEquals("System", insertedRole.getUser().getFirstName());
        assertEquals(newRole, insertedRole);
    }


    /**
     * Verify successful delete of order
     */
    @Test
    void deleteSuccess() {
        roleDao.delete(roleDao.getById(1));
        assertNull(roleDao.getById(1));
    }

    /**
     * Verify successful update of Role
     */
    @Test
    void updateSuccess() {
        String role = "Manager";
        Role roleToUpdate = (Role)roleDao.getById(1);
        roleToUpdate.setRole(role);
        roleDao.saveOrUpdate(roleToUpdate);
        Role retrievedRole = (Role)roleDao.getById(1);
        assertEquals(role, retrievedRole.getRole());
        assertEquals(roleToUpdate, retrievedRole);
    }

    /**
     * Verify successful get by property (equal match)
     */
    @Test
    void getByPropertyEqualSuccess() {
        List<Role> roles = roleDao.getByPropertyEqual("role", "Administrator");
        assertEquals(1, roles.size());
        assertEquals(1, roles.get(0).getId());
    }

    /**
     * Verify successful get by property (like match)
     */
    @Test
    void getByPropertyLikeSuccess() {
        List<Role> roles = roleDao.getByPropertyLike("role", "S");
        assertEquals(1, roles.size());
    }
}
