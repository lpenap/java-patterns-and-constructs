package com.penapereira.example.constructs.decorator;

public abstract class Decorator implements ComponentIF {
    protected final ComponentIF component;

    protected Decorator(ComponentIF component) {
        this.component = component;
    }
}
