package com.penapereira.example.constructs.producerconsumer;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import org.junit.jupiter.api.Test;

class ProducerTests {
    @Test
    void producerAddsElements() throws InterruptedException {
        BlockingQueue<Integer> queue = new LinkedBlockingDeque<>(3);
        Producer producer = new Producer(queue);
        Thread t = new Thread(producer);
        t.start();
        t.join();
        assertEquals(3, queue.size());
        assertEquals(1, queue.take());
        assertEquals(2, queue.take());
        assertEquals(3, queue.take());
    }
}
