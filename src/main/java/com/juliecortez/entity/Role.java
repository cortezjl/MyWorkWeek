package com.juliecortez.entity;



import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author JCortez
 */
@Entity(name = "Role") // Annotation to indicate this class is to be managed by Hibernate.  This is the name of the class
@Table(name = "Role") // case sensitive!   This is the name of the table
public class Role {
    // Every Entity must have a unique identifier which is annotated @Id
    // Notice there is no @Column here as the column and instance variable name are the same
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int id;

    private String name;

    /**
     * Instantiates a new Role.  Empty constructor
     */
    public Role() {
    }

    /**
     * Instantiates a new Role.  Constructor with name parameter
     *
     * @param name the name
     */
    public Role(String name) {
        this.name = name;
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
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Role{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
