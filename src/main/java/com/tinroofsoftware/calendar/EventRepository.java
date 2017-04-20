package com.tinroofsoftware.calendar;

import com.tinroofsoftware.calendar.entity.Event;
import com.tinroofsoftware.calendar.entity.EventCalendar;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@RepositoryRestResource()
public interface EventRepository
    extends PagingAndSortingRepository<Event, Long>
{
    List<Event> findByCalendarAndTitle(
        @Param( "calendar" ) EventCalendar calendar,
        @Param( "title" ) String title );

    List<Event> findByDateTimeBetween(
        @Param( "from" ) @DateTimeFormat( pattern = "yyyyMMdd" ) Calendar from,
        @Param( "to" ) @DateTimeFormat( pattern = "yyyyMMdd" ) Calendar to );
}
