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
	public void runExample() throws InterruptedException {
		log.trace("Executing Producer/Consumer implementation:");

		BlockingQueue<Integer> blockingQueue = new LinkedBlockingDeque<Integer>(3);
		ExecutorService executor = Executors.newFixedThreadPool(3);

		Consumer consumer1 = new Consumer(blockingQueue, 1);
		Consumer consumer2 = new Consumer(blockingQueue, 2);
		Producer producer = new Producer(blockingQueue);
		
		executor.execute(consumer1);
		executor.execute(consumer2);
		executor.execute(producer);
		
		executor.awaitTermination(1, TimeUnit.SECONDS);
	}
}
