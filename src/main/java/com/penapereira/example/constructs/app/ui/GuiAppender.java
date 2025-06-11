package com.penapereira.example.constructs.app.ui;

import java.awt.EventQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;

@Component
public class GuiAppender extends AppenderBase<ILoggingEvent> implements InitializingBean {

    @Autowired
    private MainWindow mainWindow;

    @Override
    public void afterPropertiesSet() throws Exception {
        ((ch.qos.logback.classic.Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME)).addAppender(this);
        start();
    }

    @Override
    protected void append(ILoggingEvent eventObject) {
        EventQueue.invokeLater(() -> mainWindow.appendOutput(eventObject.getFormattedMessage() + System.lineSeparator()));
    }
}
