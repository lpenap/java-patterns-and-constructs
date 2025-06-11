package com.penapereira.example.constructs.factorymethod;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FactoryMethodTests {
    @Test
    void productsReturnName() {
        GenericProduct a = new ConcreteProductA();
        GenericProduct b = new ConcreteProductB();
        assertEquals("ConcretepProduct A", a.factoryMethod());
        assertEquals("ConcretepProduct B", b.factoryMethod());
        a.build();
        b.build();
    }
}
