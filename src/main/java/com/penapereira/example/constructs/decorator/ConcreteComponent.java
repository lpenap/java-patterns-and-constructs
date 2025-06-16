package com.penapereira.example.constructs.decorator;

public class ConcreteComponent implements ComponentIF {
    @Override
    public String operation() {
        return "ConcreteComponent";
    }
}
