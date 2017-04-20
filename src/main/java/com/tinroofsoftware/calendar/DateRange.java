package com.tinroofsoftware.calendar;

import java.util.Calendar;

public class DateRange
{
    private Calendar from;
    private Calendar to;

    public DateRange( Calendar from, Calendar to )
    {
        this.from = from;
        this.to = to;
    }

    public Calendar getFrom() {
        return from;
    }

    public Calendar getTo() {
        return to;
    }
}
