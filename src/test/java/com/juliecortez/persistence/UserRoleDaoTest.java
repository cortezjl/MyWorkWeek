package com.juliecortez.persistence;




import com.juliecortez.entity.User;
import com.juliecortez.entity.UserRole;
import com.juliecortez.test.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type UserRole dao test.
 */
class UserRoleDaoTest {

    UserRoleDao dao;

    /**
     * Creating the dao.
     */
    @BeforeEach
    void setUp() {
        dao = new UserRoleDao();

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

    }

    /**
     * Verifies gets all orders successfully.
     */
    @Test
    void getAllSuccess() {
        List<UserRole> userRoles = dao.getAll();
        assertEquals(1, userRoles.size());
    }


    /**
     * Verifies a UserRole is returned correctly based on id search
     */
    @Test
    void getByIdSuccess() {
        UserRole retrievedUserRole = dao.getById(1);
        assertNotNull(retrievedUserRole);
        assertEquals("System Administrator", retrievedUserRole.getDescription());
    }

    /**
     * Verify successful insert of a UserRole
     */
    @Test
    void insertSuccess() {

        UserDao userDao = new UserDao();
        // retrieve user object by id
        User user = userDao.getById(1);
        // create the new order including the user object
        UserRole newUserRole = new UserRole("Plates", user);
        // add the UserRole to the set of userRoles for the user object
        user.addUserRole(newUserRole);
        // insert the UserRole, which will update the user object
        int id = dao.insert(newUserRole);

        assertNotEquals(0,id);
        UserRole insertedUserRole = dao.getById(id);
        assertEquals("Plates", insertedUserRole.getDescription());
        assertNotNull(insertedUserRole.getUser());
        // For the inserted UserRole object, get the user object and get the users first name, and compare to expected value
        assertEquals("System", insertedUserRole.getUser().getFirstName());
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
     * Verify successful update of order
     */
    @Test
    void updateSuccess() {
        String description = "Laptops";
        UserRole userRoleToUpdate = dao.getById(1);
        userRoleToUpdate.setDescription(description);
        dao.saveOrUpdate(userRoleToUpdate);
        UserRole retrievedUserRole = dao.getById(1);
        assertEquals(description, retrievedUserRole.getDescription());
    }

    /**
     * Verify successful get by property (equal match)
     */
    @Test
    void getByPropertyEqualSuccess() {
        List<UserRole> userRoles = dao.getByPropertyEqual("description", "System Administrator");
        assertEquals(1, userRoles.size());
        assertEquals(1, userRoles.get(0).getId());
    }

    /**
     * Verify successful get by property (like match)
     */
    @Test
    void getByPropertyLikeSuccess() {
        List<UserRole> userRoles = dao.getByPropertyLike("description", "S");
        assertEquals(1, userRoles.size());
    }
}
