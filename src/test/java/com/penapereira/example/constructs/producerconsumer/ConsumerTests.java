package com.penapereira.example.constructs.producerconsumer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import org.junit.jupiter.api.Test;

class ConsumerTests {

    private static void waitUntilBlockedOnQueue(Thread t) {
        while (t.getState() != Thread.State.WAITING) {
            Thread.onSpinWait();
        }
    }

    @Test
    void consumesEverythingAndStopsWhenInterrupted() throws InterruptedException {
        BlockingQueue<Integer> queue = new LinkedBlockingDeque<>();
        queue.put(1);
        queue.put(2);
        Thread t = new Thread(new Consumer(queue, 1));

        t.start();
        waitUntilBlockedOnQueue(t);
        assertTrue(queue.isEmpty());

        t.interrupt();
        t.join(5_000);
        assertFalse(t.isAlive());
    }

    @Test
    void stopsImmediatelyWhenInterruptedBeforeStarting() throws InterruptedException {
        BlockingQueue<Integer> queue = new LinkedBlockingDeque<>();
        queue.put(1);
        Thread t = new Thread(() -> {
            Thread.currentThread().interrupt();
            new Consumer(queue, 2).run();
        });

        t.start();
        t.join(5_000);

        assertFalse(t.isAlive());
        assertFalse(queue.isEmpty());
    }
}
