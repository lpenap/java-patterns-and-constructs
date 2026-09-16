package com.penapereira.example.constructs.producerconsumer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import java.time.Duration;

import org.junit.jupiter.api.Test;

class ProducerConsumerExampleRunnerTests {

    private static long consumerThreads() {
        return Thread.getAllStackTraces().keySet().stream()
                .filter(t -> t.isAlive() && t.getName().startsWith("pool-"))
                .count();
    }

    @Test
    void runsToCompletionAndStopsItsThreads() {
        long before = consumerThreads();

        assertTimeoutPreemptively(Duration.ofSeconds(10), () -> new ProducerConsumerExampleRunner().runExample());

        // Worker threads may still be exiting right after the pool reports termination.
        long deadline = System.currentTimeMillis() + 5_000;
        while (consumerThreads() != before && System.currentTimeMillis() < deadline) {
            Thread.onSpinWait();
        }
        assertEquals(before, consumerThreads());
    }
}
