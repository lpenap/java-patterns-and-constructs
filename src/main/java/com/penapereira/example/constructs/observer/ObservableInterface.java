package com.penapereira.example.constructs.observer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public interface ObservableInterface {

	PropertyChangeSupport getSupport();

	void addPropertyChangeListener(PropertyChangeListener pcl);

	void removePropertyChangeListener(PropertyChangeListener pcl);

}