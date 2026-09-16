package com.penapereira.example.constructs.app.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.EventQueue;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.LoggingEvent;

class GuiAppenderTests {
    @Test
    void appendsMessageToOutputSink() throws Exception {
        StringBuilder output = new StringBuilder();
        GuiAppender appender = new GuiAppender(output::append);
        appender.start();

        LoggingEvent event = new LoggingEvent();
        event.setLevel(Level.INFO);
        event.setMessage("test message");

        appender.doAppend(event);
        EventQueue.invokeAndWait(() -> {});

        assertEquals("test message" + System.lineSeparator(), output.toString());
    }

    @Test
    void attachesItselfToRootLoggerWhenInitialized() throws Exception {
        GuiAppender appender = new GuiAppender(text -> {});
        ch.qos.logback.classic.Logger root = (ch.qos.logback.classic.Logger) LoggerFactory
                .getLogger(Logger.ROOT_LOGGER_NAME);
        assertFalse(root.isAttached(appender));
        try {
            appender.afterPropertiesSet();

            assertTrue(appender.isStarted());
            assertTrue(root.isAttached(appender));
        } finally {
            root.detachAppender(appender);
            appender.stop();
        }
    }
}
