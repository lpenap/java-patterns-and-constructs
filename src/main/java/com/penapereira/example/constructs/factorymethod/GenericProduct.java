package com.penapereira.example.constructs.factorymethod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class GenericProduct {

	private static final Logger log = LoggerFactory.getLogger(GenericProduct.class);

	public abstract String factoryMethod();

	public void build() {
		log.trace(String.format("  Building %s", factoryMethod()));
	}
}
