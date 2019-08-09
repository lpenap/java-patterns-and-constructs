package com.penapereira.example.constructs.observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.penapereira.example.constructs.app.ExampleRunnerInterface;

@Component
public class ObserverExampleRunner implements ExampleRunnerInterface {

	private static final Logger log = LoggerFactory.getLogger(ObserverExampleRunner.class);

	@Override
	public void runExample() {
		log.trace("Executing Observer pattern:");

		Observable subject1 = new Observable();
		Observable subject2 = new Observable();

		Observer observer = new Observer();

		subject1.addPropertyChangeListener(observer);
		subject2.addPropertyChangeListener(observer);

		subject1.doSomethingWith(5);
		subject2.doSomethingWith(10);
	}
}
