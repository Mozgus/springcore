package com.berryjam.springcore.loggers;

import com.berryjam.springcore.beans.Event;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.util.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestCacheFileEventLogger {

    private File file;

    @Before
    public void createFile() throws IOException {
        this.file = File.createTempFile("test", "CacheFileEventLogger");
    }

    @After
    public void removeFile() {
        file.delete();
    }

    private CacheFileEventLogger createAndInitCacheFileEventLogger() throws IOException {
        CacheFileEventLogger logger = new CacheFileEventLogger(file.getAbsolutePath(), 2);
        logger.init();
        logger.initCache();
        return logger;
    }

    @Test
    public void testLogEvent() throws IOException {
        Event event = new Event(new Date(), DateFormat.getDateInstance());
        CacheFileEventLogger logger = createAndInitCacheFileEventLogger();

        String contents = FileUtils.readFileToString(this.file, StandardCharsets.UTF_8);
        assertTrue("File is empty initially", contents.isEmpty());

        logger.logEvent(event);

        contents = FileUtils.readFileToString(this.file, StandardCharsets.UTF_8);
        assertTrue("File is empty as events in cache", contents.isEmpty());

        logger.logEvent(event);

        contents = FileUtils.readFileToString(this.file, StandardCharsets.UTF_8);
        assertFalse("File not empty, cache was dumped", contents.isEmpty());
    }

    @Test
    public void testDestroy() throws IOException {
        Event event = new Event(new Date(), DateFormat.getDateInstance());
        CacheFileEventLogger logger = createAndInitCacheFileEventLogger();

        String contents = FileUtils.readFileToString(this.file, StandardCharsets.UTF_8);
        assertTrue("File is empty initially", contents.isEmpty());

        logger.logEvent(event);

        contents = FileUtils.readFileToString(this.file, StandardCharsets.UTF_8);
        assertTrue("File is empty as events in cache", contents.isEmpty());

        logger.destroy();

        contents = FileUtils.readFileToString(this.file, StandardCharsets.UTF_8);
        assertFalse("File not empty, cache was dumped", contents.isEmpty());
    }

}
