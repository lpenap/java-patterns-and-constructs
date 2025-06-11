package com.penapereira.example.constructs.app.ui;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.EventQueue;
import java.lang.reflect.Field;

import javax.swing.JTextArea;

import org.junit.jupiter.api.Test;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.LoggingEvent;

class GuiAppenderTests {
    @Test
    void appendsMessageToTextArea() throws Exception {
        // Allocate a MainWindow instance without triggering the JFrame constructor
        Field theUnsafeField = sun.misc.Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafeField.setAccessible(true);
        sun.misc.Unsafe unsafe = (sun.misc.Unsafe) theUnsafeField.get(null);

        MainWindow window = (MainWindow) unsafe.allocateInstance(MainWindow.class);
        JTextArea area = new JTextArea();
        Field f = MainWindow.class.getDeclaredField("outputArea");
        f.setAccessible(true);
        f.set(window, area);

        GuiAppender appender = new GuiAppender();
        Field mw = GuiAppender.class.getDeclaredField("mainWindow");
        mw.setAccessible(true);
        mw.set(appender, window);
        appender.start();

        LoggingEvent event = new LoggingEvent();
        event.setLevel(Level.INFO);
        event.setMessage("test message");

        appender.doAppend(event);
        EventQueue.invokeAndWait(() -> {});

        assertEquals("test message" + System.lineSeparator(), area.getText());
    }
}
