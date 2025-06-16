package com.penapereira.example.constructs.adapter;

public class Adapter implements Target {
    private final Adaptee adaptee;

    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public String request() {
        return "Adapter(" + adaptee.specificRequest() + ")";
    }
}
