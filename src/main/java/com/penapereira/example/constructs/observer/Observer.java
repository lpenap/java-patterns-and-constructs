package com.penapereira.example.constructs.observer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Observer implements PropertyChangeListener {

	private static final Logger log = LoggerFactory.getLogger(Observer.class);

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		log.trace(String.format("  Property updated!. \"%s\": %d->%d", event.getPropertyName(), event.getOldValue(),
				event.getNewValue()));
	}

}
