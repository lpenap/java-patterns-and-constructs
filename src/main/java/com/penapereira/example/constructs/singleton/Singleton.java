package com.penapereira.example.constructs.singleton;

public class Singleton {

	private static Singleton uniqueInstance = null;

	private Singleton() {
	}

	public static Singleton instance() {
		if (uniqueInstance == null) {
			uniqueInstance = new Singleton();
		}
		return uniqueInstance;
	}

	public void doSomething() {
	}

}
