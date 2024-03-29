package com.penapereira.example.constructs.observer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Arrays;
import java.util.Iterator;

public abstract class ObservableAbstract implements ObservableInterface {
	protected PropertyChangeSupport support;

	public ObservableAbstract() {
		super();
		this.support = new PropertyChangeSupport(this);
	}

	@Override
	public PropertyChangeSupport getSupport() {
		return this.support;
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		support.addPropertyChangeListener(pcl);
	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener pcl) {
		support.removePropertyChangeListener(pcl);
	}

	protected void removeAllListeners() {
		Iterator<PropertyChangeListener> i = Arrays.asList(support.getPropertyChangeListeners().clone()).iterator();
		while (i.hasNext()) {
			support.removePropertyChangeListener(i.next());
		}
	}

}