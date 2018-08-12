package com.berryjam.springcore.loggers;

import com.berryjam.springcore.beans.Event;

public class ConsoleEventLogger implements EventLogger {
    @Override
    public void logEvent(Event event) {
        System.out.println(event.toString());
    }
}
