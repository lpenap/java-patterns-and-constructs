package com.penapereira.example.constructs.templatemethod;

public class ConcreteClassB extends AbstractClass {

    @Override
    protected String stepOne() {
        return "B step one";
    }

    @Override
    protected String stepTwo() {
        return "B step two";
    }
}
