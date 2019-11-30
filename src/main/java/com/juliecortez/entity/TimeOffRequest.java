package com.juliecortez.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * The type TimeOffRequest.
 */
@Entity(name = "TimeOffRequest")  // name of this class
@Table(name = "time_off_request")  // name of the table (case-sensitive)
public class TimeOffRequest {

@Id
@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
@GenericGenerator(name = "native", strategy = "native")
private int id;

private LocalDateTime startDate;
private LocalDateTime endDate;

@Column(name = "user_name")
private String userName;
/**
 * Bidirectional @OneToMany (this goes on user because is zero to many time off request for a user)
 The bidirectional @OneToMany association also requires a @ManyToOne association on the child side.
 Although the Domain Model exposes two sides to navigate this association, behind the scenes,
 the relational database has only one foreign key for this relationship.

 Every bidirectional association must have one owning side only (the child side),
 the other one being referred to as the inverse (or the mappedBy) side.

 Foreign key is on the child table (role in this example)

 By default, the @ManyToOne association assumes that the parent-side entity identifier is to be used to join
 with the client-side entity Foreign Key column.

 However, when using a non-Primary Key association,
 the column description and foreign key should be used to instruct Hibernate
 which column should be used on the parent side to establish the many-to-one database relationship.
 Source: http://docs.jboss.org/hibernate/orm/5.2/userguide/html_single/Hibernate_User_Guide.html#associations-one-to-many
 */


@ManyToOne  // many time off request to one User (a user may have zero to many time off request) - create the User object instead of just variable userId
private User user;

    /**
     * constructor for TimeOffRequest.
     */
    public TimeOffRequest() {
}


    /**
     * constructor for Time off request.
     *
     * @param userName  user name value
     * @param startDate the start date
     * @param endDate   the start date
     * @param user      the user
     */
    public TimeOffRequest(String userName, LocalDateTime startDate, LocalDateTime endDate, User user ) {
    this.userName = userName;
    this.startDate = startDate;
    this.endDate = endDate;
    this.user = user;
}

    /**
     * constructor for Time off request.
     *
     * @param id        id for the time off request
     * @param userName  user name value
     * @param startDate the start date
     * @param endDate   the end date
     * @param user      the user
     */
    public TimeOffRequest(Integer id, String userName, LocalDateTime startDate, LocalDateTime endDate, User user ) {
    this.id = id;
    this.userName = userName;
    this.startDate = startDate;
    this.endDate = endDate;
    this.user = user;
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
     * Gets start date.
     *
     * @return the start date
     */
    public LocalDateTime getStartDate() {
    return startDate;
}

    /**
     * Sets start date.
     *
     * @param startDate the start date
     */
    public void setStartDate(LocalDateTime startDate) {
    this.startDate = startDate;
}


    /**
     * Gets end date.
     *
     * @return the end date
     */
    public LocalDateTime getEndDate() {
        return endDate;
    }

    /**
     * Sets end date.
     *
     * @param endDate the end date
     */
    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
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
     * Gets user.
     *
     * @return the user
     */
    public User getUser() {
    return user;
}

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(User user) {
    this.user = user;
}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeOffRequest that = (TimeOffRequest) o;
        return id == that.id &&
                startDate.equals(that.startDate) &&
                endDate.equals(that.endDate) &&
                userName.equals(that.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startDate, endDate, userName);
    }

    @Override
    public String toString() {
        return "TimeOffRequest{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate='" + endDate + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}