package com.berryjam.springcore.loggers;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import com.berryjam.springcore.beans.Event;
import org.apache.commons.io.FileUtils;

public class FileEventLogger implements EventLogger {

    private File file;
    private String filename;

    public FileEventLogger(String filename) {
        this.filename = filename;
    }

    public void init() {
        file = new File(filename);
        if (file.exists() && !file.canWrite()) {
            throw new IllegalArgumentException("Can't write to file " + filename);
        } else if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                throw new IllegalArgumentException("Can't create file", e);
            }

        }
    }

    @Override
    public void logEvent(Event event) {
        try {
            FileUtils.writeStringToFile(file, event.toString(), StandardCharsets.UTF_8, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}