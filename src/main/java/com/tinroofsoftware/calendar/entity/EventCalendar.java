package com.tinroofsoftware.calendar.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A calendar contains a collection of events
 *
 * Named EventCalendar to avoid confusion with java.util.Calendar
 */
@Entity
public class EventCalendar
{
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private long id;

    private String name;
    private String user;

    @JsonIgnore
    @OneToMany( mappedBy = "calendar" )
    private List<Event> events = new ArrayList<>();

    public long getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public void setUser( String user ) {
        this.user = user;
    }

    public String getName() {

        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public List<Event> getEvents() {
        return events;
    }
}
