package com.penapereira.example.constructs.factory;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FactoryTests {
    @Test
    void factoryCreatesConcreteProducts() {
        ProductFactory factory = new ProductFactory();
        Product a = factory.createProduct("A");
        Product b = factory.createProduct("B");
        assertEquals("Concrete Product A", a.name());
        assertEquals("Concrete Product B", b.name());
    }
}
