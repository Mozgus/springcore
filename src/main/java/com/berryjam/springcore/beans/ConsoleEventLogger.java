package com.berryjam.springcore.beans;

public class ConsoleEventLogger implements EventLogger {
    @Override
    public void logEvent(String msg) {
        System.out.println(msg);
    }
}
