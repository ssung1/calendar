package com.tinroofsoftware.calendar.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tinroofsoftware.calendar.DateFormatter;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;

@Entity
public class Event
{
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private long id;

    @ManyToOne
    private EventCalendar calendar;

    private String title;

    @Temporal( TemporalType.TIMESTAMP )
    @JsonSerialize( using = DateFormatter.class )
    private Calendar dateTime;

    private String location;

    @ElementCollection
    private List<String> attendees = new ArrayList<>();

    /**
     * number of minutes before event time
     */
    private Integer reminderTime;
    private Boolean reminderSent = false;

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle( String title ) {
        this.title = title;
    }

    public Calendar getDateTime() {
        return dateTime;
    }

    public void setDateTime( Calendar dateTime ) {
        this.dateTime = dateTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation( String location ) {
        this.location = location;
    }

    public Integer getReminderTime() {
        return reminderTime;
    }

    public void setReminderTime( Integer reminderTime ) {
        this.reminderTime = reminderTime;
    }

    public Boolean getReminderSent() {
        return reminderSent;
    }

    public void setReminderSent( Boolean reminderSent ) {
        this.reminderSent = reminderSent;
    }

    public EventCalendar getCalendar() {
        return calendar;
    }

    public void setCalendar( EventCalendar calendar ) {
        this.calendar = calendar;
    }

    public List<String> getAttendees() {
        return this.attendees;
    }

    public void setAttendees( List<String> attendees ) {
        this.attendees = attendees;
    }
}
