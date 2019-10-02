package com.juliecortez.persistence;

import com.juliecortez.entity.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.util.List;

public class RoleDao {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    /**
     * Get Role by id
     */
    public Role getById(int id) {
        Session session = sessionFactory.openSession();
        Role role = session.get( Role.class, id );
        session.close();
        return role;
    }

    /**
     * update Role
     * @param role  Role to be updated
     */
    public void saveOrUpdate(Role role) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(role);
        transaction.commit();
        session.close();
    }

    /**
     * insert Role
     * @param role  Role to be inserted
     */
    public int insert(Role role) {
        int id = 0;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        id = (int)session.save(role);
        transaction.commit();
        session.close();
        return id;
    }

    /**
     * Delete a role
     * @param role Role to be deleted
     */
    /*  Not planning on deleting any role records ever, so this is commented out
    public void delete(Role role) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(role);
        transaction.commit();
        session.close();
    }
    */

    /** Return a list of all Roles
     *
     * @return All Roles
     */
    public List<Role> getAll() {

        Session session = sessionFactory.openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Role> query = builder.createQuery( Role.class );
        builder = session.getCriteriaBuilder();
        Root<Role> root = query.from( Role.class );
        List<Role> roles = session.createQuery( query ).getResultList();

        logger.debug("The list of roles " + roles);
        session.close();

        return roles;
    }

    /**
     * Get roles by property (exact match)
     * sample usage: getByPropertyEqual("name", "Manager")
     */
    public List<Role> getByPropertyEqual(String propertyName, String value) {
        Session session = sessionFactory.openSession();

        logger.debug("Searching for roles with " + propertyName + " = " + value);

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Role> query = builder.createQuery( Role.class );
        Root<Role> root = query.from( Role.class );
        query.select(root).where(builder.equal(root.get(propertyName), value));
        List<Role> roles = session.createQuery( query ).getResultList();
        session.close();
        return roles;
    }

    /**
     * Get users by property (like)
     * sample usage: getByPropertyLike("name", "n")
     */
    public List<Role> getByPropertyLike(String propertyName, String value) {
        Session session = sessionFactory.openSession();

        logger.debug("Searching for roles with {} = {}",  propertyName, value);

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Role> query = builder.createQuery( Role.class );
        Root<Role> root = query.from( Role.class );
        Expression<String> propertyPath = root.get(propertyName);

        query.where(builder.like(propertyPath, "%" + value + "%"));

        List<Role> roles = session.createQuery( query ).getResultList();
        session.close();
        return roles;
    }
}
