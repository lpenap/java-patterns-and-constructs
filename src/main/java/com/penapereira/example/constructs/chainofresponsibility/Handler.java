package com.penapereira.example.constructs.chainofresponsibility;

public interface Handler {
    void setNext(Handler next);
    String handle(int request);
}
