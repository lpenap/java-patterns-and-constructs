package com.penapereira.example.constructs.app.properties;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MessagesTests {
    @Test
    void settersAndGettersWork() {
        Messages m = new Messages();
        m.setGreeting("G");
        m.setWindowTitle("T");
        m.setHomeUrl("U");
        m.setInfo("I");
        m.setExamplesFound("E");
        m.setEnableTraceToSeeExamplesDetails("TD");
        m.setEnableDebugToSeeExamplesList("DL");
        m.setSeparator("-");
        m.setOutputTitle("OT");

        assertEquals("G", m.getGreeting());
        assertEquals("T", m.getWindowTitle());
        assertEquals("U", m.getHomeUrl());
        assertEquals("I", m.getInfo());
        assertEquals("E", m.getExamplesFound());
        assertEquals("TD", m.getEnableTraceToSeeExamplesDetails());
        assertEquals("DL", m.getEnableDebugToSeeExamplesList());
        assertEquals("-", m.getSeparator());
        assertEquals("OT", m.getOutputTitle());

        String s = m.toString();
        assertTrue(s.contains("G"));
        assertNotEquals(0, m.hashCode());
        assertEquals(m, m);

        Messages other = new Messages();
        other.setGreeting("G");
        other.setWindowTitle("T");
        other.setHomeUrl("U");
        other.setInfo("I");
        other.setExamplesFound("E");
        other.setEnableTraceToSeeExamplesDetails("TD");
        other.setEnableDebugToSeeExamplesList("DL");
        other.setSeparator("-");
        other.setOutputTitle("OT");
        assertEquals(m, other);
        assertEquals(m.hashCode(), other.hashCode());
        assertNotEquals(m, "foo");
    }
}
