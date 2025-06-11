package com.penapereira.example.constructs.templatemethod;

public abstract class AbstractClass {

    protected abstract String stepOne();

    protected abstract String stepTwo();

    public final String templateMethod() {
        return stepOne() + " then " + stepTwo();
    }
}
