package com.tinroofsoftware.calendar;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import org.junit.Test;

import java.text.ParseException;
import java.util.Calendar;

public class TestDateUtil
{
    DateUtil sut = new DateUtil();

    private Calendar dateOf( String s ) throws ParseException
    {
        Calendar result = Calendar.getInstance();
        result.setTime( DateUtil.FORMAT.parse( s ) );
        return result;
    }

    @Test
    public void rangeOfDay() throws Exception
    {
        DateRange dr = sut.rangeOfDay( dateOf( "2017-03-04T04:05:00" ) );
        assertThat( dr.getFrom(), is( dateOf( "2017-03-04T00:00:00" ) ) );
        assertThat( dr.getTo(), is( dateOf( "2017-03-05T00:00:00" ) ) );
    }

    @Test
    public void rangeOfWeek() throws Exception
    {
        DateRange dr = sut.rangeOfWeek( dateOf( "2017-04-01T04:05:00" ) );
        assertThat( dr.getFrom(), is( dateOf( "2017-03-26T00:00:00" ) ) );
        assertThat( dr.getTo(), is( dateOf( "2017-04-02T00:00:00" ) ) );
    }

    @Test
    public void rangeOfMonth() throws Exception
    {
        DateRange dr = sut.rangeOfMonth( dateOf( "2017-03-02T04:05:00" ) );
        assertThat( dr.getFrom(), is( dateOf( "2017-03-01T00:00:00" ) ) );
        assertThat( dr.getTo(), is( dateOf( "2017-04-01T00:00:00" ) ) );
    }
}
