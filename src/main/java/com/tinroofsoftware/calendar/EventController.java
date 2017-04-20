package com.tinroofsoftware.calendar;

import com.tinroofsoftware.calendar.entity.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@RestController
public class EventController {
    Logger LOG = LoggerFactory.getLogger( getClass() );

    @Autowired
    EventRepository repo;

    DateUtil du = new DateUtil();

    @RequestMapping( "/events/day" )
    public List<Event> eventsOfTheDay(
        @RequestParam( "date" ) @DateTimeFormat( pattern = "yyyyMMdd" )
        Calendar date )
    {
        DateRange dr = du.rangeOfDay( date );
        return repo.findByDateTimeBetween( dr.getFrom(), dr.getTo() );
    }

    @RequestMapping( "/events/week" )
    public List<Event> eventsOfTheWeek(
        @RequestParam( "date" ) @DateTimeFormat( pattern = "yyyyMMdd" )
        Calendar date )
    {
        DateRange dr = du.rangeOfWeek( date );
        return repo.findByDateTimeBetween( dr.getFrom(), dr.getTo() );
    }

    @RequestMapping( "/events/month" )
    public List<Event> eventsOfTheMonth(
        @RequestParam( "date" ) @DateTimeFormat( pattern = "yyyyMMdd" )
        Calendar date )
    {
        DateRange dr = du.rangeOfMonth( date );
        return repo.findByDateTimeBetween( dr.getFrom(), dr.getTo() );
    }
}
