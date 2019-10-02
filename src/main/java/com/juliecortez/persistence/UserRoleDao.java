package com.juliecortez.persistence;

import com.juliecortez.entity.UserRole;
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

public class UserRoleDao {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    /**
     * Gets all UserRoles.
     *
     * @return the all UserRoles
     */
    public List<UserRole> getAll() {

        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<UserRole> query = builder.createQuery(UserRole.class);
        Root<UserRole> root = query.from(UserRole.class);
        List<UserRole> orders = session.createQuery(query).getResultList();
        session.close();
        return orders;
    }


    /**
     * Gets a UserRole by id
     * @param id orders id to search by
     * @return a order
     */
    public UserRole getById(int id) {
        Session session = sessionFactory.openSession();
        UserRole userRole = session.get(UserRole.class, id);
        session.close();
        return userRole;
    }

    /**
     * update userRole
     * @param userRole  UserRole to be updated
     */
    public void saveOrUpdate(UserRole userRole) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(userRole);
        transaction.commit();
        session.close();
    }

    /**
     * insert UserRole
     * @param userRole  UserRole to be inserted
     * @return id of the inserted order
     */
    public int insert(UserRole userRole) {
        int id = 0;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        id = (int)session.save(userRole);
        transaction.commit();
        session.close();
        return id;
    }

    /**
     * Delete a UserRole
     * @param userRole UserRole to be deleted
     */
    public void delete(UserRole userRole) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(userRole);
        transaction.commit();
        session.close();
    }


    /**
     * Get order by property (exact match)
     * sample usage: getByPropertyEqual("lastName", "Curry")
     *
     * @param propertyName entity property to search by
     * @param value value of the property to search for
     * @return list of orders meeting the criteria search
     */
    public List<UserRole> getByPropertyEqual(String propertyName, String value) {
        Session session = sessionFactory.openSession();

        logger.debug("Searching for userRole with " + propertyName + " = " + value);

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<UserRole> query = builder.createQuery( UserRole.class );
        Root<UserRole> root = query.from( UserRole.class );
        query.select(root).where(builder.equal(root.get(propertyName), value));
        List<UserRole> userRoles = session.createQuery( query ).getResultList();

        session.close();
        return userRoles;
    }

    /**
     * Get order by property (like)
     * sample usage: getByPropertyLike("lastName", "C")
     *
     * @param propertyName entity property to search by
     * @param value value of the property to search for
     * @return list of orders meeting the criteria search
     */
    public List<UserRole> getByPropertyLike(String propertyName, String value) {
        Session session = sessionFactory.openSession();

        logger.debug("Searching for UserRole with {} = {}",  propertyName, value);

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<UserRole> query = builder.createQuery( UserRole.class );
        Root<UserRole> root = query.from( UserRole.class );
        Expression<String> propertyPath = root.get(propertyName);

        query.where(builder.like(propertyPath, "%" + value + "%"));

        List<UserRole> userRoles = session.createQuery( query ).getResultList();
        session.close();
        return userRoles;
    }


}