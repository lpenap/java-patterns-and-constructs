package com.penapereira.example.constructs.decorator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.penapereira.example.constructs.app.ExampleRunnerInterface;

@Component
public class DecoratorExampleRunner implements ExampleRunnerInterface {

    private static final Logger log = LoggerFactory.getLogger(DecoratorExampleRunner.class);

    @Override
    public void runExample() throws Exception {
        log.trace("Executing Decorator Pattern Implementation");

        ComponentIF component = new ConcreteComponent();
        ComponentIF decorated = new ConcreteDecoratorA(component);

        log.trace("  " + decorated.operation());
    }
}
