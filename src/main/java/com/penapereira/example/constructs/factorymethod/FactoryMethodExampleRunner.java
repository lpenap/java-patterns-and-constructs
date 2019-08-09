package com.penapereira.example.constructs.factorymethod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.penapereira.example.constructs.app.ExampleRunnerInterface;

@Component
public class FactoryMethodExampleRunner implements ExampleRunnerInterface {

	private static final Logger log = LoggerFactory.getLogger(FactoryMethodExampleRunner.class);

	@Override
	public void runExample() throws Exception {
		log.trace("Executing Factory Method pattern implementation:");

		GenericProduct prod1 = new ConcreteProductA();
		GenericProduct prod2 = new ConcreteProductB();

		prod1.build();
		prod2.build();
	}

}
