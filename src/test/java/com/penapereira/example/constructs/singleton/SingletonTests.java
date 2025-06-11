package com.penapereira.example.constructs.singleton;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SingletonTests {
    @Test
    void sameInstanceReturned() {
        Singleton first = Singleton.instance();
        Singleton second = Singleton.instance();
        assertSame(first, second);
    }
}
