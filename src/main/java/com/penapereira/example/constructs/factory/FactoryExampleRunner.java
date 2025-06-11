package com.penapereira.example.constructs.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.penapereira.example.constructs.app.ExampleRunnerInterface;

@Component
public class FactoryExampleRunner implements ExampleRunnerInterface {

        private static final Logger log = LoggerFactory.getLogger(FactoryExampleRunner.class);

        @Override
        public void runExample() throws Exception {
                log.trace("Executing Factory Pattern Implementation:");

                ProductFactory factory = new ProductFactory();

                Product productA = factory.createProduct("A");
                Product productB = factory.createProduct("B");

                log.trace("  " + productA.name());
                log.trace("  " + productB.name());
        }
}
