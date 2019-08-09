package com.penapereira.example.constructs.singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.penapereira.example.constructs.app.ExampleRunnerInterface;

@Component
public class SingletonExampleRunner implements ExampleRunnerInterface {

	private static final Logger log = LoggerFactory.getLogger(SingletonExampleRunner.class);

	@Override
	public void runExample() throws Exception {
		log.trace("Instanciating a Singleton");

		Singleton myInstance = Singleton.instance();
		myInstance.doSomething();
	}
}
