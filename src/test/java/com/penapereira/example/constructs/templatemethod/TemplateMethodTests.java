package com.penapereira.example.constructs.templatemethod;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TemplateMethodTests {
    @Test
    void templateMethodRunsSteps() {
        AbstractClass a = new ConcreteClassA();
        assertEquals("A step one then A step two", a.templateMethod());
        AbstractClass b = new ConcreteClassB();
        assertEquals("B step one then B step two", b.templateMethod());
    }
}
