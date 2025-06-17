package com.penapereira.example.constructs.chainofresponsibility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.penapereira.example.constructs.app.ExampleRunnerInterface;

@Component
public class ChainOfResponsibilityExampleRunner implements ExampleRunnerInterface {

    private static final Logger log = LoggerFactory.getLogger(ChainOfResponsibilityExampleRunner.class);

    @Override
    public void runExample() throws Exception {
        log.trace("Executing Chain of Responsibility Pattern Implementation");

        Handler negative = new NegativeHandler();
        Handler zero = new ZeroHandler();
        Handler positive = new PositiveHandler();

        negative.setNext(zero);
        zero.setNext(positive);

        int[] requests = { -1, 0, 1 };
        for (int r : requests) {
            log.trace("  " + r + " is " + negative.handle(r));
        }
    }
}
