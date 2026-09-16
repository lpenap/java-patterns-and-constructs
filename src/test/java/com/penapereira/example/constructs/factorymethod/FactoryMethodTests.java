package com.penapereira.example.constructs.factorymethod;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FactoryMethodTests {
    @Test
    void productsReturnName() {
        GenericProduct a = new ConcreteProductA();
        GenericProduct b = new ConcreteProductB();
        assertEquals("Concrete Product A", a.factoryMethod());
        assertEquals("Concrete Product B", b.factoryMethod());
        a.build();
        b.build();
    }
}
