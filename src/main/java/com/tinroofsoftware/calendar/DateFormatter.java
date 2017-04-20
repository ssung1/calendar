package com.tinroofsoftware.calendar;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Calendar;

public class DateFormatter extends JsonSerializer<Calendar> {
    @Override
    public void serialize( Calendar value, JsonGenerator gen,
        SerializerProvider arg2) throws
        IOException, JsonProcessingException {

        String formattedDate = DateUtil.FORMAT.format( value.getTime() );
        gen.writeString(formattedDate);
    }
}