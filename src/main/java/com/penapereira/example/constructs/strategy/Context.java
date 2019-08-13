package com.penapereira.example.constructs.strategy;

public class Context {
	Strategy strategy;

	public Context(Strategy strategy) {
		this.strategy = strategy;
	}

	public String operation() {
		return "Operation with " + strategy.executeAlgorithm();
	}

	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}

}
