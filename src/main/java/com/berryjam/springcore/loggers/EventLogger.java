package com.berryjam.springcore.loggers;

import com.berryjam.springcore.beans.Event;

public interface EventLogger {
    void logEvent(Event event);
}
