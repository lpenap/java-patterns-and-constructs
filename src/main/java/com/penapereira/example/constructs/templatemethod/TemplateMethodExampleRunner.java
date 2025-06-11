package com.penapereira.example.constructs.templatemethod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.penapereira.example.constructs.app.ExampleRunnerInterface;

@Component
public class TemplateMethodExampleRunner implements ExampleRunnerInterface {

    private static final Logger log = LoggerFactory.getLogger(TemplateMethodExampleRunner.class);

    @Override
    public void runExample() throws Exception {
        log.trace("Executing Template Method Pattern Implementation");

        AbstractClass a = new ConcreteClassA();
        log.trace("  " + a.templateMethod());

        AbstractClass b = new ConcreteClassB();
        log.trace("  " + b.templateMethod());
    }
}
