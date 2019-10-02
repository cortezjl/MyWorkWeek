package com.juliecortez.entity;



import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author JCortez
 */
@Entity(name = "Role") // Annotation to indicate this class is to be managed by Hibernate.  This is the name of the class
@Table(name = "role") // case sensitive!   This is the name of the table
public class Role {
    // Every Entity must have a unique identifier which is annotated @Id
    // Notice there is no @Column here as the column and instance variable name are the same
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int id;

   // private Set<UserRole> userRoles = new HashSet<>();   // Hold collection/set of users have the role - one role to possibly many users_role object
   // Unidirectional One-to-Many association using a foreign key mapping
    // @OneToMany(orphanRemoval=true)
    // @JoinColumn(name="roleId") // join column is in table for userRole
    //  Set<UserRole> getUserRole() {return userRoles;}
    private String role;

    /**
     * Instantiates a new Role.  Empty constructor
     */
    public Role() {
    }

    /**
     * Instantiates a new Role.
     *
     * @param role the role
     */
    public Role(String role) {
        this.role = role;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets role.
     *
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets role
     *
     * @param role the role
     */
    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Role{" +
                "role='" + role + '\'' +
                ", id=" + id +
                '}';
    }
}
