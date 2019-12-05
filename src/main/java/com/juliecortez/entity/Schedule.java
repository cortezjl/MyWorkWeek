package com.juliecortez.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A class to represent a schedule (header level).
 *
 * @author JCortez
 */

@Entity(name = "Schedule") // Annotation to indicate this class is to be managed by Hibernate.  This is the name of the class
@Table(name = "schedule") // case sensitive!   This is the name of the table
@NamedQueries({
        @NamedQuery(
                name = "findScheduleByStartDate",
                query = "select s from Schedule s where s.startDate = :startDate"
        )
})
public class Schedule {
    // Every Entity must have a unique identifier which is annotated @Id
    // Notice there is no @Column here as the column and instance variable name are the same
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int id;

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
    // There is one Schedule with zero to many Schedule Details
    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<ScheduleDetail> scheduleDetails = new HashSet<>();   // Hold collection/set of Schedule Details for the Schedule - zero to many


    /**
     * Instantiates a new Schedule.
     */
    public Schedule() {
    }

    /**
     * Instantiates a new Schedule object.
     *
     * @param startDate the Schedule start date
     * @param endDate   the Schedule end date
     */
    public Schedule(LocalDate startDate, LocalDate endDate) {
        this();
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Instantiates a new Schedule object.
     *
     * @param id        the id
     * @param startDate the Schedule start date
     * @param endDate   the Schedule end date
     */
    public Schedule(int id, LocalDate startDate, LocalDate endDate) {
        this(startDate, endDate);
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

    /**
     * Gets Schedule Details.
     *
     * @return scheduleDetails the Schedule Details
     */
    public Set<ScheduleDetail> getScheduleDetails() {
        return scheduleDetails;
    }

    /**
     * Sets Schedule Details.
     *
     * @param scheduleDetails the Schedule Details
     */
    public void setScheduleDetails(Set<ScheduleDetail> scheduleDetails) {
        this.scheduleDetails = scheduleDetails;
    }

    /**
     * Add Schedule Detail.
     *
     * @param scheduleDetail the schedule detail for a user for a given schedule
     */
    public void addScheduleDetail(ScheduleDetail scheduleDetail) {
        scheduleDetails.add(scheduleDetail);
        scheduleDetail.setSchedule(this);
    }

    /**
     * Remove scheduleDetail.
     *
     * @param scheduleDetail the schedule detail for a user for a given schedule
     */
    public void removeRole(ScheduleDetail scheduleDetail) {
        scheduleDetails.remove(scheduleDetail);
        scheduleDetail.setSchedule(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return id == schedule.id &&
                Objects.equals(startDate, schedule.startDate) &&
                Objects.equals(endDate, schedule.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startDate, endDate);
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
