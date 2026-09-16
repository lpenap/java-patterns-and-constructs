package com.penapereira.example.constructs.producerconsumer;

import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Consumer implements Runnable {

	private static final Logger log = LoggerFactory.getLogger(Consumer.class);

	private final BlockingQueue<Integer> queue;
	private final int myId;

	public Consumer(BlockingQueue<Integer> blockingQueue, int myId) {
		this.queue = blockingQueue;
		this.myId = myId;
	}

	/**
	 * Consumes integers until the thread is interrupted, which is how the
	 * example runner asks the consumers to stop once the producer is done.
	 */
	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			try {
				var consumed = queue.take();
				log.trace(String.format("  %d: Consumed [%2d]", myId, consumed));
			} catch (InterruptedException finish) {
				Thread.currentThread().interrupt();
			}
		}
	}
}
