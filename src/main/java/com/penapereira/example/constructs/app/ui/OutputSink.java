package com.penapereira.example.constructs.app.ui;

/**
 * Destination for text produced by the application, such as log output
 * forwarded by {@link GuiAppender}.
 */
public interface OutputSink {

    void appendOutput(String text);
}
