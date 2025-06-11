package com.penapereira.example.constructs.abstractfactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.penapereira.example.constructs.app.ExampleRunnerInterface;

@Component
public class AbstractFactoryExampleRunner implements ExampleRunnerInterface {

        private static final Logger log =
                        LoggerFactory.getLogger(AbstractFactoryExampleRunner.class);

        @Override
        public void runExample() throws Exception {
                log.trace("Executing Abstract Factory Pattern Implementation:");

                AbstractFactory factory1 = new Factory1();
                AbstractFactory factory2 = new Factory2();

                log.trace("  " + factory1.createProductA().name());
                log.trace("  " + factory1.createProductB().name());

                log.trace("  " + factory2.createProductA().name());
                log.trace("  " + factory2.createProductB().name());
        }
}
