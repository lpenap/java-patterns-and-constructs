package com.penapereira.example.constructs.observer;

import java.util.Random;

public class Observable extends ObservableAbstract {

	public void doSomethingWith(int number) {
		var newNumber = number + new Random().nextInt(number);

		// Notify observers, a.k.a PropertyChangeListeners
		getSupport().firePropertyChange("myProperty", number, newNumber);
	}
}
