package com.juliecortez.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * The type User.
 *
 * @author JCortez
 */
@Entity(name = "User") // Annotation to indicate this class is to be managed by Hibernate.  This is the name of this java class
@Table(name = "User") // case sensitive!   This is the name of the table
public class User {
    // Every Entity must have a unique identifier which is annotated @Id
    // Notice there is no @Column here as the column and instance variable name are the same
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int id;

    private String cellPhone;
    private LocalDateTime createDate;
    private LocalDate dateOfBirth;
    private String email;
    private LocalDate endDate;
    private String firstName;
    private String homePhone;
    private String lastName;
    private String password;
    private LocalDate startDate;
    private LocalDateTime updateDate;

    @OneToMany(mappedBy = "User", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<UserRole> userRole = new HashSet<>();   // Hold collection/set of roles for the user - one user to possibly many roles


    /**
     * Instantiates a new User.  Empty constructor
     */
    public User() {
    }

    /**
     * Instantiates a new User.
     *
     * @param cellPhone   the cell phone
     * @param createDate  the create date
     * @param dateOfBirth the date of birth
     * @param email       the email
     * @param endDate     the end date
     * @param firstName   the first name
     * @param homePhone   the home phone
     * @param lastName    the last name
     * @param password    the password
     * @param startDate   the start date
     * @param updateDate  the update date
     * @param userRole    the user role
     */
    public User(String cellPhone, LocalDateTime createDate, LocalDate dateOfBirth, String email, LocalDate endDate, String firstName, String homePhone, String lastName, String password, LocalDate startDate, LocalDateTime updateDate, Set<UserRole> userRole) {
        this.cellPhone = cellPhone;
        this.createDate = createDate;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.endDate = endDate;
        this.firstName = firstName;
        this.homePhone = homePhone;
        this.lastName = lastName;
        this.password = password;
        this.startDate = startDate;
        this.updateDate = updateDate;
        this.userRole = userRole;
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
     * Gets cell phone.
     *
     * @return the cell phone
     */
    public String getCellPhone() {
        return cellPhone;
    }

    /**
     * Sets cell phone.
     *
     * @param cellPhone the cell phone
     */
    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    /**
     * Gets create date.
     *
     * @return the create date
     */
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    /**
     * Sets create date.
     *
     * @param createDate the create date
     */
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    /**
     * Gets date of birth.
     *
     * @return the date of birth
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets date of birth.
     *
     * @param dateOfBirth the date of birth
     */
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets end date.
     *
     * @return the end date
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Sets end date.
     *
     * @param endDate the end date
     */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets home phone.
     *
     * @return the home phone
     */
    public String getHomePhone() {
        return homePhone;
    }

    /**
     * Sets home phone.
     *
     * @param homePhone the home phone
     */
    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets start date.
     *
     * @return the start date
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Sets start date.
     *
     * @param startDate the start date
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets update date.
     *
     * @return the update date
     */
    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    /**
     * Sets update date.
     *
     * @param updateDate the update date
     */
    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * Gets user role.
     *
     * @return the user role
     */
    public Set<UserRole> getUserRole() {
        return userRole;
    }

    /**
     * Sets user role.
     *
     * @param userRole the user role
     */
    public void setUserRole(Set<UserRole> userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", cellPhone='" + cellPhone + '\'' +
                ", createDate=" + createDate +
                ", dateOfBirth=" + dateOfBirth +
                ", email='" + email + '\'' +
                ", endDate=" + endDate +
                ", firstName='" + firstName + '\'' +
                ", homePhone='" + homePhone + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", startDate=" + startDate +
                ", updateDate=" + updateDate +
                ", userRole=" + userRole +
                '}';
    }
}
