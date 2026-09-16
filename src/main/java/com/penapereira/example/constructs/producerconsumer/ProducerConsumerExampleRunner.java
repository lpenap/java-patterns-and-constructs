package com.penapereira.example.constructs.producerconsumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.penapereira.example.constructs.app.ExampleRunnerInterface;

@Component
public class ProducerConsumerExampleRunner implements ExampleRunnerInterface {

	private static final Logger log = LoggerFactory.getLogger(ProducerConsumerExampleRunner.class);

	@Override
	public void runExample() throws Exception {
		log.trace("Executing Producer/Consumer implementation:");
		BlockingQueue<Integer> blockingQueue = new LinkedBlockingDeque<>(3);
		ExecutorService executor = Executors.newFixedThreadPool(3);
		try {
			executor.execute(new Consumer(blockingQueue, 1));
			executor.execute(new Consumer(blockingQueue, 2));
			// Wait until the producer has put every item in the queue...
			executor.submit(new Producer(blockingQueue)).get();
			// ...and until the consumers have taken all of them.
			while (!blockingQueue.isEmpty()) {
				Thread.sleep(10);
			}
		} finally {
			// Consumers block forever waiting for more work; interrupt them so
			// the pool threads do not leak after the example finishes.
			executor.shutdownNow();
			if (!executor.awaitTermination(1, TimeUnit.SECONDS)) {
				log.warn("Consumers did not stop in time");
			}
		}
	}
}
