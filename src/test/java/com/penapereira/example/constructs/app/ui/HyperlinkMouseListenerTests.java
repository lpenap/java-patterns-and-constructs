package com.penapereira.example.constructs.app.ui;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.util.concurrent.atomic.AtomicReference;

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

    private static ApplicationProperties props() {
        ApplicationProperties props = new ApplicationProperties();
        props.setLinkColor("#000000");
        props.setLinkColorHover("#ffffff");
        return props;
    }

    private static MouseEvent eventOn(JLabel label, int id) {
        return new MouseEvent(label, id, 0, 0, 0, 0, 1, false);
    }

    @Test
    void clickOpensTheLinkShownWhenTheMouseEntered() {
        AtomicReference<URI> opened = new AtomicReference<>();
        HyperlinkMouseListener listener = new HyperlinkMouseListener(props(), opened::set);
        JLabel label = new JLabel("http://example.com");

        listener.mouseEntered(eventOn(label, MouseEvent.MOUSE_ENTERED));
        listener.mousePressed(eventOn(label, MouseEvent.MOUSE_PRESSED));
        listener.mouseReleased(eventOn(label, MouseEvent.MOUSE_RELEASED));
        listener.mouseClicked(eventOn(label, MouseEvent.MOUSE_CLICKED));

        assertEquals(URI.create("http://example.com"), opened.get());
    }

    @Test
    void clickOnMalformedLinkIsLoggedNotThrown() {
        AtomicReference<URI> opened = new AtomicReference<>();
        HyperlinkMouseListener listener = new HyperlinkMouseListener(props(), opened::set);
        JLabel label = new JLabel("http://exa mple.com");

        listener.mouseEntered(eventOn(label, MouseEvent.MOUSE_ENTERED));
        assertDoesNotThrow(() -> listener.mouseClicked(eventOn(label, MouseEvent.MOUSE_CLICKED)));

        assertNull(opened.get());
    }

    @Test
    void failureToOpenTheBrowserIsLoggedNotThrown() {
        HyperlinkMouseListener listener = new HyperlinkMouseListener(props(), uri -> {
            throw new IOException("no browser");
        });
        JLabel label = new JLabel("http://example.com");

        listener.mouseEntered(eventOn(label, MouseEvent.MOUSE_ENTERED));
        assertDoesNotThrow(() -> listener.mouseClicked(eventOn(label, MouseEvent.MOUSE_CLICKED)));
    }
}
