package com.penapereira.example.constructs.app.properties;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

class ApplicationPropertiesTests {
    @Test
    void settersAndGettersWork() {
        ApplicationProperties props = new ApplicationProperties();
        props.setWindowMarginX(10);
        props.setWindowMarginY(20);
        props.setLinkColor("#123456");
        props.setLinkColorHover("#abcdef");
        props.setAppIcon(new ClassPathResource("icon.png"));

        assertEquals(10, props.getWindowMarginX());
        assertEquals(20, props.getWindowMarginY());
        assertEquals("#123456", props.getLinkColor());
        assertEquals("#abcdef", props.getLinkColorHover());
        assertEquals("icon.png", props.getAppIcon().getFilename());

        String s = props.toString();
        assertTrue(s.contains("123456"));
        assertNotEquals(0, props.hashCode());
        assertEquals(props, props);

        ApplicationProperties other = new ApplicationProperties();
        other.setWindowMarginX(10);
        other.setWindowMarginY(20);
        other.setLinkColor("#123456");
        other.setLinkColorHover("#abcdef");
        other.setAppIcon(new ClassPathResource("icon.png"));
        assertEquals(props, other);
        assertEquals(props.hashCode(), other.hashCode());
        assertNotEquals(props, "bar");
    }
}
