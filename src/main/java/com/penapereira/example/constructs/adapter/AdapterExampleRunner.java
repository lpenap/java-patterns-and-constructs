package com.penapereira.example.constructs.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.penapereira.example.constructs.app.ExampleRunnerInterface;

@Component
public class AdapterExampleRunner implements ExampleRunnerInterface {

    private static final Logger log = LoggerFactory.getLogger(AdapterExampleRunner.class);

    @Override
    public void runExample() throws Exception {
        log.trace("Executing Adapter Pattern Implementation");

        Adaptee adaptee = new Adaptee();
        Target target = new Adapter(adaptee);

        log.trace("  " + target.request());
    }
}
