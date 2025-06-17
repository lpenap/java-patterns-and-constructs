package com.penapereira.example.constructs.chainofresponsibility;

public class PositiveHandler extends AbstractHandler {
    @Override
    public String handle(int request) {
        if (request > 0) {
            return "positive";
        }
        return super.handle(request);
    }
}
