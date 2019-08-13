package com.penapereira.example.constructs.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.penapereira.example.constructs.app.ExampleRunnerInterface;

@Component
public class StrategyExampleRunner implements ExampleRunnerInterface {

	private static final Logger log = LoggerFactory.getLogger(StrategyExampleRunner.class);

	@Override
	public void runExample() throws Exception {
		log.trace("Executing Strategy Pattern Implementation");

		// The "Context" will require to execute an algorithm according
		// to a specific strategy.
		Context ctx = new Context(new StrategyImpl1());
		log.trace("  " + ctx.operation());

		// The algorithm can be changed according to a different strategy
		// and can be executed again or instantiated again with the new strategy.
		ctx.setStrategy(new StrategyImpl2());
		log.trace("  " + ctx.operation());
	}

}
