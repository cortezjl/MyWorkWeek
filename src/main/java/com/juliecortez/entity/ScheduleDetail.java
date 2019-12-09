package com.juliecortez.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

/**
 * The type Schedule detail.
 */
@Entity(name = "ScheduleDetail")  // name of this class
@Table(name = "schedule_detail")  // name of the table (case-sensitive)
public class ScheduleDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    private String startTime1;
    private String endTime1;
    private String startTime2;
    private String endTime2;
    private String startTime3;
    private String endTime3;
    private String startTime4;
    private String endTime4;
    private String startTime5;
    private String endTime5;
    private String startTime6;
    private String endTime6;
    private String startTime7;
    private String endTime7;

    @Column(name = "user_name")
    private String userName;
    /**
     * Bidirectional @OneToMany (this goes on user because is zero to many roles for a user)
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

    @ManyToOne  // many schedule details to one Schedule (a schedule may have one to many schedule details) - create the Schedule object instead of just variable ScheduleId
    @JoinColumn(name="scheduleId")
    private Schedule schedule;

    @ManyToOne  // many roles to one User (a user may have zero to many roles) - create the User object instead of just variable userId
    @JoinColumn(name="userId")
    private User user;

        /**
         * Instantiates a new ScheduleDetail.
         */
        public ScheduleDetail() {
    }

        /**
         * Instantiates a new Schedule detail.
         *
         * @param startTime1 the start time 1
         * @param endTime1   the end time 1
         * @param startTime2 the start time 2
         * @param endTime2   the end time 2
         * @param startTime3 the start time 3
         * @param endTime3   the end time 3
         * @param startTime4 the start time 4
         * @param endTime4   the end time 4
         * @param startTime5 the start time 5
         * @param endTime5   the end time 5
         * @param startTime6 the start time 6
         * @param endTime6   the end time 6
         * @param startTime7 the start time 7
         * @param endTime7   the end time 7
         * @param userName   the user name
         * @param schedule   the schedule
         * @param user       the user
         */
        public ScheduleDetail(String startTime1, String endTime1, String startTime2, String endTime2, String startTime3, String endTime3, String startTime4, String endTime4, String startTime5, String endTime5, String startTime6, String endTime6, String startTime7, String endTime7, String userName, Schedule schedule, User user) {
        this.startTime1 = startTime1;
        this.endTime1 = endTime1;
        this.startTime2 = startTime2;
        this.endTime2 = endTime2;
        this.startTime3 = startTime3;
        this.endTime3 = endTime3;
        this.startTime4 = startTime4;
        this.endTime4 = endTime4;
        this.startTime5 = startTime5;
        this.endTime5 = endTime5;
        this.startTime6 = startTime6;
        this.endTime6 = endTime6;
        this.startTime7 = startTime7;
        this.endTime7 = endTime7;
        this.userName = userName;
        this.schedule = schedule;
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
         * Gets start time 1.
         *
         * @return the start time 1
         */
        public String getStartTime1() {
        return startTime1;
    }

        /**
         * Sets start time 1.
         *
         * @param startTime1 the start time 1
         */
        public void setStartTime1(String startTime1) {
        this.startTime1 = startTime1;
    }

        /**
         * Gets end time 1.
         *
         * @return the end time 1
         */
        public String getEndTime1() {
        return endTime1;
    }

        /**
         * Sets end time 1.
         *
         * @param endTime1 the end time 1
         */
        public void setEndTime1(String endTime1) {
        this.endTime1 = endTime1;
    }

        /**
         * Gets start time 2.
         *
         * @return the start time 2
         */
        public String getStartTime2() {
        return startTime2;
    }

        /**
         * Sets start time 2.
         *
         * @param startTime2 the start time 2
         */
        public void setStartTime2(String startTime2) {
        this.startTime2 = startTime2;
    }

        /**
         * Gets end time 2.
         *
         * @return the end time 2
         */
        public String getEndTime2() {
        return endTime2;
    }

        /**
         * Sets end time 2.
         *
         * @param endTime2 the end time 2
         */
        public void setEndTime2(String endTime2) {
        this.endTime2 = endTime2;
    }

        /**
         * Gets start time 3.
         *
         * @return the start time 3
         */
        public String getStartTime3() {
        return startTime3;
    }

        /**
         * Sets start time 3.
         *
         * @param startTime3 the start time 3
         */
        public void setStartTime3(String startTime3) {
        this.startTime3 = startTime3;
    }

        /**
         * Gets end time 3.
         *
         * @return the end time 3
         */
        public String getEndTime3() {
        return endTime3;
    }

        /**
         * Sets end time 3.
         *
         * @param endTime3 the end time 3
         */
        public void setEndTime3(String endTime3) {
        this.endTime3 = endTime3;
    }

        /**
         * Gets start time 4.
         *
         * @return the start time 4
         */
        public String getStartTime4() {
        return startTime4;
    }

        /**
         * Sets start time 4.
         *
         * @param startTime4 the start time 4
         */
        public void setStartTime4(String startTime4) {
        this.startTime4 = startTime4;
    }

        /**
         * Gets end time 4.
         *
         * @return the end time 4
         */
        public String getEndTime4() {
        return endTime4;
    }

        /**
         * Sets end time 4.
         *
         * @param endTime4 the end time 4
         */
        public void setEndTime4(String endTime4) {
        this.endTime4 = endTime4;
    }

        /**
         * Gets start time 5.
         *
         * @return the start time 5
         */
        public String getStartTime5() {
        return startTime5;
    }

        /**
         * Sets start time 5.
         *
         * @param startTime5 the start time 5
         */
        public void setStartTime5(String startTime5) {
        this.startTime5 = startTime5;
    }

        /**
         * Gets end time 5.
         *
         * @return the end time 5
         */
        public String getEndTime5() {
        return endTime5;
    }

        /**
         * Sets end time 5.
         *
         * @param endTime5 the end time 5
         */
        public void setEndTime5(String endTime5) {
        this.endTime5 = endTime5;
    }

        /**
         * Gets start time 6.
         *
         * @return the start time 6
         */
        public String getStartTime6() {
        return startTime6;
    }

        /**
         * Sets start time 6.
         *
         * @param startTime6 the start time 6
         */
        public void setStartTime6(String startTime6) {
        this.startTime6 = startTime6;
    }

        /**
         * Gets end time 6.
         *
         * @return the end time 6
         */
        public String getEndTime6() {
        return endTime6;
    }

        /**
         * Sets end time 6.
         *
         * @param endTime6 the end time 6
         */
        public void setEndTime6(String endTime6) {
        this.endTime6 = endTime6;
    }

        /**
         * Gets start time 7.
         *
         * @return the start time 7
         */
        public String getStartTime7() {
        return startTime7;
    }

        /**
         * Sets start time 7.
         *
         * @param startTime7 the start time 7
         */
        public void setStartTime7(String startTime7) {
        this.startTime7 = startTime7;
    }

        /**
         * Gets end time 7.
         *
         * @return the end time 7
         */
        public String getEndTime7() {
        return endTime7;
    }

        /**
         * Sets end time 7.
         *
         * @param endTime7 the end time 7
         */
        public void setEndTime7(String endTime7) {
        this.endTime7 = endTime7;
    }

        /**
         * Gets schedule.
         *
         * @return the schedule
         */
        public Schedule getSchedule() {
        return schedule;
    }

        /**
         * Sets schedule.
         *
         * @param schedule the schedule
         */
        public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
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
        ScheduleDetail that = (ScheduleDetail) o;
        return id == that.id &&
                Objects.equals(startTime1, that.startTime1) &&
                Objects.equals(endTime1, that.endTime1) &&
                Objects.equals(startTime2, that.startTime2) &&
                Objects.equals(endTime2, that.endTime2) &&
                Objects.equals(startTime3, that.startTime3) &&
                Objects.equals(endTime3, that.endTime3) &&
                Objects.equals(startTime4, that.startTime4) &&
                Objects.equals(endTime4, that.endTime4) &&
                Objects.equals(startTime5, that.startTime5) &&
                Objects.equals(endTime5, that.endTime5) &&
                Objects.equals(startTime6, that.startTime6) &&
                Objects.equals(endTime6, that.endTime6) &&
                Objects.equals(startTime7, that.startTime7) &&
                Objects.equals(endTime7, that.endTime7) &&
                Objects.equals(userName, that.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startTime1, endTime1, startTime2, endTime2, startTime3, endTime3, startTime4, endTime4, startTime5, endTime5, startTime6, endTime6, startTime7, endTime7, userName);
    }

    @Override
    public String toString() {
        return "ScheduleDetail{" +
                "id=" + id +
                ", startTime1='" + startTime1 + '\'' +
                ", endTime1='" + endTime1 + '\'' +
                ", startTime2='" + startTime2 + '\'' +
                ", endTime2='" + endTime2 + '\'' +
                ", startTime3='" + startTime3 + '\'' +
                ", endTime3='" + endTime3 + '\'' +
                ", startTime4='" + startTime4 + '\'' +
                ", endTime4='" + endTime4 + '\'' +
                ", startTime5='" + startTime5 + '\'' +
                ", endTime5='" + endTime5 + '\'' +
                ", startTime6='" + startTime6 + '\'' +
                ", endTime6='" + endTime6 + '\'' +
                ", startTime7='" + startTime7 + '\'' +
                ", endTime7='" + endTime7 + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
    }
