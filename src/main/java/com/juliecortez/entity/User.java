package com.juliecortez.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A class to represent a user.
 *
 * @author JCortez
 */
@Entity(name = "User") // Annotation to indicate this class is to be managed by Hibernate.  This is the name of the class
@Table(name = "user") // case sensitive!   This is the name of the table
@NamedQueries({
        @NamedQuery(
                name = "findUsersActiveDuringDates",
                query = "select u from User u where u.startDate <= :endDate and u.endDate >= :startDate"
        )
})
public class User {
    // Every Entity must have a unique identifier which is annotated @Id
    // Notice there is no @Column here as the column and instance variable name are the same
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int id;

    @Column(name = "first_name") // Use @Column annotation when variable name does not match database column name
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "user_name")
    private String userName;

    private String password;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    private LocalDate startDate;
    private LocalDate endDate;

    /**
     * Bidirectional @OneToMany

     The bidirectional @OneToMany association also requires a @ManyToOne association on the child side.
     Although the Domain Model exposes two sides to navigate this association, behind the scenes,
     the relational database has only one foreign key for this relationship.

     Every bidirectional association must have one owning side only (the child side),
     the other one being referred to as the inverse (or the mappedBy) side.

     Foreign key is on the child table (Order in this example)

     Source: http://docs.jboss.org/hibernate/orm/5.2/userguide/html_single/Hibernate_User_Guide.html#associations-one-to-many

     cascadeType is for how the database should handle an object that is deleted, ie delete from other tables where value is used
     orphanRemoval has to do with Hibernate removing an object that has been deletee
     */
    // There is one User with zero to many roles
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();   // Hold collection/set of Roles for the user - zero to many

    // There is one User with zero to many time off requests
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<TimeOffRequest> timeOffRequests = new HashSet<>();   // Hold collection/set of time off requests for the user - zero to many

    // There is one User with zero to many schedule details
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<ScheduleDetail> scheduleDetails = new HashSet<>();   // Hold collection/set of schedule details for the user - zero to many

    /**
     * Instantiates a new User.
     */
    public User() {
    }

    /**
     * Instantiates a new User object.
     *
     * @param firstName   the first name
     * @param lastName    the last name
     * @param userName    the user name
     * @param password    users password
     * @param dateOfBirth the date of birth
     * @param startDate   the users start date
     * @param endDate     the users end date
     */
    public User(String firstName, String lastName, String userName, String password, LocalDate dateOfBirth, LocalDate startDate, LocalDate endDate) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Instantiates a new User object.
     *
     * @param id          the id
     * @param firstName   the first name
     * @param lastName    the last name
     * @param userName    the user name
     * @param password    users password
     * @param dateOfBirth the date of birth
     * @param startDate   the users start date
     * @param endDate     the users end date
     */
    public User(int id, String firstName, String lastName, String userName, String password, LocalDate dateOfBirth, LocalDate startDate, LocalDate endDate) {
        this(firstName, lastName,userName, password, dateOfBirth, startDate, endDate);
        this.id = id;
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
     * Gets user name.
     *
     * @return the user name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets user name.
     *
     * @param userName the user name
     */
    public void setUserName(String userName) {
        this.userName = userName;
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
     * Gets dateOfBirth.
     *
     * @return the dateOfBirth
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets dateOfBirth.
     *
     * @param dateOfBirth the date of birth
     */
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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
     * Gets roles.
     *
     * @return the roles
     */
    public Set<Role> getRoles() {
        return roles;
    }

    /**
     * Sets roles.
     *
     * @param roles the roles
     */
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    /**
     * Add role.
     *
     * @param role the role
     */
    public void addRole(Role role) {
        roles.add(role);
        role.setUser(this);
    }

    /**
     * Remove role.
     *
     * @param role the role
     */
    public void removeRole(Role role) {
        roles.remove(role);
        role.setUser(null);
    }

    /**
     * Add time off request.
     *
     * @param timeOffRequest the time off request
     */
    public void addTimeOffRequest(TimeOffRequest timeOffRequest) {
        timeOffRequests.add(timeOffRequest);
        timeOffRequest.setUser(this);
    }

    /**
     * Remove time off request.
     *
     * @param timeOffRequest the time off request.
     */
    public void removeTimeOffRequest(TimeOffRequest timeOffRequest) {
        timeOffRequests.remove(timeOffRequest);
        timeOffRequest.setUser(null);
    }


    /**
     * Add scheduleDetail
     *
     * @param scheduleDetail the schedule detail
     */
    public void addScheduleDetail(ScheduleDetail scheduleDetail) {
        scheduleDetails.add(scheduleDetail);
        scheduleDetail.setUser(this);
    }

    /**
     * Remove schedule detail
     *
     * @param scheduleDetail the schedule detail
     */
    public void removeScheduleDetail(ScheduleDetail scheduleDetail) {
        scheduleDetails.remove(scheduleDetail);
        scheduleDetail.setUser(null);
    }

    /**
     * Gets date of birth for display in datepicker
     *
     * @return the date of birth as a string
     */
    public String getDateOfBirthForDisplay() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        return dateTimeFormatter.format(dateOfBirth);
    }

    /**
     * Gets start date for display in datepicker
     *
     * @return the start date as a string
     */
    public String getStartDateForDisplay() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        return dateTimeFormatter.format(startDate);
    }

    /**
     * Gets end date for display in datepicker
     *
     * @return the end date as a string
     */
    public String getEndDateForDisplay() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        return dateTimeFormatter.format(endDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(userName, user.userName) &&
                Objects.equals(password, user.password) &&
                Objects.equals(dateOfBirth, user.dateOfBirth) &&
                Objects.equals(startDate, user.startDate) &&
                Objects.equals(endDate, user.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, userName, password, dateOfBirth, startDate, endDate);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", roles=" + roles +
                '}';
    }
}