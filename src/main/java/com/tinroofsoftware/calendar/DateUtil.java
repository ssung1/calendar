package com.tinroofsoftware.calendar;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtil
{
    static final SimpleDateFormat FORMAT =
        new SimpleDateFormat( "yyyy-MM-dd'T'HH:mm:ss" );

    final int DAYS_IN_A_WEEK = 7;

    private void clearTime( Calendar c ) {
        c.set( Calendar.HOUR_OF_DAY, 0 );
        c.set( Calendar.MINUTE, 0 );
        c.set( Calendar.SECOND, 0 );
        c.set( Calendar.MILLISECOND, 0 );
    }

    public DateRange rangeOfDay( Calendar date )
    {
        Calendar from = (Calendar)date.clone();
        clearTime( from );

        Calendar to = (Calendar)from.clone();
        to.add( Calendar.DATE, 1 );

        return new DateRange( from, to );
    }

    public DateRange rangeOfWeek( Calendar date )
    {

        Calendar from = (Calendar)date.clone();
        clearTime( from );
        int dow = from.get( Calendar.DAY_OF_WEEK );
        from.add( Calendar.DATE, Calendar.SUNDAY - dow );

        Calendar to = (Calendar)from.clone();
        to.add( Calendar.DATE, DAYS_IN_A_WEEK );

        return new DateRange( from, to );
    }

    public DateRange rangeOfMonth( Calendar date )
    {
        Calendar from = (Calendar)date.clone();
        clearTime( from );
        from.set( Calendar.DAY_OF_MONTH, 1 );

        Calendar to = (Calendar)from.clone();
        to.add( Calendar.MONTH, 1 );

        return new DateRange( from, to );
    }
}
