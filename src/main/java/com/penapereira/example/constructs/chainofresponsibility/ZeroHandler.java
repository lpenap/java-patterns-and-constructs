package com.penapereira.example.constructs.chainofresponsibility;

public class ZeroHandler extends AbstractHandler {
    @Override
    public String handle(int request) {
        if (request == 0) {
            return "zero";
        }
        return super.handle(request);
    }
}
