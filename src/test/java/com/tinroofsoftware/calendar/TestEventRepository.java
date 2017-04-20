package com.tinroofsoftware.calendar;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet
    .AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request
    .MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request
    .MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result
    .MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result
    .MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TestEventRepository
{
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private EventRepository eventRepository;

    private String sprintPlanning = String.join( "\n",
        "{",
        "  'calendar': '',", // no constraint for now...
        "  'title': 'Sprint Planning',",
        "  'dateTime': '2017-04-24T10:00:00Z',",
        "  'location': 'Room 100',",
        "  'attendees' : [ 'Bob', 'Wendy', 'Bernard' ],",
        "  'reminderTime': 15",
        "}"
    ).replace( '\'', '"' );

    private String retrospective = String.join( "\n",
        "{",
        "  'calendar': '',", // no constraint for now...
        "  'title': 'Retrospective',",
        "  'dateTime': '2017-05-01T10:00:00Z',",
        "  'location': 'Room 101',",
        "  'attendees' : [ 'Bob', 'Wendy', 'Bernard' ],",
        "  'reminderTime': 20",
        "}"
    ).replace( '\'', '"' );

	@Before
	public void deleteAllBeforeTests() throws Exception {
		eventRepository.deleteAll();
	}

	@Test
	public void shouldReturnRepositoryIndex() throws Exception {
        mockMvc
            .perform( get( "/" ) )
            .andDo( print() )
            .andExpect( status().isOk() )
            .andExpect( jsonPath("$._links.events" ).exists() );
	}

	@Test
	public void createEvent() throws Exception {
        mockMvc
            .perform( post( "/events" )
                .content( sprintPlanning ) )
            .andExpect( status().isCreated() )
            .andExpect( header().string( "Location", containsString( "events/" ) ) );
	}

	@Test
	public void searchEventByDate() throws Exception {
        mockMvc.perform( post( "/events" )
                .content( sprintPlanning ) )
            .andExpect( status().isCreated() )
            .andExpect( header().string( "Location", containsString( "events/" ) ) );
        mockMvc.perform( post( "/events" )
            .content( retrospective ) )
            .andExpect( status().isCreated() )
            .andExpect( header().string( "Location", containsString( "events/" ) ) );

        mockMvc.perform( get( "/events/search/findByDateTimeBetween?from=20170423&to=20170425" ) )
            .andExpect( status().isOk() )
            .andExpect( jsonPath( "$._embedded.events[0].title" ).value( "Sprint Planning" ) );
	}

	@Test
	public void eventsOfTheDay() throws Exception {
        mockMvc.perform( post( "/events" )
                .content( sprintPlanning ) )
            .andExpect( status().isCreated() )
            .andExpect( header().string( "Location", containsString( "events/" ) ) );
        mockMvc.perform( post( "/events" )
                .content( retrospective ) )
            .andExpect( status().isCreated() )
            .andExpect( header().string( "Location", containsString( "events/" ) ) );

        mockMvc.perform( get( "/events/day?date=20170424" ) )
            .andExpect( status().isOk() )
            .andExpect( jsonPath( "$[0].title" ).value( "Sprint Planning" ) );
	}

	@Test
	public void eventsOfTheWeek() throws Exception {
        mockMvc.perform( post( "/events" )
                .content( sprintPlanning ) )
            .andExpect( status().isCreated() )
            .andExpect( header().string( "Location", containsString( "events/" ) ) );
        mockMvc.perform( post( "/events" )
                .content( retrospective ) )
            .andExpect( status().isCreated() )
            .andExpect( header().string( "Location", containsString( "events/" ) ) );

        mockMvc.perform( get( "/events/week?date=20170428" ) )
            .andExpect( status().isOk() )
            .andExpect( jsonPath( "$[0].title" ).value( "Sprint Planning" ) );
	}

	@Test
	public void eventsOfTheMonth() throws Exception {
        mockMvc.perform( post( "/events" )
                .content( sprintPlanning ) )
            .andExpect( status().isCreated() )
            .andExpect( header().string( "Location", containsString( "events/" ) ) );
        mockMvc.perform( post( "/events" )
                .content( retrospective ) )
            .andExpect( status().isCreated() )
            .andExpect( header().string( "Location", containsString( "events/" ) ) );

        mockMvc.perform( get( "/events/month?date=20170401" ) )
            .andExpect( status().isOk() )
            .andExpect( jsonPath( "$[0].title" ).value( "Sprint Planning" ) );
	}
}
