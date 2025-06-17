package com.penapereira.example.constructs.chainofresponsibility;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ChainTests {
    @Test
    void chainProcessesNumbers() {
        Handler negative = new NegativeHandler();
        Handler zero = new ZeroHandler();
        Handler positive = new PositiveHandler();
        negative.setNext(zero);
        zero.setNext(positive);

        assertEquals("negative", negative.handle(-10));
        assertEquals("zero", negative.handle(0));
        assertEquals("positive", negative.handle(5));
    }
}
