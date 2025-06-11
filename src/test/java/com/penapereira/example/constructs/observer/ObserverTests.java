package com.penapereira.example.constructs.observer;

import static org.junit.jupiter.api.Assertions.*;

import java.beans.PropertyChangeEvent;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.jupiter.api.Test;

class ObserverTests {
    @Test
    void observerReceivesEvent() {
        Observable subject = new Observable();
        AtomicReference<PropertyChangeEvent> received = new AtomicReference<>();
        subject.addPropertyChangeListener(received::set);
        for (int i = 0; i < 5 && received.get() == null; i++) {
            subject.doSomethingWith(5);
        }
        assertNotNull(received.get());
        assertEquals("myProperty", received.get().getPropertyName());
        assertEquals(5, received.get().getOldValue());
        assertTrue(((int) received.get().getNewValue()) > 5);
    }

    @Test
    void listenerCanBeRemoved() {
        Observable subject = new Observable();
        Observer obs = new Observer();
        subject.addPropertyChangeListener(obs);
        subject.removePropertyChangeListener(obs);
        subject.doSomethingWith(1); // should not throw
        assertEquals(0, subject.getSupport().getPropertyChangeListeners().length);
    }
}
