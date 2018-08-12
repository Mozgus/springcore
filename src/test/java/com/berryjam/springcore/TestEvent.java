package com.berryjam.springcore;

import static org.junit.Assert.assertTrue;

import java.text.DateFormat;
import java.util.Date;

import com.berryjam.springcore.beans.Event;
import org.junit.Test;

public class TestEvent {
    @Test
    public void testToString() {
        Date date = new Date();
        DateFormat format = DateFormat.getDateInstance();
        Event event = new Event(date, format);
        String str = event.toString();
        assertTrue(str.contains(format.format(date)));
    }

    @Test
    public void testToString2() {
        Date date = new Date();
        DateFormat format = DateFormat.getDateTimeInstance();
        Event event = new Event(date, format);
        String str = event.toString();
        assertTrue(str.contains(format.format(date)));
    }

}
