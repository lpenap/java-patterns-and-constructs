package com.penapereira.example.constructs.app.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.EventQueue;

import org.junit.jupiter.api.Test;

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
}
