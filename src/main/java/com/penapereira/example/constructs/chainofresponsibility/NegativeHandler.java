package com.penapereira.example.constructs.chainofresponsibility;

public class NegativeHandler extends AbstractHandler {
    @Override
    public String handle(int request) {
        if (request < 0) {
            return "negative";
        }
        return super.handle(request);
    }
}
