package com.tinroofsoftware.calendar;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TestCalendarRepository
{
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private EventCalendarRepository calendarRepository;

    private String holidays = "{ 'name': 'holidays', 'user': 'admin' }"
        .replace( '\'', '"' );

	@Before
	public void deleteAllBeforeTests() throws Exception {
		calendarRepository.deleteAll();
	}

	@Test
	public void shouldReturnRepositoryIndex() throws Exception {
        mockMvc
            .perform( get( "/" ) )
            .andDo( print() )
            .andExpect( status().isOk() )
            .andExpect( jsonPath("$._links.calendars" ).exists() );
	}

	@Test
	public void createCalendar() throws Exception {
        mockMvc
            .perform( post( "/calendars" )
                .content( holidays ) )
            .andExpect(	status().isCreated() )
            .andExpect( header().string( "Location", containsString( "calendars/" ) ) );
	}
}
