package com.penapereira.example.constructs.chainofresponsibility;

public abstract class AbstractHandler implements Handler {
    private Handler next;

    @Override
    public void setNext(Handler next) {
        this.next = next;
    }

    @Override
    public String handle(int request) {
        if (next != null) {
            return next.handle(request);
        }
        return "unhandled";
    }
}
