package com.tinroofsoftware.calendar;

import com.tinroofsoftware.calendar.entity.EventCalendar;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource( collectionResourceRel = "calendars",
    path = "calendars" )
public interface EventCalendarRepository extends
    PagingAndSortingRepository<EventCalendar, Long>
{
    List<EventCalendar> findByName( @Param( "name" ) String name );
}
