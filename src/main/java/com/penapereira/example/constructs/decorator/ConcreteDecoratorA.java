package com.penapereira.example.constructs.decorator;

public class ConcreteDecoratorA extends Decorator {

    public ConcreteDecoratorA(ComponentIF component) {
        super(component);
    }

    @Override
    public String operation() {
        return "ConcreteDecoratorA(" + component.operation() + ")";
    }
}
