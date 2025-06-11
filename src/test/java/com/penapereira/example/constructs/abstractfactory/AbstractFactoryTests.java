package com.penapereira.example.constructs.abstractfactory;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AbstractFactoryTests {
    @Test
    void factoriesProduceCorrectProducts() {
        AbstractFactory f1 = new Factory1();
        AbstractFactory f2 = new Factory2();
        assertEquals("ProductA1", f1.createProductA().name());
        assertEquals("ProductB1", f1.createProductB().name());
        assertEquals("ProductA2", f2.createProductA().name());
        assertEquals("ProductB2", f2.createProductB().name());
    }
}
