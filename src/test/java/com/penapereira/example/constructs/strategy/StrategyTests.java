package com.penapereira.example.constructs.strategy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StrategyTests {
    @Test
    void contextOperationUsesStrategy() {
        Context ctx = new Context(new StrategyImpl1());
        assertEquals("Operation with --> Algorithm from Strategy Implementation 1", ctx.operation());
        ctx.setStrategy(new StrategyImpl2());
        assertEquals("Operation with ==> Algorithm from Strategy Implementation 2", ctx.operation());
    }
}
