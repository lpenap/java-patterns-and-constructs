package com.penapereira.example.constructs.app.ui;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

import org.junit.jupiter.api.Test;

import com.penapereira.example.constructs.app.properties.ApplicationProperties;

class HyperlinkMouseListenerTests {
    @Test
    void hyperlinkChangesColorAndText() {
        ApplicationProperties props = new ApplicationProperties();
        props.setLinkColor("#000000");
        props.setLinkColorHover("#ffffff");
        HyperlinkMouseListener listener = new HyperlinkMouseListener(props);
        JLabel label = new JLabel("http://example.com");

        MouseEvent enter = new MouseEvent(label, MouseEvent.MOUSE_ENTERED, 0, 0, 0, 0, 1, false);
        listener.mouseEntered(enter);
        assertTrue(label.getText().contains("<html>"));
        assertEquals(Color.decode(props.getLinkColorHover()), label.getForeground());

        MouseEvent exit = new MouseEvent(label, MouseEvent.MOUSE_EXITED, 0, 0, 0, 0, 1, false);
        listener.mouseExited(exit);
        assertEquals("http://example.com", label.getText());
        assertEquals(Color.decode(props.getLinkColor()), label.getForeground());
    }
}
