package com.berryjam.springcore.loggers;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.util.Date;

import com.berryjam.springcore.beans.Event;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestFileEventLogger {

    private File file;

    @Before
    public void createFile() throws IOException {
        this.file = File.createTempFile("test", "FileEventLogger");
    }

    @After
    public void removeFile() {
        file.delete();
    }

    @Test
    public void testInit() throws IOException {
        FileEventLogger logger = new FileEventLogger(file.getAbsolutePath());
        logger.init();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInitFail() throws IOException {
        file.setReadOnly();
        FileEventLogger logger = new FileEventLogger(file.getAbsolutePath());
        logger.init();
    }

    @Test
    public void testLogEvent() throws IOException {
        Event event = new Event(new Date(), DateFormat.getDateInstance());
        FileEventLogger logger = new FileEventLogger(file.getAbsolutePath());
        logger.init();

        String contents = FileUtils.readFileToString(this.file, StandardCharsets.UTF_8);
        assertTrue(contents.isEmpty());

        logger.logEvent(event);

        contents = FileUtils.readFileToString(this.file, StandardCharsets.UTF_8);
        assertFalse(contents.isEmpty());
    }

}
