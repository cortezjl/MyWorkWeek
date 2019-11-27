package com.juliecortez.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

private LocalDate startDate;
private LocalDate endDate;
private String startTime;
private String endTime;

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
     * @param startTime the start time
     * @param endTime   the end time
     * @param user      the user
     */
    public TimeOffRequest(String userName, LocalDate startDate, LocalDate endDate, String startTime, String endTime, User user ) {
    this.userName = userName;
    this.startDate = startDate;
    this.endDate = endDate;
    this.startTime = startTime;
    this.endTime = endTime;
    this.user = user;
}

    /**
     * constructor for Time off request.
     *
     * @param id        id for the time off request
     * @param userName  user name value
     * @param startDate the start date
     * @param endDate   the end date
     * @param startTime the start time
     * @param endTime   the end time
     * @param user      the user
     */
    public TimeOffRequest(Integer id, String userName, LocalDate startDate, LocalDate endDate, String startTime, String endTime, User user ) {
    this.id = id;
    this.userName = userName;
    this.startDate = startDate;
    this.endDate = endDate;
    this.startTime = startTime;
    this.endTime = endTime;
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
     * Gets start date for display in datepicker
     *
     * @return the start date as a string
     */
    public String getStartDateForDisplay() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        return dateTimeFormatter.format(startDate);
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
     * Gets end date for display in datepicker
     *
     * @return the end date as a string
     */
    public String getEndDateForDisplay() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        return dateTimeFormatter.format(endDate);
    }

    /**
     * Gets start time.
     *
     * @return the start time
     */
    public String getStartTime() {
    return startTime;
}

    /**
     * Sets start time.
     *
     * @param startTime the start time
     */
    public void setStartTime(String startTime) {
    this.startTime = startTime;
}

    /**
     * Gets end time.
     *
     * @return the end time
     */
    public String getEndTime() {
    return endTime;
}

    /**
     * Sets end time.
     *
     * @param endTime the end time
     */
    public void setEndTime(String endTime) {
    this.endTime = endTime;
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
                startTime.equals(that.startTime) &&
                endTime.equals(that.endTime) &&
                userName.equals(that.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startDate, endDate, startTime, endTime, userName);
    }

    @Override
    public String toString() {
        return "TimeOffRequest{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate='" + endDate + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}