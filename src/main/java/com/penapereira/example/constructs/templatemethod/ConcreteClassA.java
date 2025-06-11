package com.penapereira.example.constructs.templatemethod;

public class ConcreteClassA extends AbstractClass {

    @Override
    protected String stepOne() {
        return "A step one";
    }

    @Override
    protected String stepTwo() {
        return "A step two";
    }
}
